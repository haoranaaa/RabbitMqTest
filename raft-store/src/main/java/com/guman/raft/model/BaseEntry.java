package com.guman.raft.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by guman on 2019/7/24.
 */
@Data
public class BaseEntry implements Serializable {

    private long term;

    private String serviceId;

}
