package com.dhr.cache;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author duanhaoran
 * @since 2020/6/8 5:59 PM
 */
public class LockCurrent {

    private static final String RELEASE_LOCK_SCRIPT_NAME = "release_lock_script";

    private long expireTime;

    private String key;

    private Jedis redisCache;

    private boolean getLock = false;

    private boolean actualGetLock = false;

    //private long curValue;

    private String hostKey = null;

    private String a = null;

    private static final String HOST_AND_PID = "test:a";

    public LockCurrent(Jedis redisCache, String key) {
        this(redisCache, key, DateUtils.MILLIS_PER_SECOND);
    }

    /**
     * @param redisCache 使用的redis节点/集群
     * @param key        加锁使用的key，比如friend为 friend:lock:uid，注意前面不要加app:
     *                   命名格式为 业务名:lock:具体id
     * @param expireTime 锁的过期时间，默认为1s
     */
    public LockCurrent(Jedis redisCache, String key, long expireTime) {
        this.redisCache = redisCache;
        this.key = key; //这里做个前缀是为了跟老版本区分开
        this.expireTime = expireTime;
        a = redisCache.scriptLoad(sha1);

    }

    public boolean lock() {
        return lock(false, 0);
    }

    public boolean tryLock() {
        return tryLockOnce();
    }
    String sha1 = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    /**
     * 释放锁，使用lua脚本保证原子性
     *
     * @return
     */
    public boolean unlock() {
        boolean ret = false;
        if (actualGetLock) {
            try {
                ret = NumberUtils.toInt(String.valueOf(redisCache.evalsha(a, 1, key, hostKey))) > 0;
            } catch (Throwable e) {
                ret = NumberUtils.toInt(String.valueOf(redisCache.evalsha(a, 1, key, hostKey))) > 0;
            }
        }
        return ret;
    }

    public boolean tryLock(long time) {
        return lock(true, time);
    }

    private boolean lock(boolean useTimeout, long timeout) {
        if (timeout <= 0) {
            useTimeout = false;
        }
        long start = System.currentTimeMillis();
        while (!useTimeout || !isTimeout(start, timeout)) {
            if (tryLockOnce()) {
                System.out.println(String.format("get lock success |%s|%s|%s|%s", key, useTimeout, timeout, System.currentTimeMillis() - start));
                break;
            } else {
                System.out.println(String.format("retry lock|%s|%s|%s|%s", key, useTimeout, timeout, "t-" + Thread.currentThread().getId()));
            }
        }

        System.out.println(String.format("get lock ret|%s|%s|%s|%s|%s|", key, getLock, actualGetLock, useTimeout, timeout));
        return getLock;
    }

    private boolean tryLockOnce() {
        String curHost = getCurrentHostKey();
        if (StringUtils.isEmpty(hostKey)) {
            hostKey = redisCache.get(key);
        }
        if (!StringUtils.isEmpty(hostKey) && hostKey.equals(curHost)) {
            getLock = true;
            return true;
        }
        if (StringUtils.equals(redisCache.set(key, curHost, "nx", "px", expireTime),"OK")) {
            hostKey = curHost;
            getLock = true;
            actualGetLock = true;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        JedisPool jedisPool = new JedisPool("127.0.0.1"
                , 6379);
        new Thread(()->{
            LockCurrent lockCurrent = new LockCurrent(jedisPool.getResource(), "110");
            lockCurrent.lock();
            System.out.println("1锁上了！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            lockCurrent.unlock();
            System.out.println(System.currentTimeMillis());
        }).start();
        Thread.sleep(200);
        new Thread(()->{
            LockCurrent lockCurrent = new LockCurrent(jedisPool.getResource(), "110");
            while (true) {
                if (!lockCurrent.tryLockOnce()) {
                    System.out.println("2没抢到！"+ System.currentTimeMillis());
                }else {
                    System.out.println("2抢到了");
                    break;
                }
            }
        }).start();

        Thread.sleep(20* 1000);
    }

    private String getCurrentHostKey() {
        return HOST_AND_PID + ":" + Thread.currentThread().getId();
    }

    private boolean isTimeout(long start, long timeout) {
        boolean ret = start + timeout < System.currentTimeMillis();

        return ret;
    }


}
