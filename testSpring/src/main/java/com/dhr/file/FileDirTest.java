package com.dhr.file;

import com.guman.common.json.JSON;
import org.joda.time.DateTime;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author duanhaoran
 * @since 2019/9/5 1:08 PM
 */
public class FileDirTest {


    public static void main(String[] args) {
        String filePath = "/Users/duanhaoran/Desktop/";
        File file = new File(filePath);
        File[] files = file.listFiles(((dir, name) -> {
            if (name.endsWith(".aac")) {
                return true;
            }
            return false;
        }));
        for (File file1: files) {
            System.out.println(file1.getName());
        }
        long l = System.currentTimeMillis() / 1000 / 60;
        System.out.println(l);
        ABCTesc abcTesc = null;
        String json = "{\"a\":2.2}";
        ABCTesc abcTesc1 = JSON.readValue(json, ABCTesc.class);
        System.out.println(JSON.writeValueAsString(abcTesc1));
        DateTime dateTime = new DateTime(new Date(1567612800000L));
        DateTime dateTime1 = new DateTime(2019, 9, 5, 4, 0, 0);
        System.out.println(formatTo4Y2M2D(dateTime1.minusDays(1).toDate()));

        String a = "app:official:record:admin:12312341:12412414:9";
        String pattern = "app:official:record:admin:.*.9$";
        String shunfengPattern = "SF.*[0-9]";
        boolean matches = Pattern.matches(pattern, a);
        System.out.println(matches);
    }
    public static String formatTo4Y2M2D(Date date) {
        if (Objects.isNull(date)) {
            return "0000-00-00";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    private static void set(ABCTesc abcTesc) {
        abcTesc = new ABCTesc();
    }

}
 class ABCTesc {
    private double a;

     public double getA() {
         return a;
     }

     public void setA(double a) {
         this.a = a;
     }
 }
