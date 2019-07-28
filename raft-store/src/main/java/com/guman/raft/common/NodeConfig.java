package com.guman.raft.common;

import lombok.Data;

import java.util.List;

/**
 * @author duanhaoran
 * @since 2019/7/28 7:02 PM
 */
@Data
public class NodeConfig {
    /**
     * 当前节点端口号
     */
    private int selfPort;

    /**
     * 当前节点端口号
     */
    private String selfAddress = "localhost";

    /**
     * 其它节点地址
     */
    private List<String> otherAddresses;

}
