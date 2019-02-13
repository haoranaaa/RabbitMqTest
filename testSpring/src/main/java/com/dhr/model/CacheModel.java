package com.dhr.model;

/**
 *
 * @author haoran.duan
 * @since 09 一月 2019
 */
public class CacheModel<T> {

    private T data;

    private long timestamp=System.currentTimeMillis();

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
