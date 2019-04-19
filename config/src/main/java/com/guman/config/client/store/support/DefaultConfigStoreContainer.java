package com.guman.config.client.store.support;

import com.google.common.util.concurrent.ListenableFuture;
import com.guman.common.pojo.Status;
import com.guman.config.client.ConfigEnv;
import com.guman.config.client.store.ConfigStoreContainer;
import com.guman.config.client.store.ConfigStore;
import com.guman.config.client.store.ConfigStore.Version;

import java.util.List;

/**
 * @author duanhaoran
 * @since 2019/4/15 10:58 AM
 */
public class DefaultConfigStoreContainer implements ConfigStoreContainer {

    public static Version ABSENT;

    @Override
    public void setup(ConfigEnv env) {

    }

    @Override
    public void reloading() {

    }

    @Override
    public ConfigStore getOrAdd(String application, String name) {
        return null;
    }

    @Override
    public void pull(String application, String name, long version, String profile) {

    }

    @Override
    public ListenableFuture<String> view(ViewConfigItem item) {
        return null;
    }

    @Override
    public ListenableFuture<Status> update(UpdateConfigItem item) {
        return null;
    }

    @Override
    public ListenableFuture<Status> batchUpdate(List<UpdateConfigItem> items) {
        return null;
    }
}
