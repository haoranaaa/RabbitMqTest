package com.dhr.test;

import com.google.common.collect.Maps;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author haoran.duan
 * @since 02 十二月 2018
 */
public class StreamTest2 {

    final Integer x = 0;

    private void testStream(){
        Map<Abean,Integer> map= Maps.newConcurrentMap();
        for (int i = 0; i < 10; i++) {
            Abean a=new Abean();
            a.setX(i+"");
            a.setY(2*i+"");
            a.setDate(new DateTime().withTimeAtStartOfDay().toDate());
            map.put(a,i);
        }
        for (int i = 0; i < 10; i++) {
            Abean a=new Abean();
            a.setX(i+"");
            a.setY(2*i+"");
            a.setDate(new DateTime().withTimeAtStartOfDay().toDate());
            System.out.println(map.get(a));
        }

    }

    public static void main(String[] args) {
        StreamTest2 st2=new StreamTest2();

                st2.testStream();

    }
}
@Data
class Abean{
    private String x;

    private String y;

    private Date date;
}
