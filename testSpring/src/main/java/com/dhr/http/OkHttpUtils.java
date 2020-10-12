package com.dhr.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.guman.common.json.JSON;
import lombok.Data;
import okhttp3.*;
import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2020/8/13 4:05 PM
 */
public class OkHttpUtils {
    private static OkHttpClient okHttpClient = new OkHttpClient();

    private static final String APP_ID = "wxa3b799f0dca49df4";

    private static final String SERCRET = "40c41616156d3e47cc289c824c401424";


    public static <T> T sendPostReq(String url, Map<String, Object> body, Class<T> responseType) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.post(RequestBody.create(MediaType.parse("application/json"), JSON.writeValueAsString(body)));
        Call call = okHttpClient.newCall(builder.build());
        Response execute = call.execute();
        if (execute != null && execute.body() !=null) {
            ResponseBody body1 = execute.body();
            return JSON.readValue(body1.string(), responseType);
        }
        return null;
    }

    public static Map<String, Object> sendGetReq(String url) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.get();
        Call call = okHttpClient.newCall(builder.build());
        Response execute = call.execute();
        if (execute != null && execute.body() !=null) {
            ResponseBody body1 = execute.body();
            try {
                return JSON.readValue(body1.string(), new TypeReference<Map<String, Object>>() {
                });
            }finally {
                body1.close();
                execute.close();
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        Map<String, Object> map = sendGetReq(String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APP_ID, SERCRET));
        System.out.println(JSON.writeValueAsString(map));
        String access_token = MapUtils.getString(map, "access_token");
        ImmutableMap<String, Object> of = ImmutableMap.of("content", "澳门赌场-开户就送-网址");
        WechatTextCheckResp wechatTextCheckResp = sendPostReq("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + access_token, of, WechatTextCheckResp.class);
        System.out.println(JSON.writeValueAsString(wechatTextCheckResp));
    }

    @Data
    public static class WechatTextCheckResp{

        public Integer errcode;

        public String errmsg;
    }
}
