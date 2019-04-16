package com.guman.common.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.guman.common.json.joda.JacksonDateFormat;

import java.io.*;
import java.net.URL;

/**
 * @author duanhaoran
 * @since 2019/4/15 8:13 PM
 */
public class JSON {

    private static volatile JsonMapper jsonMapper = new JsonMapper(false, JacksonDateFormat.PATTERN_YYYYMMDDHHMMSS);

    public static JsonMapper getJsonMapper() {
        return jsonMapper;
    }

    public static void setJsonMapper(JsonMapper jsonMapper) {
        JSON.jsonMapper = jsonMapper;
    }

    public static <T> T readValue(File src, Class<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(File src, TypeReference<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(File src, JavaType valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(URL src, Class<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(URL src, TypeReference<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(URL src, JavaType valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(String content, Class<T> valueType) throws JsonException {
        return jsonMapper.readValue(content, valueType);
    }

    public static <T> T readValue(String content, TypeReference<T> valueType) throws JsonException {
        return jsonMapper.readValue(content, valueType);
    }

    public static <T> T readValue(String content, JavaType valueType) throws JsonException {
        return jsonMapper.readValue(content, valueType);
    }

    public static <T> T readValue(Reader src, Class<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(Reader src, TypeReference<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(Reader src, JavaType valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(InputStream src, Class<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(InputStream src, TypeReference<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(InputStream src, JavaType valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(byte[] src, Class<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(byte[] src, int offset, int len, Class<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, offset, len, valueType);
    }

    public static <T> T readValue(byte[] src, TypeReference<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static <T> T readValue(byte[] src, int offset, int len, TypeReference<T> valueType) throws JsonException {
        return jsonMapper.readValue(src, offset, len, valueType);
    }

    public static <T> T readValue(byte[] src, JavaType valueType) throws JsonException {
        return jsonMapper.readValue(src, valueType);
    }

    public static JsonReader reader() {
        return jsonMapper.reader();
    }

    public static String writeValueAsString(Object object) throws JsonException {
        return jsonMapper.writeValueAsString(object);
    }

    public static byte[] writeValueAsBytes(Object object) throws JsonException {
        return jsonMapper.writeValueAsBytes(object);
    }

    public static void writeValue(File file, Object object) throws JsonException {
        jsonMapper.writeValue(file, object);
    }

    public static void writeValue(OutputStream out, Object object) throws JsonException {
        jsonMapper.writeValue(out, object);
    }

    public static void writeValue(Writer writer, Object object) throws JsonException {
        jsonMapper.writeValue(writer, object);
    }

    public static JsonWriter writer() {
        return jsonMapper.writer();
    }

    public static <T> T readValue(byte[] src, int offset, int len, JavaType valueType) throws JsonException {
        return jsonMapper.readValue(src, offset, len, valueType);
    }
}
