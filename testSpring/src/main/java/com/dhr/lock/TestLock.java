package com.dhr.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author haoran.duan
 * @since 20 一月 2019
 */
public class TestLock implements Lock{

    private TestSync sync = new TestSync();

    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.isHeldExclusively();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryRelease(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public static class TestSync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if(arg != 1 && getExclusiveOwnerThread()==null){
                if(compareAndSetState(1,0)){
                    setExclusiveOwnerThread(Thread.currentThread());
                }
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(arg==1 && getExclusiveOwnerThread()==Thread.currentThread()
                    && compareAndSetState(0,1)){
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }
}
