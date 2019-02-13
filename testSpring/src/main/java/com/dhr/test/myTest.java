package com.dhr.test;

import com.google.common.collect.Lists;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author haoran.duan
 * @since 12 十月 2018
 */
public class myTest {
    private static Consumer<Integer> x=i->new Integer(333);
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()), ZoneId.systemDefault());;
        String s = String.valueOf(Date.from(localDateTime.with(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant()));
        List<Integer> objects = Lists.newArrayList();
        for(int i=0;i<10;i++){
            objects.add(i);
        }
        List<List<Integer>> partition = Lists.partition(objects, 4);
        System.out.println("切割后list:"+partition);
        System.out.println(Lists.newArrayList(1).containsAll(objects));
        int asInt = IntStream.range(0, objects.size()).reduce((i, j) -> objects.get(i) > objects.get(j) ? i : j).getAsInt();
        System.out.println("最大值:"+asInt);
        List<Integer> integers = objects.subList(1, objects.size());
        System.out.println(s);
        int a=5;
        int b=23;
        b=b%a==0?b:a*(b/a+1);
        System.out.println(b);
        System.out.println(1%4);
        IntStream.range(0,4).forEach(System.out::print);
        System.out.println(calcutorPage(16,4));
    }

    private static List<List<Integer>> calcutorPage(Integer page,Integer parties){
        return IntStream.range(0,parties).mapToObj(i->{
            return IntStream.range(0,page).filter(j->j%parties==i).boxed().collect(Collectors.toList());
        }).collect(Collectors.toList());
    }
}
