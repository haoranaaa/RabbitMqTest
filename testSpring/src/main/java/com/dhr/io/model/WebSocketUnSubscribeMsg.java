package com.dhr.io.model;

import java.util.List;

public class WebSocketUnSubscribeMsg {
    private List<String> topics;

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTopics() {
        return topics;
    }
}
