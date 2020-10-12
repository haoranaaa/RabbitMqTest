package com.dhr.io;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author duanhaoran
 * @since 2020/5/19 2:51 PM
 */
public class NettyClient {

    private static WebSocketClient client;

    public static void main(String[] args) throws InterruptedException {
//        try {
//            client = new WebSocketClient(new URI("ws://dev-access.kuaishebao.com:7887/websocket_test_wchat"),new Draft_6455()) {
//                @Override
//                public void onOpen(ServerHandshake serverHandshake) {
//                    System.out.println("握手成功");
//                }
//
//                @Override
//                public void onMessage(String msg) {
//                    System.out.println("收到消息=========="+msg);
//                    if(msg.equals("over")){
//                        client.close();
//                    }
//
//                }
//
//                @Override
//                public void onClose(int i, String s, boolean b) {
//                    System.out.println("链接已关闭， code:"+ i + "reason:"+s);
//                }
//
//                @Override
//                public void onError(Exception e){
//                    e.printStackTrace();
//                    System.out.println("发生错误已关闭");
//                }
//            };
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        client.connect();
//        System.out.println(String.valueOf(client.getDraft()));
//        while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
//            Thread.sleep(1000);
//            System.out.println("正在连接...");
//        }
//        //连接成功,发送信息
//        client.send("哈喽,连接一下啊");

    }



}
