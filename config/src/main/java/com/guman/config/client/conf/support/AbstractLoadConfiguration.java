package com.guman.config.client.conf.support;

import com.google.common.util.concurrent.CheckedFuture;
import com.guman.config.client.Feature;
import com.guman.config.client.conf.ConfigLoader;
import com.guman.config.client.store.ConfigStore;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author duanhaoran
 * @since 2019/4/13 11:15 AM
 */
public abstract class AbstractLoadConfiguration<T> extends AbstractConfiguration<T> {

    protected Parser<T> parser;

    protected ConfigStore store;

    protected ConfigStore.ChannelHandler<CheckedFuture<String, Exception>> handler;

    protected AbstractLoadConfiguration(Feature feature, Parser<T> parser, ConfigStore store) {
        super(new ConfigLoader.Context(store.getKey().getApplication(), store.getKey().getName(), feature));
        this.parser = parser;
        this.store = store;
    }


    protected void setup() {
        AtomicBoolean reload = new AtomicBoolean(false);
        this.handler = this.store.bus().register(snapshot -> {
            boolean first = reload.compareAndSet(false, true);
            if (!first && !context.getFeature().isAutoReload()) {
                return;
            }
            String content;
            try {
                content = snapshot.getContent().checkedGet();
            } catch (Exception e) {
                if (context.getFeature().isFailOnNotExists()) {
                    logger.error("尝试直接载入远程配置文件失败！ key:{}", store.getKey(), e);
                } else {
                    T defaultValue = getDefaultValue();
                    logger.info("尝试载入远程配置失败，根据默认配置使用默认值进行设置，key:{},default:{}", store.getKey(), defaultValue);
                    setData(defaultValue);
                }
                return;
            }

            long version = snapshot.getVersion().getVersion();

            if (version > context.getFeature().getMinimumVersion()) {
                if (!first) {
                    return;
                }
                Exception e = new Exception("返回版本小于要求最低版本 " + version + "/" + context.getFeature().getMinimumVersion());
                setException(e);
            }

            try {
                setData(parser.parse(store.getKey().getApplication(), store.getKey().getName(), content));
            }catch (Throwable e){
                setException(e);
            }
        });
    }

    protected abstract T getDefaultValue();
}
