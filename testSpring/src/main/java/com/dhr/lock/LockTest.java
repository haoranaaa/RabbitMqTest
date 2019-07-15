package com.dhr.lock;

import org.apache.commons.lang3.time.StopWatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author haoran.duan
 * @since 20 一月 2019
 */
public class LockTest {

    private static final TestLock lock = new TestLock();

    private static Integer k = 0;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            0, 100, 10,
            TimeUnit.SECONDS, new SynchronousQueue<Runnable>()
            , r -> new Thread("batchGetWolfOfficial-%s")
            , new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
//        IntStream.range(0, 5).boxed().forEach(System.out::println);
//        AtomicInteger integer = new AtomicInteger(0);
//        for (int i = 0; i < 1000; i++) {
//            executor.execute(() -> {
//                try {
//                    System.out.println(integer.getAndIncrement());
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        System.out.println(executor.getActiveCount());


        StopWatch started = StopWatch.createStarted();
        boolean between2Time = isBetween2Time("20:00:00", "21:00:00");
        started.stop();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse("2019-11-11 00:00:00");
            System.out.println(parse);
            List<Integer> list = null;
            IntStream.range(0, 10).boxed().filter(i -> i == 2)
                    .filter(list::contains)
                    .collect(Collectors.toList());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(started.getTime(TimeUnit.MILLISECONDS));
    }

    /**
     * 判断当前时间是否在当天指定的小时内
     * @param startTime  20：00：00
     * @param endTime    21：00：00   26：00：00代表次日两点
     * @return
     */
    public static boolean isBetween2Time(String startTime,String endTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            long nowTime = calendar.getTimeInMillis();

            String[] startTimeArr = startTime.split(":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTimeArr[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(startTimeArr[1]));
            calendar.set(Calendar.SECOND, Integer.parseInt(startTimeArr[2]));
            long startTimeL = calendar.getTimeInMillis();

            String[] endTimeArr = endTime.split(":");
            if(Integer.parseInt(endTimeArr[0]) <= 24) {
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endTimeArr[0]));
                calendar.set(Calendar.MINUTE, Integer.parseInt(endTimeArr[1]));
                calendar.set(Calendar.SECOND, Integer.parseInt(endTimeArr[2]));
                long endTimeL = calendar.getTimeInMillis();
                calendar.setTimeInMillis(endTimeL);

                return nowTime >= startTimeL && nowTime <= endTimeL;
            }else {
                //去掉天数的结束时间  比如26 的结果为2
                Integer endTimeHour = Integer.parseInt(endTimeArr[0]) % 24;
                calendar.set(Calendar.HOUR_OF_DAY, endTimeHour);
                calendar.set(Calendar.MINUTE, Integer.parseInt(endTimeArr[1]));
                calendar.set(Calendar.SECOND, Integer.parseInt(endTimeArr[2]));
                long endTimeL = calendar.getTimeInMillis();
                calendar.setTimeInMillis(endTimeL);

                return nowTime >= startTimeL || nowTime <= endTimeL;

            }
        } catch (Exception e) {
            return false;
        }
    }
    class Bean{
        private String a = "a";
        private String b = "b";
        private String c = "c";
        private String d = "d";

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }
    }

    private static void printLog(){
        lock.lock();
        System.out.println(k);
        k++;
        lock.unlock();
    }
}
