package com.dhr.dataStructer;

import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * 最小堆
 *
 * @author duanhaoran
 * @since 2020/10/18 6:23 PM
 */
public class MinHeap implements Heap {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    private int maxSize;

    private int size;

    private int[] h;

    public MinHeap(int initialArraySize, int[] defaultArray) {
        Preconditions.checkArgument(initialArraySize > 0, "size can not less than 0 .");
        maxSize = sizeFor(initialArraySize)+1;
        init(defaultArray);
        size = defaultArray.length;
    }

    private void init(int[] array) {
        Preconditions.checkNotNull(array);
        Preconditions.checkArgument(array.length <= maxSize);
        h = new int[maxSize];
        for (int i = 1; i <= array.length; i++) {
            h[i] = array[i - 1];
        }
        for (int i = array.length +1; i < maxSize ; i++) {
            h[i] = -10;
        }
        for (int i = array.length / 2; i > 0; i--) {
            down(i);
        }
    }

    /**
     * 删除最小值
     */
    public void delMin() {
        h[1] = h[size];
        down(1);
        h[size] = 0;
        size--;
    }

    /**
     * 插入值
     *
     * @param k
     */
    public void insert(int k) {
        if (size == maxSize) {
            maxSize = maxSize / 2;
            h = Arrays.copyOf(h, maxSize);
        }
        h[++size] = k;
        up(size);
    }

    /**
     * 删除任意一个值
     *
     * @param idx
     */
    public void del(int idx) {
        h[idx] = h[size];
        h[size] = 0;
        size--;
        down(idx);
        up(idx);
    }

    public int min() {
        return h[1];
    }

    private int sizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int size() {
        return size;
    }

    private void down(int u) {
        int t = u;
        if (u * 2 < size && h[u * 2] < h[t]) t = u * 2;
        if (u * 2 < size && h[u * 2 + 1] < h[t]) t = u * 2 + 1;
        if (u != t) {
            swap(u, t);
            down(t);
        }
    }

    private void up(int u) {
        while (u / 2 > 0 && h[u / 2] > h[u]) {
            swap(u, u / 2);
            u = u / 2;
        }
    }

    private void swap(int u, int i) {
        int k = h[u];
        h[u] = h[i];
        h[i] = k;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10, new int[]{21, 3, 1, 512, 44, 1, 31, 2451, 2, 2});
        System.out.println(minHeap.min());
        minHeap.insert(0);
        System.out.println(minHeap.min());
        minHeap.insert(-5);
        System.out.println(minHeap.min());
    }


}
