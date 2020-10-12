package com.dhr.compute;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2020/6/5 2:40 AM
 */
public class MapSum {
    Map<String, Integer> map;
    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        val = val - map.getOrDefault(key, 0);
        for(int i = 1;i<=key.length();i++){
            String key1 = key.substring(0, i);
            Integer v = map.getOrDefault(key1, 0) + val;
            map.put(key1,v);
        }
    }

    public int sum(String prefix) {
        return map.getOrDefault(prefix, 0);
    }


    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("apple",3);
        System.out.println(obj.sum("ap"));
        obj.insert("app",2);
        System.out.println(obj.sum("ap"));
    }
/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
}
