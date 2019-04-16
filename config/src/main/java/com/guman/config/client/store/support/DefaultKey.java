package com.guman.config.client.store.support;

import com.guman.config.client.ConfigEnv;
import com.guman.config.client.store.ConfigStore.Key;

/**
 * @author duanhaoran
 * @since 2019/4/15 8:19 PM
 */

public class DefaultKey implements Key {

    String application;

    String name;

    ConfigEnv env;

    public DefaultKey(String application, String name, ConfigEnv env) {
        this.application = application;
        this.name = name;
        this.env = env;
    }

    @Override
    public String getApplication() {
        return application;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ConfigEnv getEnv() {
        return env;
    }
}
