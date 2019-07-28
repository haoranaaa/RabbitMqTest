package com.guman.raft.rpc;

import com.alipay.remoting.exception.RemotingException;
import com.guman.raft.exception.RaftRemoteException;
import com.guman.raft.rpc.model.Request;
import com.guman.raft.rpc.model.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duanhaoran
 * @since 2019/7/28 6:47 PM
 */
@Slf4j
public class DefaultRpcClient implements RpcClient{

    private static final com.alipay.remoting.rpc.RpcClient rpcClient = new com.alipay.remoting.rpc.RpcClient();

    private static final Integer DEFAULT_TIME_OUT = 2000;

    static {
        rpcClient.init();
    }

    @Override
    public Response send(Request request) {
        return this.send(request, DEFAULT_TIME_OUT);
    }

    @Override
    public Response send(Request request, int timeOut) {
        try {
            rpcClient.invokeSync(request.getUrl(), request.getObj(), timeOut);
        } catch (RemotingException e) {
            log.error("read address error ! ", e);
            throw new RaftRemoteException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response sendAsync(Request request) {
        return null;
    }
}
