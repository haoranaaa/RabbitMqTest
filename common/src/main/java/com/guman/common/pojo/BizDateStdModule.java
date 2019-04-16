package com.guman.common.pojo;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

/**
 * @author duanhaoran
 * @since 2019/4/15 8:12 PM
 */
public class BizDateStdModule extends SimpleModule {
    public BizDateStdModule() {
        super("BizDateStdModule", Version.unknownVersion());
        /*this.addDeserializer(Date.class, new DateJsonDeserializer());
        this.addSerializer(Date.class, new DateJsonSerializer());*/
    }
}
