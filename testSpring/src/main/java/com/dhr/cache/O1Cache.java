package com.dhr.cache;

import com.dhr.model.CacheNode;
import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

/**
 * o(1)lru 缓存
 * @author haoran.duan
 * @since 27 一月 2019
 */
public class O1Cache<K,V>{

    private CacheNode<K,V> head;

    private CacheNode<K,V> tail;

    private HashMap<K,CacheNode<K,V>> map;

    private Integer size;

    private static Integer DEFAULT_SIZE = 10;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public O1Cache(Integer defaultSize){
        this.map=new HashMap<>();
        DEFAULT_SIZE=defaultSize;
    }
    public O1Cache(){
        this.map=new HashMap<>();
    }

    public void put(K key,V value){
        readWriteLock.writeLock().lock();
        CacheNode<K, V> privious = tail.getPrivious();
        try {
            if (map.containsKey(key)) {
                map.get(key).setValue(value);
                return;
            }
            CacheNode<K, V> nNode = new CacheNode<>(key, value);
            if(size<2){
                if(head==null){
                    nNode=head;
                }else{
                    head.setNext(nNode);
                    head.setPrivious(nNode);
                }
                size++;
            } else if (size > DEFAULT_SIZE) {
                map.remove(tail.getKey());
                tail = new CacheNode<K, V>(key, value, privious, head);
            } else {
                nNode.setNext(head);
                nNode.setNext(tail);
                tail.setNext(nNode);
                size++;
            }
            map.put(key,nNode);
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public V getValue(K key){
        readWriteLock.readLock().lock();
        try {
            if (!map.containsKey(key)) {
                return null;
            }
            return map.get(key).getValue();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Boolean containsKey(K key){
        return map.containsKey(key);
    }

    public Integer size(){
        return size;
    }

    public V computeIfPresent(K k,Function<K,V> fun){
        Preconditions.checkNotNull(fun);
        this.put(k,fun.apply(k));
        return map.get(k).getValue();
    }


}
