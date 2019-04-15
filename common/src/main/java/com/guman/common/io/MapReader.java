package com.guman.common.io;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/4/15 7:18 PM
 */
public class MapReader<K> implements Reader<K, Map<K,String>> {
    private final Map<K, String> target;

    protected MapReader(Map<K, String> target) {
        this.target = target == null ? Maps.newHashMap() : target;
    }

    public static <K> MapReader<K> from(Map<K, String> map) {
        return new MapReader<>(map);
    }

    @Override
    public Map<K, String> getTarget() {
        return target;
    }

    @Override
    public String getString(K name) {
        return getString(name, null, false);
    }

    @Override
    public String getString(K name, String defValue) {
        return getString(name, defValue, false);
    }

    @Override
    public String getString(K name, String defValue, boolean checkContains) {
        String v = target.get(name);
        if (v == null) {
            return checkContains && target.containsKey(name) ? null : defValue;
        }
        return v;
    }

    @Override
    public Integer getInteger(K name) {
        return getInteger(name, null, false);
    }

    @Override
    public Integer getInteger(K name, Integer defValue) {
        return getInteger(name, defValue, false);
    }

    @Override
    public Integer getInteger(K name, Integer defValue, boolean checkContainsKey) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        try {
            return Integer.parseInt(v);
        }
        catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public Long getLong(K name) {
        return getLong(name, null, false);
    }

    @Override
    public Long getLong(K name, Long defValue) {
        return getLong(name, defValue, false);
    }

    @Override
    public Long getLong(K name, Long defValue, boolean checkContainsKey) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        try {
            return Long.parseLong(v);
        }
        catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public Float getFloat(K name) {
        return getFloat(name, null, false);
    }

    @Override
    public Float getFloat(K name, Float defValue) {
        return getFloat(name, defValue, false);
    }

    @Override
    public Float getFloat(K name, Float defValue, boolean checkContainsKey) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        try {
            return Float.parseFloat(v);
        }
        catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public Double getDouble(K name) {
        return getDouble(name, null, false);
    }

    @Override
    public Double getDouble(K name, Double defValue) {
        return getDouble(name, defValue, false);
    }

    @Override
    public Double getDouble(K name, Double defValue, boolean checkContainsKey) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        try {
            return Double.parseDouble(v);
        }
        catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public Boolean getBoolean(K name) {
        return getBoolean(name, null, false);
    }

    @Override
    public Boolean getBoolean(K name, Boolean defValue) {
        return getBoolean(name, defValue, false);
    }

    @Override
    public Boolean getBoolean(K name, Boolean defValue, boolean checkContainsKey) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        try {
            return Boolean.parseBoolean(v);
        }
        catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public Byte getByte(K name) {
        return getByte(name, null, false);
    }

    @Override
    public Byte getByte(K name, Byte defValue) {
        return getByte(name, defValue, false);
    }

    @Override
    public Byte getByte(K name, Byte defValue, boolean checkContainsKey) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        try {
            return Byte.parseByte(v);
        }
        catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public Short getShort(K name) {
        return getShort(name, null, false);
    }

    @Override
    public Short getShort(K name, Short defValue) {
        return getShort(name, defValue, false);
    }

    @Override
    public Short getShort(K name, Short defValue, boolean checkContainsKey) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        try {
            return Short.parseShort(v);
        }
        catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public Character getCharacter(K name) {
        return getCharacter(name, null, false);
    }

    @Override
    public Character getCharacter(K name, Character defValue) {
        return getCharacter(name, defValue, false);
    }

    @Override
    public Character getCharacter(K name, Character defValue, boolean checkContainsKey) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        return v.charAt(0);
    }

    @Override
    public <E extends Enum<E>> E getEnum(K name, Class<E> clazz) {
        return getEnum(name, null, false, clazz);
    }

    @Override
    public <E extends Enum<E>> E getEnum(K name, E defValue, Class<E> clazz) {
        return getEnum(name, defValue, false, clazz);
    }

    @Override
    public <E extends Enum<E>> E getEnum(K name, E defValue, boolean checkContainsKey, Class<E> clazz) {
        String v = target.get(name);
        if (v == null) {
            return checkContainsKey && target.containsKey(name) ? null : defValue;
        }
        E[] codeEnums = clazz.getEnumConstants();
        for (E codeEnum : codeEnums) {
            if (codeEnum.name().equals(v)) {
                return codeEnum;
            }
        }
        return defValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapReader)) return false;

        MapReader<?> mapReader = (MapReader<?>) o;

        return target.equals(mapReader.target);
    }

    @Override
    public int hashCode() {
        return target.hashCode();
    }

    @Override
    public String toString() {
        return target.toString();
    }
}
