package com.dhr.cache;

import java.util.LinkedList;
import java.util.Queue;

public class LRUCache {
    private int capacity;

    private Queue<Integer> map=new LinkedList<>();
    /*
     * @param capacity: An integer
     */
    public LRUCache(int capacity) {
        // do intialization if necessary
        this.capacity=capacity;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        return 0;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here

        map.offer(key);
    }
}