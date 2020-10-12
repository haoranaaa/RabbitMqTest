package com.dhr.bean;


import okhttp3.*;

import java.io.IOException;


/**
 * @author duanhaoran
 * @since 2019/12/14 3:29 PM
 */
public class InvokeTest {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url("http://localhost:8081/club/member/getMemberInformationList")
                .post(RequestBody.create(MediaType.get("application/json"), "{\"clubId\":\"99999\",\"page\":1,\"sortType\":0}"));
        try {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(10);
                Call call = okHttpClient.newCall(builder.build());
                Response execute = call.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface ClubConstants{

        int a = 1;





        int b = 1;

    }
}
