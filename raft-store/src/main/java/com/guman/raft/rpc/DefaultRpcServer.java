package com.guman.raft.rpc;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.AbstractUserProcessor;
import com.guman.raft.impl.DefaultNode;
import com.guman.raft.model.ClientPairReq;
import com.guman.raft.model.EntryParam;
import com.guman.raft.model.VoteParam;
import com.guman.raft.rpc.model.Request;
import com.guman.raft.rpc.model.Response;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author duanhaoran
 * @since 2019/7/28 7:18 PM
 */
public class DefaultRpcServer implements RpcServer {

    private AtomicBoolean init = new AtomicBoolean(Boolean.FALSE);

    private com.alipay.remoting.rpc.RpcServer rpcServer;

    private DefaultNode node;

    public DefaultRpcServer(int port, DefaultNode node) {
        if (!init.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
            return;
        }
        synchronized (this) {
            rpcServer = new com.alipay.remoting.rpc.RpcServer(port, false, false);
            rpcServer.registerUserProcessor(new AbstractUserProcessor<Request>() {
                @Override
                public void handleRequest(BizContext bizContext, AsyncContext asyncContext, Request request) {
                    throw new RuntimeException(
                            "Raft Server not support handleRequest(BizContext bizCtx, AsyncContext asyncCtx, T request) ");
                }

                @Override
                public Object handleRequest(BizContext bizContext, Request request) throws Exception {
                    return DefaultRpcServer.this.handleRequest(request);
                }

                @Override
                public String interest() {
                    return Request.class.getName();
                }
            });
            this.node = node;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response handleRequest(Request request) {
        switch (request.getCmd()) {
            case Request.R_VOTE:
                return new Response(node.handleRequestVote((VoteParam) request.getObj()));
            case Request.A_ENTRIES:
                return new Response(node.handleRequestAppendLog((EntryParam) request.getObj()));
            case Request.CLIENT_REQ:
                return new Response(node.handleClientRequest((ClientPairReq) request.getObj()));
            case Request.CHANGE_CONFIG_ADD:
            case Request.CHANGE_CONFIG_REMOVE:
            default:
                return null;
        }
    }

    @Override
    public void init() {
        this.rpcServer.start();
    }

    @Override
    public void destroy() {
        this.rpcServer.stop();
    }
}
