package com.douban.query;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.StopWatch;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author haoran.duan
 * @since 19 九月 2018
 */
public class Test3 {

    private static final CharMatcher CHAR_MATCHER = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.digit()).or(CharMatcher.anyOf("_-.")).negate();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        List<Object> collect = IntStream.range(1, 10).boxed().parallel().map(i -> {
            try {
                Thread.sleep(i * 500);
            } catch (InterruptedException e) {


            }
            return null;
        }).collect(Collectors.toList());
        sw.stop();
        System.out.println(sw.getTime());

        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        sw.reset();
        sw.start();
        List<CompletableFuture<Void>> collect1 = list.stream().map(i -> CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(i * 500);
            } catch (InterruptedException e) {
            }
        })).collect(Collectors.toList());
        collect1.stream().map(CompletableFuture::join).collect(Collectors.toList());
        sw.stop();
        System.out.println(sw.getTime());
    }
}
