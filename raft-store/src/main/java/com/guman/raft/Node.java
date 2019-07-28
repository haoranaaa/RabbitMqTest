package com.guman.raft;

import com.guman.raft.common.NodeConfig;
import com.guman.raft.model.*;

/**
 * @author duanhaoran
 * @since 2019/7/28 7:02 PM
 */
public interface Node<T> extends LifeCycle{
    /**
     * 设置配置文件
     * @param config
     */
    void setConfig(NodeConfig config);

    /**
     * 处理投票请求
     * @param param
     * @return
     */
    VoteResult handleRequestVote(VoteParam param);

    /**
     * 处理附加日志请求
     * @param entryParam
     * @return
     */
    EntryResult handleRequestAppendLog(EntryParam entryParam);

    /**
     * 处理客户端k,v请求
     * @param clientPairReq
     * @return
     */
    EntryResult handleClientRequest(ClientPairReq clientPairReq);

}
