package com.dhr.file;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.joda.time.LocalTime;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author duanhaoran
 * @since 2019/4/19 8:18 PM
 */
public class FileTest {

    public static void main(String[] args) {
        File file = new File("/Users/duanhaoran/Downloads/data_20200128_130555.txt");
        File tofile = new File("/Users/duanhaoran/Projects/RabbitMQTest/testSpring/src/main/resources/bcd.txt");
        try(InputStreamReader is = new InputStreamReader(new FileInputStream(file))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(tofile));
            BufferedReader br = new BufferedReader(is);
            Set<String> set = Sets.newHashSet();
            List<String> list = Lists.newArrayList();
            AtomicInteger ai = new AtomicInteger(0);
            String line = br.readLine();
            while (line != null) {
                String a = line;
                String trim = a.split("gameStage =")[1].trim().split("java.lang.IndexOutOfBoundsException")[0];
                String s = trim.substring(33,trim.length()-5);
                set.add(s);
                list.add(s);
                ai.incrementAndGet();
                line = br.readLine();
            }
            System.out.println(ai.get());
            System.out.println(set.size());
            AtomicInteger ae = new AtomicInteger(set.size());
            set.forEach(i-> {
                try {
                    ae.getAndDecrement();
                    bw.write(i);
                    bw.newLine();
                    bw.flush();
                } catch (IOException e) {
                    System.out.println("error !");
                }
            });
            bw.close();
            System.out.println(ae.get());
        } catch (FileNotFoundException e) {
            System.out.println("error !");
        } catch (IOException e) {
            System.out.println("error !");
        }

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
