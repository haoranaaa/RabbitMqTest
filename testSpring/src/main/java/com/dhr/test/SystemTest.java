package com.dhr.test;

import com.google.common.collect.Maps;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author duanhaoran
 * @since 2019/4/1 11:50 AM
 */
public class SystemTest
{

    public static void main(String[] args) {
        try {
            Resource classPathResource = new ClassPathResource("test.properties");
            Map<String, String> stringStringMap = asMap(classPathResource);
            System.out.println(stringStringMap);
        } catch (Exception e) {
            //ignore
            System.out.println("file not found !");
        }
        System.out.println(System.getProperties().getProperty("test2"));
    }

    public static Map<String, String> asMap(Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        try {
            return mapFromInputStream(inputStream);
        }
        finally {
            inputStream.close();
        }
    }

    private static Map<String, String> mapFromInputStream(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        return Maps.fromProperties(properties);
    }
}
