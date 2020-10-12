package com.dhr.io.model;

public class WebSocketMsgType {
    public final static int MSG_TYPE_CONNECT = 0;
    public final static int MSG_TYPE_CONNECT_ACK = 1;
    public final static int MSG_TYPE_SUBSCRIBE = 2;
    public final static int MSG_TYPE_SUBSCRIBE_ACK = 3;
    public final static int MSG_TYPE_UNSUBSCRIBE = 4;
    public final static int MSG_TYPE_UNSUBSCRIBE_ACK = 5;
    public final static int MSG_TYPE_PUBLISH = 6;
    public final static int MSG_TYPE_PUBLISH_ACK = 7;
    public final static int MSG_TYPE_DISCONNECT = 8;
    public final static int MSG_TYPE_PING = 9;
    public final static int MSG_TYPE_PONG = 10;
}
