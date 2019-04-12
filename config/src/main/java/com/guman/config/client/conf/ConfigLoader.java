package com.guman.config.client.conf;

import com.guman.config.client.Feature;
import com.guman.config.client.store.ConfigStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author duanhaoran
 * @since 2019/4/12 8:39 PM
 */
public interface ConfigLoader<T> {


    Context vilidate(String application, String name, Feature feature);

    <T> Configuration<T> load(Context context, Generator<T> generator);


    /**
     * 配置生成器。
     *
     * @author duanhaoran
     * @since 2019/4/12 8:39 PM
     */
    interface Generator<T> {

        Configuration<T> generate(ConfigStore fileStore, Feature feature);

    }

    /**
     * 加载参数。
     *
     * @author duanhaoran
     * @since 2019/4/12 8:39 PM
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Context {

        protected String application;

        protected String name;

        protected Feature feature;
    }
}
