package com.guman.config.client.store;

import com.google.common.util.concurrent.ListenableFuture;
import com.guman.common.pojo.CodeEnum;
import com.guman.common.pojo.Status;
import com.guman.config.client.ConfigEnv;
import com.guman.config.client.Feature;
import com.guman.config.client.store.ConfigStore.Key;
import com.guman.config.client.store.ConfigStore.Version;
import com.guman.config.client.store.ConfigStore.Snapshot;

import java.util.List;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/4/15 11:40 AM
 */
public interface ConfigService {

    void setup(ConfigEnv env);

    ListenableFuture<Map<Key, ConfigStore.Version>> checkUpdate(Map<ConfigStore.Key, ConfigStore.Version> configStores);

    ListenableFuture<String> loadData(Key key, Version version);

    ListenableFuture<String> viewData(Key key, Version version);

    ListenableFuture<Snapshot<String>> forceReload(Key key, String profile);

    ListenableFuture<Status> update(ConfigItem item);

    ListenableFuture<Status> batchUpdate(List<ConfigItem> items);

    void record(ConfigOperatorType type, Key key, long version, Throwable e);

    void record(ConfigOperatorType type, String application, String name, Throwable e);


    /**
     * @author duanhaoran
     * @since 2019/4/15 11:45 AM
     */
    enum ConfigOperatorType implements CodeEnum<ConfigOperatorType> {

        PULL_SUCCESS(1),
        PULL_ERROR(2),
        PARSE_REMOTE_ERROR(3),
        USE_OVERRIDE(4),
        USE_REMOTE_FILE(5),
        UPDATE_REMOTE_ERROR(6);

        private int code;

        ConfigOperatorType(int code) {
            this.code = code;
        }

        @Override
        public int getCode() {
            return code;
        }
    }

    class ConfigItem {

        private Key key;

        private Version version;

        private Feature feature;

        private String data;

        public ConfigItem(Key key, Version version, Feature feature, String data) {
            this.key = key;
            this.version = version;
            this.feature = feature;
            this.data = data;
        }

        public Key getKey() {
            return key;
        }

        public Version getVersion() {
            return version;
        }

        public Feature getFeature() {
            return feature;
        }

        public String getData() {
            return data;
        }
    }
}
