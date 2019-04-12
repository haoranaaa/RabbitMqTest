package com.dhr.compute;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/3/27 2:15 PM
 */
public class FormatTest {

    public static List<String> strs = Lists.newArrayList();


    public static void main(String[] args) {
        FormatTest test = new FormatTest();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                format(new DateTime(2017,1,1,1,1,1).toDate());

            }
        }).start();
        new Thread(()->test.test()).start();


    }
    private void test(){
        strs.add("hahahahahah");
        strs.add("Date: {xx}");
        strs.add("xixixixixix");

        Date date = new Date();
        Config config=new Config();
        config.setStringList(strs);
        IntStream.range(0,100).boxed().map(k->CompletableFuture.runAsync(()->
            strs.stream().filter(Objects::nonNull).map(i -> {
                return replace(i,
                        format(date));
            })
                    .forEach(System.out::println)
        )).map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(config.getStringList());
    }

    private String replace(String i, String date) {
        return i.replace("Date",date);

    }

    private static String format(Date date){
      return DateTimeFormat.forPattern("YYYY-MM-DD HH:mm:ss").print(new DateTime(date));
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Config{
        private List<String> stringList;
    }
}
