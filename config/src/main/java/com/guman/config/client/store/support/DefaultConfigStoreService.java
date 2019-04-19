package com.guman.config.client.store.support;

import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.io.Files;
import com.guman.config.client.ConfigEnv;
import com.guman.config.client.spring.annotation.Config;
import com.guman.config.client.store.ConfigService;
import com.guman.config.client.store.ConfigStore;
import com.guman.config.client.store.ConfigStore.*;
import com.guman.config.client.store.ConfigStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static com.guman.common.constant.CommonConstants.SPLITTER_COMMA;

/**
 * @author duanhaoran
 * @since 2019/4/16 7:38 PM
 */
public class DefaultConfigStoreService implements ConfigStoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfigStoreService.class);

    private static final String VERSION_EXT = ".ver1";

    private static final String SNAPSHOT_DIR = "/v1";

    private static final String SNAPSHOT_FORMAT = "%s" + SNAPSHOT_DIR + "/%s.%s.%s";

    private static final String OVERRIDE_FORMAT = "override/%s/%s";

    private static final String LOCAL_TEST_FORMAT = "wconfig_test/%s/%s";

    private static final String REFERENCE_FORMAT = "%s/%s%s";

    private static final int MAX_RETAIN_VERSION = 3;

    private static final Logger logger = LoggerFactory.getLogger(DefaultConfigStoreService.class);

    private static final Ordering<File> FILE_ORDERING = Ordering.natural().reverse().onResultOf(input -> {
        Assert.notNull(input, "File must not be null");
        String name = input.getName();
        int profilePosition = name.lastIndexOf('.');
        name = name.substring(0, profilePosition);
        int versionPosition = name.lastIndexOf('.');
        return Integer.parseInt(name.substring(versionPosition + 1));
    });

    @Resource
    private ConfigService configService;

    @Override
    public Map<Key, Version> getReference(ConfigEnv env) {
        Map<Key, Version> references = Maps.newHashMap();
        try {
            for (File applicationFile : Objects.requireNonNull(env.getConfigStoreHome().listFiles(File::isDirectory))) {
                String application = applicationFile.getName();
                for (File referenceFile : Objects.requireNonNull(applicationFile.listFiles(pathname -> pathname.getName().equals(VERSION_EXT)))) {
                    Version version = readVersion(referenceFile);
                    String name = referenceFile.getName();
                    name = name.substring(0, name.length() - VERSION_EXT.length());
                    references.put(new DefaultKey(application, name, env), version);
                }
            }
        } catch (Throwable throwable) {
            logger.error("初始化加载所有应用文件失败！", throwable);
        }
        return references;
    }

    @Override
    public boolean hasSnapshot(Key key, Version version) {
        ConfigEnv env = key.getEnv();
        return createSnapshotFile(env.getConfigStoreHome(), key, version).exists();
    }

    private File createSnapshotFile(File configStoreHome, Key key, Version version) {
        String snapshot = String.format(SNAPSHOT_FORMAT, key.getApplication(), key.getName(), version.getVersion(), version.getProfile());
        return new File(configStoreHome, snapshot);
    }

    private File createReferenceFile(File configStoreHome, Key key) {
        String snapshot = String.format(REFERENCE_FORMAT, key.getApplication(), key.getName(), VERSION_EXT);
        return new File(configStoreHome, snapshot);
    }
    private static File createOverrideFile(File configStoreHome, Key key) {
        String override = String.format(OVERRIDE_FORMAT, key.getApplication(), key.getName());
        return new File(configStoreHome, override);
    }

    private static File createLocalTestFile(Key key) throws URISyntaxException {
        String localTest = String.format(LOCAL_TEST_FORMAT, key.getApplication(), key.getName());
        URL url = Thread.currentThread().getContextClassLoader().getResource(localTest);
        if (url == null) {
            return null;
        }
        return new File(url.toURI());
    }

    @Override
    public ConfigStore create(Key key) {
        return new DefaultConfigStore(key, configService, this);
    }

    @Override
    public void storeSnapshot(Key key, Snapshot<String> snapshot) throws IOException {

    }

    Version readVersion(File referenceFile) {
        try {
            if (referenceFile.canRead()) {
                String line = Files.readFirstLine(referenceFile, Charset.defaultCharset());
                Iterable<String> strs = SPLITTER_COMMA.split(line);
                Iterator<String> iterator = strs.iterator();
                long version = Long.parseLong(iterator.next());
                String profile = iterator.next();
                return new DefaultVersion(version, profile);
            }
        } catch (IOException e) {
            logger.error("读取引用文件失败！{}", referenceFile);
        }
        return DefaultConfigStoreContainer.ABSENT;
    }
}
