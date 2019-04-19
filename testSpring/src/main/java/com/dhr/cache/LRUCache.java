package com.dhr.cache;

import com.google.common.collect.Maps;

import java.util.HashMap;
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

    public static void main(String[] args) {
        User user=new User();
        user.setName("a");
        HashMap<String, User> map = Maps.newHashMap();
        map.put("a", user);
        System.out.println(String.format("欢迎%s的「%s」%s",1,map.get("b").getName(),2));
    }
    static class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}