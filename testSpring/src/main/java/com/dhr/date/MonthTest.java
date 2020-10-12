package com.dhr.date;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/8/6 4:35 PM
 */
public class MonthTest {
    public static void main(String[] args) throws ParseException {
        Date date = DateTime.now().minusMonths(1).toDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = format.format(cal_1.getTime());
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = format.format(cale.getTime());
        System.out.println(firstDay + "-" + lastDay);
        System.out.println(strToDate("2019-08-09", "yyyy-MM-dd"));
        System.out.println(Runtime.getRuntime().availableProcessors());
        List<Date> dates = IntStream.range(0, 10).boxed().map(i -> DateTime.now().minusDays(i).toDate()).collect(Collectors.toList());
        Map<Date, List<Date>> collect = dates.stream().collect(Collectors.groupingBy(Function.identity()));
        collect.entrySet().stream().sorted((a,b)->a.getKey().compareTo(b.getKey()) * -1).forEach(System.out::println);

        int x = 60 * 60 * 24 - DateTime.now().getSecondOfDay();
        System.out.println(x);
        System.out.println(x / 60 / 60);
    }

    public static Date strToDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sp = new SimpleDateFormat(pattern);
        return sp.parse(date);
    }
}
