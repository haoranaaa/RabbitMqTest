package com.guman.config.client.store.support;

import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.CheckedFuture;
import com.guman.config.client.store.ConfigService;
import com.guman.config.client.store.ConfigStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author duanhaoran
 * @since 2019/4/16 7:32 PM
 */
public class DefaultConfigStore implements ConfigStore {

    private final Key key;

    private final ConfigService configService;

    private final DefaultConfigStoreService storeService;

    private final FileStatus fileStatus = new FileStatus(null);

    private final EventChannelBus<String> bus = new EventChannelBus<>();

    private static Logger logger = LoggerFactory.getLogger(DefaultConfigStore.class);
    
    private volatile long lastCheck = -1;

    public DefaultConfigStore(Key key, ConfigService configService, DefaultConfigStoreService storeService) {
        this.key = key;
        this.configService = configService;
        this.storeService = storeService;
    }

    @Override

    public Key getKey() {
        return key;
    }

    @Override
    public ChannelBus<CheckedFuture<String, Exception>> bus() {
        return bus;
    }

    @Override
    public boolean isLoaded() {
        return fileStatus.version() != null;
    }

    @Override
    public Version initLoad(Version version) {
        if (version != DefaultConfigStoreContainer.ABSENT) {
            try {
                setVersion(version);
            } catch (IOException e) {
                logger.warn("尝试初始化失败！key:{},version:{}",key,version,e);
            }
        }
        return null;
    }

    @Override
    public void setVersion(Version version) throws IOException {

    }

    @Override
    public void checkOverride() {

    }

    public static class FileStatus {

        private final AtomicReference<FileVersion> current;

        public FileStatus(AtomicReference<FileVersion> current) {
            this.current = current;
        }

        private boolean update(FileVersion target) {
            FileVersion current = this.current.get();
            boolean needUpdate;
            if (current == null) {
                needUpdate = true;
            } else {
                needUpdate = current.needUpdate(target);
            }
            return needUpdate && this.current.compareAndSet(current, target);
        }

        private boolean updateOverrideToRemote() {
            FileVersion FileVersion = this.current.get();

            if (FileVersion != null && FileVersion.type == DefaultConfigStore.FileVersion.Type.override) {
                this.current.compareAndSet(FileVersion, new FileVersion(DefaultConfigStore.FileVersion.Type.remote, DefaultConfigStoreContainer.ABSENT));
                return true;
            }
            return false;
        }

        private FileVersion version() {
            return current.get();
        }
    }


    public static class FileVersion {
        private final Type type;

        private final Version version;

        FileVersion(Type type, Version version) {
            this.type = type;
            this.version = version;
        }

        private boolean needUpdate(FileVersion target) {
            if (type == Type.override) {
                return target.type == Type.override && target.version.getVersion() > version.getVersion();
            }
            if (type == Type.remote) {
                return true;
            }
            return DefaultVersion.needUpdate(version, target.version);
        }
        
        enum Type {
            override, remote
        }
    }

    private class EventChannelBus<T> implements ChannelBus<CheckedFuture<T, Exception>> {

        private final EventBus eventBus = new EventBus();

        private volatile Snapshot<CheckedFuture<T, Exception>> current;

        @Override
        public ChannelHandler<CheckedFuture<T, Exception>> register(Channel<CheckedFuture<T, Exception>> channel) {

            ChannelAdapter<T> channelAdapter = new ChannelAdapter<>(channel);
            synchronized (eventBus) {
                Snapshot<CheckedFuture<T, Exception>> snapshot = this.current;
                if (current != null) {
                    channel.onReceive(snapshot);
                }
                eventBus.register(channelAdapter);
            }
            return new ChannelHandler<CheckedFuture<T, Exception>>() {
                @Override
                public void unregister() {
                    eventBus.unregister(channelAdapter);
                }

                @Override
                public Channel<CheckedFuture<T, Exception>> channel() {
                    return channel;
                }
            };
        }

        @Override
        public void post(Snapshot<CheckedFuture<T, Exception>> value) {
            synchronized (eventBus) {
                current = value;
                eventBus.post(current);
            }

        }
    }

    private class ChannelAdapter<T> implements Channel<CheckedFuture<T, Exception>> {
        private final Channel<CheckedFuture<T, Exception>> channel;

        private ChannelAdapter(Channel<CheckedFuture<T, Exception>> channel) {
            this.channel = channel;
        }

        @Override
        public void onReceive(Snapshot<CheckedFuture<T, Exception>> snapshot) {
            channel.onReceive(snapshot);
        }
    }

}
