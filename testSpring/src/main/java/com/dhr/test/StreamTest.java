package com.dhr.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.guman.common.json.JSON;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author haoran.duan
 * @since 28 十一月 2018
 */
public class StreamTest {

    public static void main(String[] args) {
        LoadingCache<String, Set<String>> cache = CacheBuilder.newBuilder()
                .maximumSize(1)
                .expireAfterWrite(10 ,TimeUnit.MINUTES)
                .build(new CacheLoader<String, Set<String>>() {
                    @Override
                    public Set<String> load(String key) throws Exception {
                        HashSet<String> strings = Sets.newHashSet("1", "2", "3");
                        System.out.println("get a b c");
                        return strings;
                    }
                });
        getObj();
        try {
            Set<String> a = cache.get("a");
            System.out.println(a.contains("1"));

            Set<String> b = cache.get("b");
            System.out.println(b.contains("2"));

            Set<String> c = cache.get("c");
            System.out.println(c.contains("2"));

            Set<String> d = cache.get("a");
            System.out.println(d.contains("1"));
            ArrayList<Integer> objects = Lists.newArrayList();
            objects.add(3);
            objects.add(4);
            objects.add(1);
            objects.add(2);
            List<Integer> collect = objects.stream().sorted(Comparator.comparing(Integer::longValue)).collect(Collectors.toList());
            //System.out.println(JSON.writeValueAsString(collect));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        HashSet<String> strings = Sets.newHashSet("1", "2");
        //System.out.println(strings.contains(null));
    }

    public static Object getObj() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("done");
                return 1;
            } catch (InterruptedException e) {
                return 0;
            }
        });
        try {
            integerCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("no done");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> getList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return result;
    }
}
