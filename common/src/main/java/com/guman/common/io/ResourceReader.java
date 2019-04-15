package com.guman.common.io;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.guman.common.Parameters;
import com.guman.common.util.Resources;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/4/15 1:20 PM
 */
public class ResourceReader extends DelegateReader<String, Map<String, String>> implements Parameters {


    public ResourceReader(Reader<String, Map<String, String>> delegate) {
        super(delegate);
    }

    public ResourceReader(Resource resource, boolean ignoreResourceNotFound) {
        super(from(resource, ignoreResourceNotFound));
    }

    private static Reader<String, Map<String, String>> from(Resource resource, boolean ignoreResourceNotFound) {
        Map<String, String> configuraiton;
        try {
            configuraiton = Maps.newHashMap(Resources.asMap(resource));
        } catch (IOException e) {
            if (!ignoreResourceNotFound) {
                String message = String.format("Failed to load the properties file %s. Please check if the file " +
                        "exists!", resource);
                RuntimeException to = new RuntimeException(message, e);
                LoggerFactory.getLogger(ResourceReader.class).error(e.getMessage(), to);
                throw to;
            }
            else {
                configuraiton = Maps.newHashMap();
            }
        }
        return MapReader.from(configuraiton);
    }

    @Override
    public String getParameter(String key) {
        return getString(key);
    }

    @Override
    public String getParameter(String key, String defaultValue) {
        return getString(key, defaultValue);
    }

    @Override
    public Map<String, String> getParameters() {
        return ImmutableMap.copyOf(getTarget());
    }
}
