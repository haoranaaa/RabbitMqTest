package com.guman.raft.exception;

/**
 * @author duanhaoran
 * @since 2019/7/28 6:53 PM
 */
public class RaftRemoteException extends RuntimeException {

    public RaftRemoteException() {
        super();
    }

    public RaftRemoteException(String msg) {
        super(msg);
    }
}
