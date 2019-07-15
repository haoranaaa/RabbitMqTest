package com.dhr.compute;

import com.google.common.collect.Maps;
import com.guman.common.json.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/5/21 5:59 PM
 */
public class NewCaclute {

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        for (int i = 0; i < 100000; i++) {
            map.put(i + 1 + "", String.valueOf(i));
        }
        NewCaclute newCaclute = new NewCaclute();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                long timeMillis = System.currentTimeMillis();
                for (int j = 0; j < 10; j++) {
                    newCaclute.serilize(map);
                }
                System.out.println(System.currentTimeMillis() - timeMillis);
            }).start();
        }
    }

    public void serilize(Map<String, String> map) {
        String s = JSON.writeValueAsString(map);
    }
}
