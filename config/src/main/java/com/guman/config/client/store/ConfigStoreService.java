package com.guman.config.client.store;

import java.io.IOException;
import java.util.Map;

import com.guman.config.client.ConfigEnv;
import com.guman.config.client.store.ConfigStore.*;

/**
 * @author duanhaoran
 * @since 2019/4/16 7:35 PM
 */
public interface ConfigStoreService {

    Map<Key, Version> getReference(ConfigEnv env);

    boolean hasSnapshot(Key key, Version version);

    ConfigStore create(Key key);

    void storeSnapshot(Key key, Snapshot<String> snapshot) throws IOException;
}
