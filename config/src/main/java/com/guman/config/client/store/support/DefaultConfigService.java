package com.guman.config.client.store.support;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListenableFuture;
import com.guman.common.pojo.Status;
import com.guman.config.client.ConfigConstant;
import com.guman.config.client.ConfigEnv;
import com.guman.config.client.ConfigEnv;
import com.guman.config.client.store.ConfigService;
import com.guman.config.client.store.ConfigStore.Key;
import com.guman.config.client.store.ConfigStore.Version;
import com.guman.config.client.store.ConfigStore.Snapshot;
import io.netty.buffer.PooledByteBufAllocator;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.guman.config.client.ConfigConstant.*;
import static com.guman.config.client.ConfigEnv.clientConnectionTimeoutKey;
import static com.guman.config.client.ConfigEnv.clientRequestTimeoutKey;

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

    private Map<String, List<String>> servers;

    private static final AtomicInteger index = new AtomicInteger(new Random().nextInt(1000));

    private static final Logger log = LoggerFactory.getLogger(DefaultConfigService.class);

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
        return null;
    }

    @Override
    public ListenableFuture<String> loadData(Key key, Version version) {
        return null;
    }

    @Override
    public ListenableFuture<String> viewData(Key key, Version version) {
        return null;
    }

    @Override
    public ListenableFuture<Snapshot<String>> forceReload(Key key, String profile) {
        return null;
    }

    @Override
    public ListenableFuture<Status> update(ConfigItem item) {
        return null;
    }

    @Override
    public ListenableFuture<Status> batchUpdate(List<ConfigItem> items) {
        return null;
    }

    @Override
    public void record(ConfigOperatorType type, Key key, long version, Throwable e) {

    }

    @Override
    public void record(ConfigOperatorType type, String application, String name, Throwable e) {

    }
}
