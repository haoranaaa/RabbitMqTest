package com.dhr.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class IOClient {


    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }

}
