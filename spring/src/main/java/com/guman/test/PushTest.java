package com.guman.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
/**
 * @author duanhaoran
 * @since 2020/1/10 12:02 PM
 */
public class PushTest {



        public static void main(String[] args) {
            String from = "10000001";
            String to = "69101825";
            int msgCount = 1000;

            sendMsg2UserLoop(from,to,msgCount);
        }

        /**
         * 循环给一个用户发消息
         */
        private static void sendMsg2UserLoop(String from, String to, int msgCount){

            for (int i = 0; i < msgCount; i++) {
                java.util.UUID uuid = java.util.UUID.randomUUID();
                String sid = uuid.toString().replace("-", "");

                String str = sendPost("http://devapitest.wb-intra.com/ms/test",
                        "url=chat.sendChatMsgV2&host=chatmsg.wb-ms.com&params={\n" +
                                " \"msgs\": {\n" +
                                "  \"msgs\": [{\n" +
                                "   \"msg\": {\n" +
                                "    \"v\": 1,\n" +
                                "    \"sid\": \""
                                +sid
                                + "\",\n" +
                                "    \"bizType\": 1,\n" +
                                "    \"format\": 0,\n" +
                                "    \"chatType\": 1,\n" +
                                "    \"msgType\": 0,\n" +
                                "    \"offlineFlg\": 1,\n" +
                                "    \"pushFlg\": 1,\n" +
                                "    \"ackFlg\": 1,\n" +
                                "    \"from\": \""
                                +from
                                + "\",\n" +
                                "    \"to\": \""
                                +to
                                + "\",\n" +
                                "    \"time\": "
                                +System.currentTimeMillis()
                                + ",\n" +
                                "    \"appId\": 0,\n" +
                                "    \"appVer\": 0,\n" +
                                "    \"bubble\": 0,\n" +
                                "    \"offline\": 0,\n" +
                                "    \"body\": {\n" +
                                "     \"message\": \"大家好，我是小玩，很高兴认识你，一起加油哦！"+i+"\"\n" +
                                "    }\n" +
                                "   }\n" +
                                "  }]\n" +
                                " },\n" +
                                " \"from\": \"ChatMsgSenderV2\"\n" +
                                "}");

                String data = (i+1)+"/"+msgCount;
                System.out.println(data);

            }

        }


        public static String sendPost(String url, String param) {
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送 POST 请求出现异常！" + e);
                e.printStackTrace();
            }
            // 使用finally块来关闭输出流、输入流
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return result;
        }

}
