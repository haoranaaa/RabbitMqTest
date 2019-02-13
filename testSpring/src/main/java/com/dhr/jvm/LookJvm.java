package com.dhr.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class LookJvm {
    private static final double ONE_MB = 1024 * 1024 * 1.0;

    public static void main(String[] args) {
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        memoryPoolMXBeans.forEach(i -> {
            System.out.println("name:" + i.getName());
            System.out.println("type:" + i.getType());
            System.out.println("usage/used:" + i.getUsage().getUsed() / ONE_MB + "MB");
        });
    }
}
