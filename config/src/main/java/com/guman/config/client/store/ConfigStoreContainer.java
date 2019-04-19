package com.guman.config.client.store;

import com.google.common.util.concurrent.ListenableFuture;
import com.guman.common.pojo.Status;
import com.guman.config.client.ConfigEnv;
import com.guman.config.client.conf.support.UpdateConfig;
import com.guman.config.client.store.ConfigStore;
import lombok.Data;

import java.util.List;

/**
 * @author duanhaoran
 * @since 2019/4/15 10:58 AM
 */
public interface ConfigStoreContainer {

    void setup(ConfigEnv env);

    void reloading();

    ConfigStore getOrAdd(String application, String name);

    void pull(String application, String name, long version, String profile);

    ListenableFuture<String> view(ViewConfigItem item);

    ListenableFuture<Status> update(UpdateConfigItem item);

    ListenableFuture<Status> batchUpdate(List<UpdateConfigItem> items);


    @Data
    class ViewConfigItem {

        private String application;

        private String name;

        private Long version;

        private String profile;
    }


    @Data
    class UpdateConfigItem {

        private String application;

        private String name;

        private UpdateConfig.UpdateFeature feature;

        private String data;

        private Long version;

        private String profile;
    }
}
