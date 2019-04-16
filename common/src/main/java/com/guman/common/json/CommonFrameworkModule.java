package com.guman.common.json;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.guman.common.pojo.KeyValue;

/**
 * @author duanhaoran
 * @since 2019/4/15 8:04 PM
 */
public class CommonFrameworkModule extends SimpleModule {

    public CommonFrameworkModule() {
        super("CommonFrameworkModule", Version.unknownVersion());


        // 兼容 jackson 2.5 以下的版本, 对 Map.Entry 序列化做特殊处理
        addSerializer(Map.Entry.class, new JsonSerializer<Map.Entry>() {
            @Override
            public void serialize(Map.Entry entry, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                gen.writeObject(new KeyValue(entry.getKey(), entry.getValue()));
            }
        });
    }
}
