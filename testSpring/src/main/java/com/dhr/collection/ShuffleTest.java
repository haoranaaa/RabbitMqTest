package com.dhr.collection;

import com.google.common.collect.Maps;
import com.guman.common.json.JSON;
import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/4/22 11:59 AM
 */
public class ShuffleTest {

    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
        AtomicInteger n=new AtomicInteger(0);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("第"+n.getAndIncrement()+"次执行");
        }, 1, 1, TimeUnit.SECONDS);

        HashMap<String, Object> map = Maps.newHashMap();
//        map.put("a", 1);
//        map.put("b", 2);
//        map.put("d", "3");
        String a = JSON.writeValueAsString(map);


        A a1 = JSON.readValue(a, A.class);


        System.out.println(a1);


    }


}
@Data
class A {
    private Integer a;

    private Integer b;

    private String c;
}
