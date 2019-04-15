package com.guman.common.io;

/**
 * @author duanhaoran
 * @since 2019/4/15 1:14 PM
 */
public interface Reader<K, T> {
    T getTarget();

    String getString(K name);

    String getString(K name, String defValue);

    String getString(K name, String defValue, boolean checkContains);

    Integer getInteger(K name);

    Integer getInteger(K name, Integer defValue);

    Integer getInteger(K name, Integer defValue, boolean checkContainsKey);

    Long getLong(K name);

    Long getLong(K name, Long defValue);

    Long getLong(K name, Long defValue, boolean checkContainsKey);

    Float getFloat(K name);

    Float getFloat(K name, Float defValue);

    Float getFloat(K name, Float defValue, boolean checkContainsKey);

    Double getDouble(K name);

    Double getDouble(K name, Double defValue);

    Double getDouble(K name, Double defValue, boolean checkContainsKey);

    Boolean getBoolean(K name);

    Boolean getBoolean(K name, Boolean defValue);

    Boolean getBoolean(K name, Boolean defValue, boolean checkContainsKey);

    Byte getByte(K name);

    Byte getByte(K name, Byte defValue);

    Byte getByte(K name, Byte defValue, boolean checkContainsKey);

    Short getShort(K name);

    Short getShort(K name, Short defValue);

    Short getShort(K name, Short defValue, boolean checkContainsKey);

    Character getCharacter(K name);

    Character getCharacter(K name, Character defValue);

    Character getCharacter(K name, Character defValue, boolean checkContainsKey);

    <E extends Enum<E>> E getEnum(K name, Class<E> clazz);

    <E extends Enum<E>> E getEnum(K name, E defValue, Class<E> clazz);

    <E extends Enum<E>> E getEnum(K name, E defValue, boolean checkContainsKey, Class<E> clazz);
}
