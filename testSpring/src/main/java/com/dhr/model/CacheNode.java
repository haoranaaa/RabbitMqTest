package com.dhr.model;

/**
 *
 * @author haoran.duan
 * @since 27 一月 2019
 */
public class CacheNode<K,V> {

    private K key;

    private V value;

    private CacheNode<K,V> next;

    private CacheNode<K,V> privious;

    public CacheNode(K key,V value,CacheNode<K,V> privious,CacheNode<K,V> next){
        this.key=key;
        this.value=value;
        this.privious=privious;
    }

    public CacheNode(K key,V value){
        this.key=key;
        this.value=value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public CacheNode<K, V> getNext() {
        return next;
    }

    public void setNext(CacheNode<K, V> next) {
        this.next = next;
    }

    public CacheNode<K, V> getPrivious() {
        return privious;
    }

    public void setPrivious(CacheNode<K, V> privious) {
        this.privious = privious;
    }
}
