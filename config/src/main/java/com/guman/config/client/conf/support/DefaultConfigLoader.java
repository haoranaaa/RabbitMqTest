package com.guman.config.client.conf.support;

import com.google.auto.service.AutoService;
import com.google.common.util.concurrent.ListenableFuture;
import com.guman.common.pojo.Status;
import com.guman.config.client.ConfigEnv;
import com.guman.config.client.Feature;
import com.guman.config.client.conf.ConfigLoader;
import com.guman.config.client.store.ConfigStoreContainer;
import com.guman.config.client.conf.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


/**
 * @author duanhaoran
 * @since 2019/4/13 12:02 PM
 */
@AutoService(ConfigLoader.class)
public class DefaultConfigLoader implements ConfigLoader {

    private final ConfigEnv env;

    private final static String DEFAULT_APPLICATION = "default";

    @Resource
    private ConfigStoreContainer container;

    public DefaultConfigLoader() {
        this.env = new ConfigEnv();
    }

    @Override
    public Context vilidate(String application, String name, Feature feature) {
        Context context = new Context(application, name, feature);

        ContextWrapper wrapper = new ContextWrapper(context);

        wrapper.vilidate();

        return new Context(wrapper.getApplication(), wrapper.getName(), wrapper.getFeature());
    }

    @Override
    public ListenableFuture<Status> update(Context context, long version, String profile, String data) {
        return null;
    }

    @Override
    public ListenableFuture<String> view(Context context, Long version, String profile) {
        return null;
    }

    @Override
    public Configuration load(Context context, Generator generator) {
        Context target = vilidate(context.getApplication(), context.getName(), context.getFeature());
        //ConfigStore store =
        return null;
    }

    class ContextWrapper {
        private static final int MIN_LENGTH = 1;

        private static final int MAX_LENGTH = 100;

        private final Context context;

        ContextWrapper(Context context) {
            this.context = context;
        }

        void vilidate() {
            String name = context.getName();
            if (!StringUtils.hasText(name)) {
                throw new IllegalArgumentException("name属性必须提供！");
            }
            if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
                throw new IllegalArgumentException("name[" + name + "] 长度必须在 " + MIN_LENGTH + "到 " + MAX_LENGTH + "之间！");
            }
            for (char c : name.toCharArray()) {
                if (c >= 'a' && c <= 'z')
                    continue;
                if (c >= '0' && c <= '9')
                    continue;
                switch (c) {
                    case '-':
                    case '_':
                    case '.':
                        continue;
                    default:
                        throw new IllegalArgumentException("name[" + name + "] 不能包含特殊符号. 请使用小写字母和[-_.]");
                }
            }
        }

        String getApplication() {
            String application = context.getApplication();
            if (application == null) {
                application = DEFAULT_APPLICATION;
            }
            return application;
        }

        String getName() {
            return context.getName();
        }

        Feature getFeature() {
            return context.getFeature();
        }
    }
}
