package com.dhr.io;

import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author duanhaoran
 * @since 2020/5/19 5:35 PM
 */
public class WebSocketClientInst  extends WebSocketClient{

    public WebSocketClientInst(URI serverUri ) {
        super( serverUri );
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        System.out.println( "Connected" );

    }

    @Override
    public void onMessage( String message ) {
        System.out.println( "got: " + message );

    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println( "Disconnected" );

    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();

    }
}
