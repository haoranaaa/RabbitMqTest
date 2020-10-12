package com.dhr.io;

import com.dhr.io.model.WebSocketConnectMsg;
import com.dhr.io.model.WebSocketMsg;
import com.guman.common.json.JSON;
import com.guman.common.util.BeanMapUtils;
import com.guman.common.util.ProtobufFormart;
import io.netty.handler.codec.mqtt.*;
import org.java_websocket.enums.ReadyState;
import org.springframework.cglib.beans.BeanMap;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.URI;
import java.security.KeyStore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author duanhaoran
 * @since 2020/5/19 5:31 PM
 */
public class WebSocketClient {
    //基于证书生成的store秘钥文件的路径
    private static String KEYSTORE = "/Users/duanhaoran/Desktop/my.store";
    //使用keytool工具时，输入的密码
    private static String STOREPASSWORD = "123456";
    private static String KEYPASSWORD = "123456";

    private static AtomicInteger GEN_MSG_ID = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

//        WebSocketClientInst chatclient = new WebSocketClientInst(new URI("wss://dev-access.kuaishebao.com:7887/websocket_test_wchat"));
//
//        // load up the key store
//
//
//        File kf = new File(KEYSTORE);
//        ks.load(new FileInputStream(kf), STOREPASSWORD.toCharArray());
//
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
//
//        kmf.init(ks, KEYPASSWORD.toCharArray());
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
//        tmf.init(ks);
//
//        SSLContext sslContext;
//        sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
//
//        // (SSLSocketFactory) SSLSocketFactory.getDefault();
//        SSLSocketFactory factory = sslContext.getSocketFactory();
//
//        chatclient.setSocketFactory(factory);
//
//        chatclient.connectBlocking();
//
//        boolean loop = true;
//        int times = 0;
//        while (loop) {
//            times++;
//            if (ReadyState.OPEN.equals(chatclient.getReadyState())) {
//                System.out.println("开始建立连接！");
//                chatclient.send(ProtobufFormart.serializer(genConnectMsg()));
//            } else {
//                System.out.println("还没ready, 继续进行中");
//                if (times <= 10) {
//                    Thread.sleep(1000);
//                } else {
//                    System.out.println("超时");
//                    break;
//                }
//            }
//        }
    }

    private static WebSocketMsg genConnectMsg() {
        WebSocketConnectMsg connectMsg = new WebSocketConnectMsg();
        connectMsg.setAppId(100000);
        connectMsg.setClientId("1001");
        connectMsg.setKeepAlive(1);
        connectMsg.setUserName("69101825");
        connectMsg.setPassword("testabc");
        WebSocketMsg msg = new WebSocketMsg(0, 0, String.valueOf(GEN_MSG_ID.getAndIncrement()), BeanMapUtils.beanToMap(connectMsg));
        return msg;
    }

    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


}
