package com.dhr.bean;


import com.alipay.remoting.NamedThreadFactory;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import okhttp3.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author duanhaoran
 * @since 2019/5/28 1:23 PM
 */
public class tesss {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private static Splitter on = Splitter.on(",");

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20
            , 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024)
            , new NamedThreadFactory("terst"), new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        tesss tesss = new tesss();
        tesss.castMap();
    }

    @Data
    static class A{
        private String xRate;
    }

    private void castMap(){

       String a= "14563378,17846533,245801962,28752875," +
               "29203238," +
               "36577220," +
               "442673131," +
               "442673180," +
               "49780550," +
               "58068855," +
               "58346823," +
               "60450692," +
               "62418983," +
               "62824760," +
               "72115091," +
               "74430358," +
               "75451275," +
               "83565513," +
               "86377209," +
               "944045808," +
               "944045813," +
               "944045877," +
               "944045896," +
               "982161516," +
               "98486384," +
               "52117460," +
               "764389649," +
               "89793157," +
               "23153352," +
               "38772130," +
               "98120308,98120308,38772130,23153352,89793157,764389649,52117460,98486384,982161516,944045896" +
               ",944045877,944045813,944045808,86377209,83565513,75451275,74430358,72115091,62824760" +
               ",62418983,60450692";
        on.splitToList(a).stream()
                .filter(Objects::nonNull).filter(StringUtils::hasText)
                .forEach(i-> {
                    try {
                        senMsgByRpc(i);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    private boolean senMsg(String uid) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url("http://testapitest.wb-intra.com/ms/test");
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("url", "groupChat.forcedAddUser");
        builder1.add("host", "chat.wb-ms.com");
        builder1.add("params", "{\"groupId\":\"42481\",\"inviteUid\":\"24137770\",\"uid\":\""+uid+"\", \"from\":\"test\"}");
        builder.post(builder1.build());
        long timeMillis = System.currentTimeMillis();
        Call call = okHttpClient.newCall(builder.build());
        Response execute = call.execute();
        System.out.println("耗时"+ (System.currentTimeMillis() - timeMillis)+ "ms");
        System.out.println(execute.body().string());
        return true;

    }

    private boolean senMsgByRpc(String uid) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url("http://testapitest.wb-intra.com/rpc/test");
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("url", "/club/member/joinApplication");
        builder1.add("host", "club");
        builder1.add("params", "{\"uid\":\""+uid+"\",\"clubId\":\"148864\",\"index\":1}");
        builder.post(builder1.build());
        long timeMillis = System.currentTimeMillis();
        Call call = okHttpClient.newCall(builder.build());
        Response execute = call.execute();
        System.out.println("耗时"+ (System.currentTimeMillis() - timeMillis)+ "ms");
        System.out.println(execute.body().string());
        return true;

    }

}
