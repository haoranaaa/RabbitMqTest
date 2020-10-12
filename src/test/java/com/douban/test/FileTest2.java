package com.douban.test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.guman.common.io.FileReader;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2020/5/2 7:44 PM
 */
public class FileTest2 {


    public static Splitter splitter = Splitter.on(" | ");

    public static void main(String[] args) {
//        File file = new File("/Users/duanhaoran/Downloads/data_20200502_115815.txt");
//        File tofile = new File("/Users/duanhaoran/Projects/RabbitMQTest/testSpring/src/main/resources/uids.txt");
//        AtomicInteger ai = new AtomicInteger(0);
//        try(InputStreamReader is = new InputStreamReader(new FileInputStream(file))) {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(tofile));
//            BufferedReader br = new BufferedReader(is);
//            Set<String> set = Sets.newHashSet();
//            String line = br.readLine();
//            while (line != null) {
//                String a = line;
//                List<String> list = splitter.splitToList(a);
//                String s = list.get(2);
//                set.add(s);
//                ai.incrementAndGet();
//                line = br.readLine();
//            }
//            System.out.println(ai.get());
//            System.out.println(set.size());
//            AtomicInteger ae = new AtomicInteger(set.size());
//            set.forEach(i-> {
//                try {
//                    ae.getAndDecrement();
//                    bw.write(i);
//                    bw.newLine();
//                    bw.flush();
//                } catch (IOException e) {
//                    System.out.println("error !");
//                }
//            });
//            bw.close();
//            System.out.println(ae.get());
//        } catch (FileNotFoundException e) {
//            System.out.println("error !");
//        } catch (IOException e) {
//            System.out.println("error !");
//        }

        IntStream.range(1,10).boxed().map(String::valueOf).filter(StringUtils::isEmpty).forEach(System.out::println);
    }

}
