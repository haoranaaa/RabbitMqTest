package com.dhr.collection;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.guman.common.json.JSON;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/7/25 7:51 PM
 */
public class ContainsTest {

    public static void main(String[] args) throws ExecutionException {
        for (int i = 0; i < 100; i++) {
            List<ABC> collect = IntStream.range(0, 100).boxed().collect(Collectors.toList())
                    .parallelStream().map(k -> k % 10 == 0 ? null : k%10).map(ABC::new)
                    .filter(distinctByKey(ABC::getA)).collect(Collectors.toList());
            System.out.println(JSON.writeValueAsString(collect));
        }

    }

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new HashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    static class ABC{
        public ABC(Integer a) {
            this.a = a;
        }

        private Integer a;

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }
    }

}

class AException extends RuntimeException {
    public AException(String message) {
        super(message);
    }
}

class CException extends AException {
    public CException(String message) {
        super(message);
    }
}
class BException extends RuntimeException {
    public BException(String message) {
        super(message);
    }
    
}