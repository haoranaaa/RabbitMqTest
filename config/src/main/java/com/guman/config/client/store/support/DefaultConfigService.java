package com.guman.config.client.store.support;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.guman.common.json.JSON;
import com.guman.common.json.JsonResult;
import com.guman.common.pojo.Status;
import com.guman.common.util.Checksumer;
import com.guman.config.client.ConfigEnv;
import com.guman.config.client.store.ConfigService;
import com.guman.config.client.store.ConfigStore.Key;
import com.guman.config.client.store.ConfigStore.Snapshot;
import com.guman.config.client.store.ConfigStore.Version;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.asynchttpclient.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.guman.common.constant.CommonConstants.ACCEPT_JSON;
import static com.guman.common.constant.CommonConstants.CONTENT_TYPE_JSON;
import static com.guman.common.constant.CommonConstants.STRING_EMPTY;
import static com.guman.config.client.ConfigConstant.*;
import static com.guman.config.client.ConfigEnv.*;

/**
 * @author duanhaoran
 * @since 2019/4/15 11:50 AM
 */
public class DefaultConfigService implements ConfigService {

    private ConfigEnv env;

    private int connectionTimeout;

    private int requestTimeout;

    private AsyncHttpClient client;

    private volatile ListenableFuture<List<String>> urlsFuture;

    private String appName;

    private int port;

    private Map<String, List<String>> servers;

    private static final AtomicInteger index = new AtomicInteger(new Random().nextInt(1000));

    private static final Logger log = LoggerFactory.getLogger(DefaultConfigService.class);

    private static final String DEFAULT_POINT_URL = "localhost:8080/";

    private static final Function<ConfigItem, Map<Object, Object>> batchItemFunction = input -> ImmutableMap.builder()
            .put(APPLICATION_NAME, input.getKey().getApplication())
            .put(NAME_NAME, input.getKey().getName())
            .put(VERSION_NAME, input.getVersion().getProfile())
            .put(DATA_NAME, input.getData())
            .put(FEATURE_NAME, input.getFeature())
            .build();

    @Override
    public void setup(ConfigEnv env) {
        this.env = env;
        this.connectionTimeout = env.getInteger(clientConnectionTimeoutKey, CONNECTION_TIMEOUT);
        this.requestTimeout = env.getInteger(clientRequestTimeoutKey, REQUEST_TIMEOUT);
        this.client = createClient();
        initEntryPoint();
    }

    private void initEntryPoint() {
        this.servers = Maps.newConcurrentMap();
        servers.put(CHECK_UPDATE_URL, ImmutableList.of());
        servers.put(LOAD_DATA_URL, ImmutableList.of());
        servers.put(FORCE_RELOAD_URL, ImmutableList.of());
        servers.put(RECORD_LOADING_URL, ImmutableList.of());
        servers.put(UPDATE_DATA_URL, ImmutableList.of());
        servers.put(BATCH_UPDATE_DATA_URL, ImmutableList.of());
        fetchServers();
    }

    private void fetchServers() {
        BoundRequestBuilder boundRequestBuilder = client.prepareGet(String.format(entryPointUrl(), appName))
                .addHeader(PROFILE_NAME, env.getString(profileKey));
        HttpListenableFuture<Response> future = HttpListenableFuture.wrap(client.executeRequest(boundRequestBuilder.build()));
        this.urlsFuture = Futures.transform(future, input -> {
            if (input == null || input.getStatusCode() != HttpResponseStatus.OK.code()) {
                String message = String.format("Status Code is not 200. statusCode:{}", input == null ? "" : input.getStatusCode());
                log.warn(message);
                throw new RuntimeException(message);
            }

            List<String> servers = JSON.readValue(input.getResponseBodyAsStream(), new TypeReference<List<String>>() {
            });

            if (CollectionUtils.isEmpty(servers)) {
                String message = "No config server address received !";
                log.warn(message);
                throw new RuntimeException(message);
            }
            return servers;
        });

    }

    private String entryPointUrl() {
        String pointUrl = "";
        Properties properties = new Properties();
        try (FileInputStream inStream = new FileInputStream(CONFIG_SETTING)) {
            properties.load(inStream);
            pointUrl = properties.getProperty(CONFIG_SETTING, DEFAULT_POINT_URL);
            return pointUrl;
        } catch (IOException e) {
            log.error("请确保已配置了config_setting.properties！");
            return DEFAULT_POINT_URL;
        }
    }

    private AsyncHttpClient createClient() {
        DefaultAsyncHttpClientConfig.Builder builder = new DefaultAsyncHttpClientConfig.Builder();
        builder.setConnectTimeout(connectionTimeout);
        builder.setRequestTimeout(requestTimeout);
        builder.setAllocator(PooledByteBufAllocator.DEFAULT);
        builder.setCompressionEnforced(true);
        builder.setPooledConnectionIdleTimeout(3 * 60 * 1000);
        return new DefaultAsyncHttpClient(builder.build());
    }

    @Override
    public ListenableFuture<Map<Key, Version>> checkUpdate(Map<Key, Version> configStores) {
        fetchServers();
        return new CheckUpdateFuture(client, resolve(CHECK_UPDATE_URL), configStores).request();
    }

    private List<String> resolve(String suffix) {
        List<String> addresses = null;
        try {
            addresses = urlsFuture.get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            //ignore
            log.info("ignore get Future");
        }

        if (CollectionUtils.isEmpty(addresses)) {
            List<String> urls = defaultAddress();
            return urls;
        }
        List<String> urls = Lists.newArrayListWithCapacity(addresses.size());
        for (String address : addresses) {
            urls.add("http://" + address + suffix);
        }
        servers.put(suffix, urls);
        return urls;
    }

    private List<String> defaultAddress() {
        return Lists.newArrayList();
    }

    @Override
    public ListenableFuture<String> loadData(Key key, Version version) {
        return new LoadDataFuture<String>(client, resolve(LOAD_DATA_URL), key, version).request();
    }

    @Override
    public ListenableFuture<String> viewData(Key key, Version version) {
        return new ViewDataFuture(client, resolve(VIEW_DATA_URL), key, version).request();
    }

    @Override
    public ListenableFuture<Snapshot<String>> forceReload(Key key, String profile) {
        return new ForceReloadFuture(client, resolve(FORCE_RELOAD_URL), key, profile);
    }

    @Override
    public ListenableFuture<Status> update(ConfigItem item) {
        return new UpdateDataFuture(client, resolve(UPDATE_DATA_URL), item);
    }

    @Override
    public ListenableFuture<Status> batchUpdate(List<ConfigItem> items) {
        return new BatchUpdateDataFuture(client, resolve(BATCH_UPDATE_DATA_URL), items);
    }

    @Override
    public void record(ConfigOperatorType type, Key key, long version, Throwable e) {
        String remarks = e != null ? (e.getClass().getName() + ":" + e.getMessage()) : STRING_EMPTY;
        try {
            new RecordLoadingFuture(client, resolve(RECORD_LOADING_URL), type, key, version, remarks);
        } catch (Throwable ex) {
            log.error("record error, type: {}, key: {}, version: {}, remarks={}", type, key, version, remarks, ex);
        }
    }

    @Override
    public void record(ConfigOperatorType type, String application, String name, Throwable e) {
        Key key = new DefaultKey(application, name,this.env);
        record(type, key, -1, e);
    }

    static class HttpListenableFuture<T> implements ListenableFuture<T> {

        private final org.asynchttpclient.ListenableFuture<T> future;

        HttpListenableFuture(org.asynchttpclient.ListenableFuture future) {
            this.future = future;
        }

        public static <U> HttpListenableFuture<U> wrap(org.asynchttpclient.ListenableFuture<U> future) {
            return new HttpListenableFuture<>(future);
        }

        @Override
        public void addListener(Runnable runnable, Executor executor) {
            future.addListener(runnable, executor);
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return future.cancel(mayInterruptIfRunning);
        }

        @Override
        public boolean isCancelled() {
            return future.isCancelled();
        }

        @Override
        public boolean isDone() {
            return future.isDone();
        }

        @Override
        public T get() throws InterruptedException, ExecutionException {
            return future.get();
        }

        @Override
        public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return future.get(timeout, unit);
        }
    }

    private static class CheckUpdateResponse {

        private List<Item> changedItems;

        @JsonCreator
        public CheckUpdateResponse(@JsonProperty("changedItems") List<Item> changedItems) {
            this.changedItems = changedItems;
        }

        static class Item {

            private String application;

            private String name;

            private DefaultVersion version;

            @JsonCreator
            public Item(
                    @JsonProperty("appName") String application,
                    @JsonProperty("dataId") String name,
                    @JsonProperty("version") long version,
                    @JsonProperty("profile") String profile
            ) {
                this.application = application;
                this.name = name;
                this.version = new DefaultVersion(version, profile);
            }

            DefaultKey getDefaultKey(ConfigEnv env) {
                return new DefaultKey(application, name, env);
            }

            DefaultVersion getVersion() {
                return version;
            }
        }

    }

    private abstract class RetryFuture<T> extends AbstractFuture<T> {

        protected final AsyncHttpClient client;

        private final List<String> urls;

        private final int maxRetryTime;

        protected Throwable lastException;

        private int retries = 0;

        public RetryFuture(AsyncHttpClient client, List<String> urls) {
            this(client, urls, urls.size());
        }

        protected RetryFuture(AsyncHttpClient client, List<String> urls, int maxRetryTime) {
            this.client = client;
            this.urls = urls;
            this.maxRetryTime = maxRetryTime;
        }

        protected abstract BoundRequestBuilder buildRequest(String url) throws IOException;

        protected abstract T process(Request request, Response response) throws Throwable;

        public RetryFuture<T> request() {
            if (retries++ > maxRetryTime) {
                setException(new RuntimeException("Failed to request. Tried " + maxRetryTime + " times of the urls " + urls + ". Last error is: " + lastException.getMessage(),
                        lastException));
                return this;
            }
            if (isDone()) {
                return this;
            }
            try {
                String profile = env.getString(profileKey);
                BoundRequestBuilder builder = buildRequest(String.format(select(), appName))
                        .addHeader(PROFILE_NAME, profile);
                Request request = builder.build();
                org.asynchttpclient.ListenableFuture<Response> future = client.executeRequest(request);

                future.addListener(() -> {
                    Response response;
                    try {
                        response = future.get();
                    } catch (Throwable e) {
                        log.warn("Get response error, try to fail over. profile: {}, appName: {}", profile, appName, e);
                        lastException = e;
                        failOver();
                        request();
                        return;
                    }

                    if (response.getStatusCode() == HttpResponseStatus.FORBIDDEN.code()) {
                        String message = String.format("Please confirm your requested application permissions. profile: %s, appName: %s", profile, appName);
                        RuntimeException ex = new RuntimeException(message);
                        log.error(message, ex);
                        setException(ex);
                        return;
                    }

                    if (response.getStatusCode() == HttpResponseStatus.BAD_REQUEST.code()) {
                        String message = String.format("The request parameters are incorrect. profile: %s, appName: %s", profile, appName);
                        setException(new RuntimeException(message));
                        return;
                    }

                    try {
                        set(process(request, response));
                    } catch (Throwable e) {
                        lastException = e;
                        failOver();
                        request();
                    }
                }, MoreExecutors.directExecutor());
            } catch (Throwable e) {
                lastException = e;
                failOver();
                request();
            }
            return this;
        }

        private String select() {
            return urls.get(Math.abs(index.get() % urls.size()));
        }

        private void failOver() {
            index.incrementAndGet();
        }
    }

    private class CheckUpdateFuture extends RetryFuture<Map<Key, Version>> {

        private final String requestBody;

        CheckUpdateFuture(AsyncHttpClient client, List<String> urls, Map<Key, Version> keys) {
            super(client, urls);
            this.requestBody = buildRequestBody(keys);
        }

        @Override
        protected BoundRequestBuilder buildRequest(String url) throws IOException {
            return client.preparePost(url)
                    .setHeader("Content-Type", CONTENT_TYPE_JSON)
                    .setHeader("Accept", ACCEPT_JSON)
                    .setBody(requestBody);
        }

        @Override
        protected Map<Key, Version> process(Request request, Response response) throws Throwable {
            if (response.getStatusCode() == HttpResponseStatus.NOT_MODIFIED.code()) {
                return ImmutableMap.of();
            }

            if (response.getStatusCode() != HttpResponseStatus.OK.code()) {
                log.warn("{} response status code is {}", request.getUrl(), response.getStatusCode());
                String message = String.format("Request url error! url: %s, response: %s", request.getUrl(), response);
                throw new RuntimeException(message);
            }

            try {
                return parseResponseBody(response, env);
            } catch (Throwable e) {
                log.warn("ParseResponseBody response error", e);
                throw e;
            }
        }

        private Map<Key, Version> parseResponseBody(Response response, ConfigEnv env) throws IOException {
            String responseBody = response.getResponseBody();
            CheckUpdateResponse checkResult = JSON.readValue(responseBody, CheckUpdateResponse.class);
            Map<Key, Version> result = Maps.newHashMap();
            try {
                for (CheckUpdateResponse.Item item : checkResult.changedItems) {
                    result.put(item.getDefaultKey(env), item.getVersion());
                }
            } catch (Exception e) {
                log.error("Illegal data format, {}", responseBody, e);
            }
            return result;
        }

        private String buildRequestBody(Map<Key, Version> keys) {
            List<Map<String, Object>> items = Lists.newArrayListWithCapacity(keys.size());
            for (Map.Entry<Key, Version> entry : keys.entrySet()) {
                Map<String, Object> item = Maps.newHashMap();
                Key key = entry.getKey();
                Version version = entry.getValue();
                item.put(APPLICATION_NAME, key.getApplication());
                item.put(NAME_NAME, key.getName());
                item.put(PROFILE_NAME, version.getProfile());
                item.put(VERSION_NAME, version.getVersion());
                items.add(item);
            }
            return JSON.writeValueAsString(ImmutableMap.of("checkList", items));
        }
    }

    private class RecordLoadingFuture extends RetryFuture<Void> {

        private final ConfigOperatorType type;

        private final Key key;

        private final long version;

        private final String remarks;

        private RecordLoadingFuture(AsyncHttpClient client, List<String> urls, ConfigOperatorType type, Key key, long version, String remarks) {
            super(client, urls, 0);
            this.type = type;
            this.key = key;
            this.version = version;
            this.remarks = remarks;
        }

        @Override
        protected BoundRequestBuilder buildRequest(String url) throws IOException {
            return client.preparePut(url)
                    .setHeader("Content-Type", CONTENT_TYPE_JSON)
                    .setHeader("Accept", ACCEPT_JSON)
                    .setBody(JSON.writeValueAsString(ImmutableMap.builder()
                            .put(APPLICATION_NAME, key.getApplication())
                            .put(NAME_NAME, key.getName())
                            .put(VERSION_NAME, version)
                            .put(CONFIG_OPERATOR_TYPE, type.getCode())
                            .put(REMARKS_NAME, remarks)
                            .build()
                    ));

        }

        @Override
        protected Void process(Request request, Response response) throws Throwable {
            if (response.getStatusCode() != HttpResponseStatus.OK.code()) {
                String message = String.format("Request url error! url: %s, response: %s", request.getUrl(), response);
                throw new RuntimeException(message);
            }
            return null;
        }
    }

    private class LoadDataFuture<T> extends RetryFuture<T> {

        private final Key key;

        private final Version version;

        public LoadDataFuture(AsyncHttpClient client, List<String> urls, Key key, Version version) {
            super(client, urls);
            this.key = key;
            this.version = version;
        }

        @Override
        protected BoundRequestBuilder buildRequest(String url) throws IOException {
            return client.prepareGet(url)
                    .addQueryParam(APPLICATION_NAME, key.getApplication())
                    .addQueryParam(NAME_NAME, key.getName())
                    .addQueryParam(VERSION_NAME, String.valueOf(version.getVersion()))
                    .addQueryParam(PROFILE_NAME, StringUtils.hasText(version.getProfile()) ?
                            version.getProfile() : key.getEnv().getString(profileKey));
        }

        @Override
        protected T process(Request request, Response response) throws Throwable {
            if (response.getStatusCode() == HttpResponseStatus.NOT_FOUND.code()) {
                throw new FileNotFoundException("No configuration file found, name: " + key.getName() + ", version: " + version);
            }

            if (response.getStatusCode() != HttpResponseStatus.OK.code()) {
                log.warn("{} response status code is {}", request.getUrl(), response.getStatusCode());
                String message = String.format("Request url error! url: %s, response: %s", request.getUrl(), response);
                throw new RuntimeException(message);
            }

            String expected = response.getHeader(CHECKSUM_NAME);
            String body = response.getResponseBody(Charsets.UTF_8);
            String actual = Checksumer.checksum(body);

            if (!actual.equals(expected)) {
                log.warn("Checksum verification failed, try to re-pull configuration. actual: {}, expected: {}", actual, expected);
                String message = String.format("Checksum verification failed! url: %s, expected: %s, actual: %s, response: %s", request.getUrl(), expected, actual, response);
                throw new RuntimeException(message);
            }
            return getResult(response, body);
        }

        @SuppressWarnings("unchecked")
        protected T getResult(Response response, String body) {
            return (T) body;
        }
    }

    private class ViewDataFuture extends RetryFuture<String> {

        private final Key key;

        private final Version version;

        public ViewDataFuture(AsyncHttpClient client, List<String> urls, Key key, Version version) {
            super(client, urls);
            this.key = key;
            this.version = version;
        }

        @Override
        protected BoundRequestBuilder buildRequest(String url) throws IOException {
            return client.prepareGet(url)
                    .addQueryParam(APPLICATION_NAME, key.getApplication())
                    .addQueryParam(NAME_NAME, key.getName())
                    .addQueryParam(VERSION_NAME, String.valueOf(version.getVersion()))
                    .addQueryParam(PROFILE_NAME, StringUtils.hasText(version.getProfile()) ?
                            version.getProfile() : key.getEnv().getString(profileKey));
        }

        @Override
        protected String process(Request request, Response response) throws Throwable {
            if (response.getStatusCode() == HttpResponseStatus.NOT_FOUND.code()) {
                return null;
            }

            if (response.getStatusCode() != HttpResponseStatus.OK.code()) {
                log.warn("{} response status code is {}", request.getUrl(), response.getStatusCode());
                String message = String.format("Request url error! url: %s, response: %s", request.getUrl(), response);
                throw new RuntimeException(message);
            }

            return response.getResponseBody(Charsets.UTF_8);
        }
    }

    private class UpdateDataFuture extends RetryFuture<Status> {

        private final ConfigItem item;

        public UpdateDataFuture(AsyncHttpClient client, List<String> urls, ConfigItem item) {
            super(client, urls);
            this.item = item;
        }

        @Override
        protected BoundRequestBuilder buildRequest(String url) throws IOException {
            return client.preparePost(url)
                    .setHeader("Content-Type", CONTENT_TYPE_JSON)
                    .setHeader("Accept", ACCEPT_JSON)
                    .setBody(JSON.writeValueAsString(ImmutableMap.builder()
                            .put(APPLICATION_NAME, item.getKey().getApplication())
                            .put(NAME_NAME, item.getKey().getName())
                            .put(VERSION_NAME, item.getVersion().getVersion())
                            .put(PROFILE_NAME, item.getVersion().getProfile())
                            .put(DATA_NAME, item.getData())
                            .put(FEATURE_NAME, item.getFeature())
                            .build()
                    ));
        }

        @Override
        protected Status process(Request request, Response response) throws Throwable {
            if (response.getStatusCode() != HttpResponseStatus.OK.code()) {
                log.warn("{} response status code is {}", request.getUrl(), response.getStatusCode());
                String message = String.format("Request url error! url: %s, response: %s", request.getUrl(), response);
                throw new RuntimeException(message);
            }

            JsonResult<Void> result = JSON.readValue(response.getResponseBody(Charsets.UTF_8), JsonResult.class);
            return Status.create(result.getStatus(), result.getMsg());
        }
    }

    private class ForceReloadFuture extends LoadDataFuture<Snapshot<String>> {

        public ForceReloadFuture(AsyncHttpClient client, List<String> urls, Key key, String profile) {
            super(client, urls, key, new DefaultVersion(0L, profile));
        }

        @Override
        protected Snapshot<String> getResult(Response response, String result) {
            String profile = response.getHeader(PROFILE_NAME);
            long version = Long.parseLong(response.getHeader(VERSION_NAME));
            return new DefaultSnapshot(new DefaultVersion(version, profile), result);
        }
    }

    private class BatchUpdateDataFuture extends RetryFuture<Status> {

        private final List<ConfigItem> items;

        public BatchUpdateDataFuture(AsyncHttpClient client, List<String> urls, List<ConfigItem> items) {
            super(client, urls);
            this.items = items;
        }

        @Override
        protected BoundRequestBuilder buildRequest(String url) throws IOException {
            return client.preparePost(url)
                    .setHeader("Content-Type", CONTENT_TYPE_JSON)
                    .setHeader("Accept", ACCEPT_JSON)
                    .setBody(JSON.writeValueAsString(items.stream().map(batchItemFunction::apply).collect(Collectors.toList())));
        }

        @Override
        protected Status process(Request request, Response response) throws Throwable {
            if (response.getStatusCode() != HttpResponseStatus.OK.code()) {
                log.warn("{} response status code is {}", request.getUrl(), response.getStatusCode());
                String message = String.format("Request url error! url: %s, response: %s", request.getUrl(), response);
                throw new RuntimeException(message);
            }

            JsonResult<Void> result = JSON.readValue(response.getResponseBody(Charsets.UTF_8), JsonResult.class);
            return Status.create(result.getStatus(), result.getMsg());
        }
    }
}
