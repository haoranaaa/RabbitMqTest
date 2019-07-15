package com.dhr.file;

import org.joda.time.LocalTime;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author duanhaoran
 * @since 2019/4/19 8:18 PM
 */
public class FileTest {

    public static void main(String[] args) {
       /* File file = new File("");
        String absolutePath = file.getAbsolutePath();
        File file1 = new File(absolutePath, "home");
        if (!file1.exists()) {
            System.out.println("我建了一个文件夹 ");
            boolean mkdirs = file1.mkdirs();
            System.out.println(mkdirs);
        }
        if (file1.isDirectory()) {
            System.out.println("我是文件夹！");
        }*/

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        if (atomicBoolean.getAndSet(false)) {
            System.out.println("aaa");
        }
        System.out.println(isOpenMatchTime());
    }

    public static boolean isOpenMatchTime() {
        int millisOfDay = LocalTime.now().getMillisOfDay();
        LocalTime start = LocalTime.parse("12:00:00");
        LocalTime end = LocalTime.parse("02:00:00");
        //判断是否跨天
        if (start.getMillisOfDay() <= end.getMillisOfDay()) {
            return millisOfDay <= end.getMillisOfDay() && millisOfDay >= start.getMillisOfDay();
        }
        //跨天的话需要拿每天的最后一秒作为分界值
        return millisOfDay >= start.getMillisOfDay() || millisOfDay <= end.getMillisOfDay();
    }
}
