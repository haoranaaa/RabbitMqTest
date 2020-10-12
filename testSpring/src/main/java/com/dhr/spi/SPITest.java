package com.dhr.spi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.guman.common.json.JSON;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author duanhaoran
 * @since 2019/4/13 2:31 PM
 */
public class SPITest {

    private InterfaceSpiTestService spiOneTestProcessor;

    public static void main(String[] args) throws IOException {
        Iterator<InterfaceSpiTestService> iterator = ServiceLoader.load(InterfaceSpiTestService.class).iterator();
        while (iterator.hasNext()){
            InterfaceSpiTestService next = iterator.next();
            next.testPrint();
        }
    }
}
