package com.guman.config.client.conf;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author duanhaoran
 * @since 2019/4/12 8:38 PM
 */
public interface Configuration<T> {

    ListenableFuture<Boolean> init();

    void addListener(ConfigListener<T> listener);

    void removeListener(ConfigListener<T> listener);

    interface ConfigListener<T> {

        void onLoad(String application, String name, T value);
    }

    interface Parser<T> {
        T parse(String application, String name, String data) throws Exception;
    }

    /**
     * 配置监听器。默认第一次添加时，如果已经加载完毕，会马上出发通知，通过cas跳过第一次加载。
     *
     * @author duanhaoran
     * @since 2019/4/12 8:38 PM
     */
    abstract class SkipFirstConfigListener<T> implements ConfigListener<T> {

        private AtomicBoolean init = new AtomicBoolean(false);

        @Override
        public void onLoad(String application, String name, T value) {
            if (!init.compareAndSet(false, true)) {
                doLoad(application, name, value);
            }
        }

        protected abstract void doLoad(String application, String name, T value);
    }
}
