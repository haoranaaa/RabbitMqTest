package com.dhr.json;

import lombok.Data;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author duanhaoran
 * @since 2019/11/18 10:35 AM
 */
public class IntJson {

    public static void main(String[] args) {
        ServiceLoader<InterF> load = ServiceLoader.load(InterF.class);
        Iterator<InterF> iterator = load.iterator();
        while (iterator.hasNext()) {
            InterF next = iterator.next();
            next.sout();
        }
    }



}
class InteFAImpl implements InterF{

    @Override
    public void sout() {
        System.out.println("A");
    }
}

class InteFBImpl implements InterF{

    @Override
    public void sout() {
        System.out.println("B");
    }
}
interface InterF{
    void sout();
}
