package com.guman.common.util;

import com.google.common.collect.Maps;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author duanhaoran
 * @since 2019/4/15 1:26 PM
 */
public class Resources {

    public static Map<String, String> asMap(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            return mapFromInputStream(inputStream);
        }
    }

    public static Map<String, String> mapFromInputStream(InputStream stream) throws IOException {
        Properties properties = new Properties();
        properties.load(stream);
        return Maps.fromProperties(properties);
    }


}
