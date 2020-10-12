package com.dhr.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个int实现读写锁
 * @author duanhaoran
 * @since 2020/3/7 4:30 PM
 */
public class TwoIntLock {

    private AtomicInteger readCount = new AtomicInteger(0);

    private AtomicInteger writeCount = new AtomicInteger(0);

    public void lockRead() throws InterruptedException {
        while (writeCount.get() > 0) {
            synchronized (this) {
                wait();
            }
        }
        readCount.incrementAndGet();
    }

    public void unLockRead() {
        readCount.decrementAndGet();
        synchronized (this) {
            notifyAll();
        }
    }

    public void writeLock() throws InterruptedException {
        while (writeCount.get() > 0) {
            synchronized (this) {
                wait();
            }
        }
        writeCount.incrementAndGet();
        while (readCount.get() > 0) {
            synchronized (this) {
                wait();
            }
        }
    }


    public void unLockWrite() {
        writeCount.decrementAndGet();
        synchronized (this) {
            notifyAll();
        }
    }

}
