package com.guman.config.client.conf.support;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.guman.common.pojo.CodeEnum;
import com.guman.common.pojo.Status;
import com.guman.config.client.Feature;
import com.guman.config.client.conf.ConfigLoader;
import com.guman.config.client.store.ConfigService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.ServiceLoader;

import static com.guman.config.client.store.ConfigService.ConfigOperatorType.UPDATE_REMOTE_ERROR;

/**
 * @author duanhaoran
 * @since 2019/4/15 11:14 AM
 */
public class UpdateConfig extends AbstractConfiguration<Status> {

    private static ConfigLoader loader = null;

    public UpdateConfig(ConfigLoader.Context context) {
        super(context);
    }

    static {
        //默认获取第一个实现，具体情况应该重写一个spi获取类 后期补上根据名称获取
        for (ConfigLoader next : ServiceLoader.load(ConfigLoader.class)) {
            if (next == null) {
                throw new IllegalArgumentException("Service type == null");
            }
            loader = next;
            break;
        }
    }

    public static UpdateConfig get(String application, String name, UpdateFeature feature) {
        return new UpdateConfig(loader.vilidate(application, name, feature));
    }

    public static UpdateConfig get(String application, String name) {
        return get(application, name, UpdateFeature.builder().build());
    }

    public static UpdateConfig get(String name) {
        return get(null, name, UpdateFeature.builder().build());
    }


    public ListenableFuture<Status> update(String data) {
        return update(data, null, null);
    }

    public ListenableFuture<Status> update(String data, long version) {
        return update(data, version, null);
    }

    public ListenableFuture<Status> update(String data, String profile) {
        return update(data, null, profile);
    }

    public ListenableFuture<Status> update(String data, Long version, String profile) {
        Assert.notNull(data, "Data must be not null");
        ListenableFuture<Status> target = loader.update(context, version, profile, data);
        target.addListener(() -> {
            try {
                setData(target.get());
            }
            catch (Throwable e) {
                ConfigService removeService = ServiceLoader.load(ConfigService.class).iterator().next();
                removeService.record(UPDATE_REMOTE_ERROR, context.getApplication(), context.getName(), e);
                setException(e);
            }
        }, MoreExecutors.directExecutor());
        return target;
    }





    public enum FileType implements CodeEnum<FileType> {

        AppFile(1), SystemFile(2);

        private final int code;

        FileType(int code) {
            this.code = code;
        }

        @Override
        public int getCode() {
            return code;
        }

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateFeature extends Feature {

        private boolean onlyUpdate = false;

        private boolean onlyInsert = false;

        private FileType fileType = FileType.AppFile;

        private boolean publicIdentity = false;

        private boolean compareAndUpdate = false;
    }
}
