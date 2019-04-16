package com.guman.common.json.helper;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

/**
 * @author duanhaoran
 * @since 2019/4/16 10:50 AM
 */
public class BizDateStdModule extends SimpleModule {
    public BizDateStdModule() {
        super("BizDateStdModule", Version.unknownVersion());
        this.addDeserializer(Date.class, new DateJsonDeserializer());
        this.addSerializer(Date.class, new DateJsonSerializer());
    }
}