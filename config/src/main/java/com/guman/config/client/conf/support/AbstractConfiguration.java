package com.guman.config.client.conf.support;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.guman.config.client.conf.ConfigLoader;
import com.guman.config.client.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author duanhaoran
 * @since 2019/4/12 8:42 PM
 */
abstract class AbstractConfiguration<T> implements Configuration<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final CopyOnWriteArrayList listeners = Lists.newCopyOnWriteArrayList();

    protected final InitFuture future = new InitFuture();

    protected final AtomicReference<T> current = new AtomicReference<>(null);

    protected ConfigLoader.Context context;


    @Override
    public ListenableFuture<Boolean> init() {
        return future;
    }

    @Override
    public void addListener(ConfigListener<T> listener) {
        synchronized (current) {
            if (future.isDone() && future.isSuccess()){
                trigger(listener, current.get());
            }
            listeners.add(listener);
        }
    }

    private void trigger(ConfigListener<T> listener, T data){
        try {
            listener.onLoad(context.getApplication(), context.getName(), data);
        }catch (Throwable e){
            logger.error("配置文件变更触发异常！data:{}",data,e);
        }

    }



    private static class InitFuture extends AbstractFuture<Boolean> {
        private Throwable throwable;

        public void set(boolean value) {
            super.set(value);
        }

        public boolean setException(Throwable throwable) {
            this.throwable = throwable;
            return super.setException(throwable);
        }

        public boolean isSuccess() {
            return throwable == null;
        }
    }
}
