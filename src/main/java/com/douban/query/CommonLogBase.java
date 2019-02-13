package com.douban.query;


import javax.annotation.Resource;

/**
 *
 * @author haoran.duan
 * @since 19 九月 2018
 */

public class CommonLogBase<T> extends LogAdaptor<T> {



    @Override
    public String diffLog(T o, T n) {
        return commonLog(o,n);
    }

    @Override
    public void nullToNewLog(T n) {
        System.out.println(diffLog(null, n));
    }

    @Override
    public void oldToNewLog(T o, T n) {
        System.out.println(o.getClass().getSimpleName());
        System.out.println(diffLog(o, n));

    }

    @Override
    public void oldToNullLog(T o) {
        System.out.println(diffLog(o, null));

    }
}
