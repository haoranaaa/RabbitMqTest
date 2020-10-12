package com.dhr.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/8/16 5:26 PM
 */
public class AAATest {

    private static ConcurrentHashMap<Integer, Process> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0, 10).boxed().map((i) -> CompletableFuture.runAsync(() -> {
            try {
                Process pwd = Runtime.getRuntime().exec("tail -f /opt/log/officialserver/wb_service_stdout.log");
                InputStream inputStream = pwd.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(reader);
                String s = br.readLine();
//            while (StringUtils.hasText(s)) {
//                System.out.println(s);
//                s = br.readLine();
//            }
//            new Thread(()->{
//                try {
//                    Thread.sleep(2000);
//                    Process process = map.get(1);
//                    if (Objects.nonNull(process)) {
//                        System.out.println("子线程kill子进程！");
//                        process.destroy();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
                map.put(1, pwd);
                Class<?> clazz = Class.forName("java.lang.UNIXProcess");
                Field field = clazz.getDeclaredField("pid");
                field.setAccessible(true);
                Integer pid = (Integer) field.get(pwd);
                System.out.println(pid);
            } catch (IOException | ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                System.out.println(e.getMessage());
            }
        })).collect(Collectors.toList());
        Thread.sleep(3000 * 20);

    }
}
