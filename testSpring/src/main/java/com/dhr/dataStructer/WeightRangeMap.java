package com.dhr.dataStructer;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import com.guman.common.json.JSON;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 自适应权重map
 *
 * @author duanhaoran
 * @since 2019/11/9 3:09 PM
 */
public class WeightRangeMap<T> {

    private RangeMap<Integer, T> rangeMap;

    private AtomicInteger nextRandom = new AtomicInteger(0);

    private AtomicInteger size = new AtomicInteger(0);

    public WeightRangeMap() {
        rangeMap = TreeRangeMap.create();
    }

    public T get(Integer key) {
        return rangeMap.get(key);
    }

    /**
     * 顺序push会为生成一个自动range的方法
     * 根据传入的值 第一个的区间为 (0,key1],第二个区间为(key1,key1 + key2]
     *
     * @param key 权重值
     * @param t value
     */
    public void put(int key, T t) {
        if (key <= 0) {
            throw new RuntimeException("commodity rangeMap only access big than 0 key in .");
        }
        Preconditions.checkNotNull(t);
        rangeMap.put(Range.closedOpen(nextRandom.get(), nextRandom.addAndGet(key)), t);
        size.getAndIncrement();
    }

    /**
     * 随机一定数量的body
     * 一定会返回指定数量的body
     * 如果需要的大于已有的数量
     * 则会先将size()倍数的value push进去 然后再对余下数量进行随机
     *
     * @param num
     * @return
     */
    public List<T> randomBody(Integer num, boolean allowDuplicate) {
        if (num <= 0 || size() == 0) {
            return Collections.emptyList();
        }
        List<T> list = Lists.newArrayList();
        if (allowDuplicate) {
            for (int i = 0; i < num; i++) {
                list.add(rangeMap.get(ThreadLocalRandom.current().nextInt(max())));
            }
        } else {
            int i = num / size();
            for (int j = 0; j < i; j++) {
                list.addAll(getValues());
            }
            num = num % size();
            if (num == 0) {
                return list;
            }
            lessRandom(this, num, list);
        }
        return list;
    }

    private <T> void lessRandom(WeightRangeMap<T> rangeMap, Integer num, List<T> resultList) {
        if (num == 0) {
            return;
        }
        int i = ThreadLocalRandom.current().nextInt(rangeMap.max());
        T t = rangeMap.get(i);
        resultList.add(t);
        WeightRangeMap<T> weightRangeMap = new WeightRangeMap<>();
        Map<Range<Integer>, T> entrys = rangeMap.getEntrys();
        entrys.forEach((range, v) -> {
            if (range.contains(i)) {
                return;
            }
            weightRangeMap.put(range.upperEndpoint() - range.lowerEndpoint(), v);
        });
        lessRandom(weightRangeMap, --num, resultList);
    }


    public static void main(String[] args) {
        WeightRangeMap<Integer> map = new WeightRangeMap<>();
        IntStream.range(1, 20).boxed().forEach(i -> map.put(ThreadLocalRandom.current().nextInt(1, 10), i));
        for (int i = 0; i < 20; i++) {
            List<Integer> integers = map.randomBody(18);
            System.out.println(JSON.writeValueAsString(integers));
        }
        System.out.println(JSON.writeValueAsString(map));
    }

    /**
     * 默认缺省 不允许重复
     *
     * @param num
     * @return
     */
    public List<T> randomBody(Integer num) {
        return randomBody(num, Boolean.FALSE);
    }

    public Integer max() {
        return nextRandom.get();
    }

    public int size() {
        return size.get();
    }

    public Collection<T> getValues() {
        return rangeMap.asMapOfRanges().values();
    }

    public Map<Range<Integer>, T> getEntrys() {
        return rangeMap.asMapOfRanges();
    }
}
