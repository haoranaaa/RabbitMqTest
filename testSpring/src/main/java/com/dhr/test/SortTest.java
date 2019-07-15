package com.dhr.test;

import com.google.common.collect.Maps;
import javafx.util.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author haoran.duan
 * @since 10 十二月 2018
 */
public class SortTest {
    private static Logger logger = LoggerFactory.getLogger(SortTest.class);
    private static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        logger.info("{5},{4},{3},{2},{1},{0}",0,1,2,3,4,5);
        System.out.println(52 * 100 / 99 + "%");
        System.out.println(1704 / 60);
        // 结束时间
        String endDate = DateTime.now().toString();
        // 7天之前
        String startDate = LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern(NORMAL_DATE_FORMAT));
        System.out.println(endDate + "" + startDate);

    }

    private void sortList(List<String> list){
        list.sort(Comparator.comparing(Function.identity()));

    }
}
