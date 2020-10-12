package com.dhr.io.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMsg {
    private int v;

    private int type;

    private String msgId;

    private Map<String, Object> payload;
}
