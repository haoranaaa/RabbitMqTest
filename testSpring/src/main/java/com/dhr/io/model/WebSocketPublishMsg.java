package com.dhr.io.model;

import java.util.Map;

public class WebSocketPublishMsg {

    private int delayLevel;

    private int qos;

    private int needStore;

    private String topic;

    private Map<String, String> properties;

    private Map<String, Object> body;

    public void setDelayLevel(int delayLevel) {
        this.delayLevel = delayLevel;
    }

    public int getDelayLevel() {
        return delayLevel;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public int getQos() {
        return qos;
    }

    public void setNeedStore(int needStore) {
        this.needStore = needStore;
    }

    public int getNeedStore() {
        return needStore;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
