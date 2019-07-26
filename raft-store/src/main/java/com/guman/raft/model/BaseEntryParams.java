package com.guman.raft.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by guman on 2019/7/24.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntryParams {

    /**
     * 领导人Id
     */
    private String leaderId;
    /**
     * 索引值 紧贴上一索引
     */
    private long prevLogIndex;
    /**
     * 任期号
     */
    private long preLogTerm;
    /**
     * 存储的日志条目
     */
    private List<EntryParam> entryParam;
    /**
     * 领导已经提交的索引
     */
    private long leaderCommit;

}
