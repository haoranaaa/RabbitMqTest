package com.guman.raft.impl;

import com.google.common.base.Splitter;
import com.guman.raft.LifeCycle;
import com.guman.raft.LogModule;
import com.guman.raft.Node;
import com.guman.raft.Store;
import com.guman.raft.common.NodeConfig;
import com.guman.raft.model.*;
import com.guman.raft.rpc.DefaultRpcClient;
import com.guman.raft.rpc.DefaultRpcServer;
import com.guman.raft.rpc.RpcClient;
import com.guman.raft.rpc.RpcServer;
import com.guman.raft.store.DefaultLogModule;
import com.guman.raft.store.DefaultStore;
import com.guman.raft.thread.DefaultThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author duanhaoran
 * @since 2019/7/28 7:09 PM
 */
@Slf4j
public class DefaultNode<T> implements Node, LifeCycle {

    private AtomicBoolean start = new AtomicBoolean(Boolean.FALSE);

    private static Splitter splitter = Splitter.on(":");

    /**
     * 选举时间间隔基数
     */
    public volatile long electionTime = 15 * 1000;

    /**
     * 上一次选举时间
     */
    public volatile long preElectionTime = 0;

    /**
     * 上次一心跳时间戳
     */
    public volatile long preHeartBeatTime = 0;

    /**
     * 心跳间隔基数
     */
    public final long heartBeatTick = 5 * 1000;

    private HeartBeatJob heartBeatJob = new HeartBeatJob();

    private ElectionJob electionJob = new ElectionJob();

    /* -------------- 所有服务器上持久存在的 ----------- */

    /** 服务器最后一次知道的任期号（初始化为 0，持续递增） */
    volatile long currentTerm = 0;
    /** 在当前获得选票的候选人的 Id */
    volatile String votedFor;
    /** 日志条目集；每一个条目包含一个用户状态机执行的指令，和收到时的任期号 */
    LogModule logModule;



    /* --------------- 所有服务器上经常变的 ------------- */

    /** 已知的最大的已经被提交的日志条目的索引值 */
    volatile long commitIndex;

    /** 最后被应用到状态机的日志条目索引值（初始化为 0，持续递增) */
    volatile long lastApplied = 0;

    /* --------------- 在领导人里经常改变的(选举后重新初始化) --------------- */

    /** 对于每一个服务器，需要发送给他的下一个日志条目的索引值（初始化为领导人最后索引值加一） */
    Map<Peer, Long> nextIndexs;

    /** 对于每一个服务器，已经复制给他的日志的最高索引值 */
    Map<Peer, Long> matchIndexs;


    private NodeConfig nodeConfig;

    private RpcClient rpcClient = new DefaultRpcClient();

    private RpcServer rpcServer;

    private Store store;

    private PeerSet peerSet;



    private static class DefaultNodeLazyHolder {
        private static final DefaultNode INSTANCE = new DefaultNode();
    }

    private static DefaultNode getInstance() {
        return DefaultNodeLazyHolder.INSTANCE;
    }

    @Override
    public void init() {
        if (!start.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
            return;
        }
        synchronized (this) {
            rpcServer.init();

            DefaultThreadPool.scheduleWithFixedDelay(heartBeatJob, 500);
            DefaultThreadPool.scheduleAtFixedRate(electionJob, 5000, 500);
        }
    }


    @Override
    public void setConfig(NodeConfig config) {
        this.nodeConfig = config;
        this.store = DefaultStore.getInstance();
        this.logModule = DefaultLogModule.getInstance();
        List<String> otherAddresses = config.getOtherAddresses();
        if (CollectionUtils.isEmpty(otherAddresses)) {
            throw new RuntimeException("address can not be null ！");
        }
        peerSet = PeerSet.getInstance();
        try {
            List<Peer> peers = otherAddresses.stream().map(i -> splitter.splitToList(i))
                    .map(i -> new Peer(i.get(0), Integer.valueOf(i.get(1))))
                    .peek(i -> {
                        if (Objects.equals(i.getAddr(), config.getSelfAddress())
                                && Objects.equals(i.getPort(), i.getPort())) {
                            peerSet.setSelf(i);
                        }
                    }).collect(Collectors.toList());
            peerSet.setList(peers);
        } catch (Exception e) {
            log.error("格式化节点地址失败！ ", e);
            throw e;
        }
        rpcServer = new DefaultRpcServer(config.getSelfPort(), this);
    }

    @Override
    public VoteResult handleRequestVote(VoteParam param) {
        return null;
    }

    @Override
    public EntryResult handleRequestAppendLog(EntryParam entryParam) {
        return null;
    }

    @Override
    public EntryResult handleClientRequest(ClientPairReq clientPairReq) {
        return null;
    }


    @Override
    public void destroy() {

    }

    /**
     * 客户端和服务端之间的心跳
     *
     */
    class HeartBeatJob implements Runnable{

        @Override
        public void run() {

        }
    }

    /**
     * 1. 在转变成候选人后就立即开始选举过程
     *      自增当前的任期号（currentTerm）
     *      给自己投票
     *      重置选举超时计时器
     *      发送请求投票的 RPC 给其他所有服务器
     * 2. 如果接收到大多数服务器的选票，那么就变成领导人
     * 3. 如果接收到来自新的领导人的附加日志 RPC，转变成跟随者
     * 4. 如果选举过程超时，再次发起一轮选举
     */
    class ElectionJob implements Runnable {

        @Override
        public void run() {

        }
    }
}
