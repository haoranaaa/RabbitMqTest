package com.guman.common.io;

import org.springframework.util.Assert;

/**
 * @author duanhaoran
 * @since 2019/4/15 1:15 PM
 */
public class DelegateReader<K, T> implements Reader<K,T> {

    private final Reader<K, T> delegate;

    public DelegateReader(Reader<K, T> delegate) {
        Assert.notNull(delegate ,"输入不能为null!");
        this.delegate = delegate;
    }

    public Reader<K, T> getDelegate() {
        return delegate;
    }

    @Override
    public T getTarget() {
        return getDelegate().getTarget();
    }

    @Override
    public String getString(K name) {
        return getDelegate().getString(name);
    }

    @Override
    public String getString(K name, String defValue) {
        return getDelegate().getString(name, defValue);
    }

    @Override
    public String getString(K name, String defValue, boolean checkContains) {
        return getDelegate().getString(name, defValue, checkContains);
    }

    @Override
    public Integer getInteger(K name) {
        return getDelegate().getInteger(name);
    }

    @Override
    public Integer getInteger(K name, Integer defValue) {
        return getDelegate().getInteger(name, defValue);
    }

    @Override
    public Integer getInteger(K name, Integer defValue, boolean checkContainsKey) {
        return getDelegate().getInteger(name, defValue, checkContainsKey);
    }

    @Override
    public Long getLong(K name) {
        return getDelegate().getLong(name);
    }

    @Override
    public Long getLong(K name, Long defValue) {
        return getDelegate().getLong(name, defValue);
    }

    @Override
    public Long getLong(K name, Long defValue, boolean checkContainsKey) {
        return getDelegate().getLong(name, defValue,checkContainsKey);
    }
    @Override
    public Float getFloat(K name) {
        return getDelegate().getFloat(name);
    }

    @Override
    public Float getFloat(K name, Float defValue) {
        return getDelegate().getFloat(name, defValue);
    }

    @Override
    public Float getFloat(K name, Float defValue, boolean checkContainsKey) {
        return getDelegate().getFloat(name, defValue, checkContainsKey);
    }

    @Override
    public Double getDouble(K name) {
        return getDelegate().getDouble(name);
    }

    @Override
    public Double getDouble(K name, Double defValue) {
        return getDelegate().getDouble(name, defValue);
    }

    @Override
    public Double getDouble(K name, Double defValue, boolean checkContainsKey) {
        return getDelegate().getDouble(name, defValue, checkContainsKey);
    }

    @Override
    public Boolean getBoolean(K name) {
        return getDelegate().getBoolean(name);
    }

    @Override
    public Boolean getBoolean(K name, Boolean defValue) {
        return getDelegate().getBoolean(name, defValue);
    }

    @Override
    public Boolean getBoolean(K name, Boolean defValue, boolean checkContainsKey) {
        return getDelegate().getBoolean(name, defValue, checkContainsKey);
    }

    @Override
    public Byte getByte(K name) {
        return getDelegate().getByte(name);
    }

    @Override
    public Byte getByte(K name, Byte defValue) {
        return getDelegate().getByte(name, defValue);
    }

    @Override
    public Byte getByte(K name, Byte defValue, boolean checkContainsKey) {
        return getDelegate().getByte(name, defValue, checkContainsKey);
    }

    @Override
    public Short getShort(K name) {
        return getDelegate().getShort(name);
    }

    @Override
    public Short getShort(K name, Short defValue) {
        return getDelegate().getShort(name, defValue);
    }

    @Override
    public Short getShort(K name, Short defValue, boolean checkContainsKey) {
        return getDelegate().getShort(name, defValue, checkContainsKey);
    }

    @Override
    public Character getCharacter(K name) {
        return getDelegate().getCharacter(name);
    }

    @Override
    public Character getCharacter(K name, Character defValue) {
        return getDelegate().getCharacter(name, defValue);
    }

    @Override
    public Character getCharacter(K name, Character defValue, boolean checkContainsKey) {
        return getDelegate().getCharacter(name, defValue, checkContainsKey);
    }

    @Override
    public <E extends Enum<E>> E getEnum(K name, Class<E> clazz) {
        return getDelegate().getEnum(name, clazz);
    }

    @Override
    public <E extends Enum<E>> E getEnum(K name, E defValue, Class<E> clazz) {
        return getDelegate().getEnum(name, defValue, clazz);
    }

    @Override
    public <E extends Enum<E>> E getEnum(K name, E defValue, boolean checkContainsKey, Class<E> clazz) {
        return getDelegate().getEnum(name, defValue, checkContainsKey, clazz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DelegateReader)) return false;

        DelegateReader<?, ?> that = (DelegateReader<?, ?>) o;

        return delegate.equals(that.delegate);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}