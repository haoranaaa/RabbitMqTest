package com.guman.common.result;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.guman.common.exception.StatusCodeException;
import com.guman.common.json.JsonResult;
import com.guman.common.pojo.Status;

import java.io.Serializable;
/**
 * @author duanhaoran
 * @since 2019/4/15 8:25 PM
 */

public class RpcResult<T> implements Serializable {

    private final Status statusCode;

    private final T data;

    @JsonCreator
    protected RpcResult(@JsonProperty("statusCode") Status statusCode, @JsonProperty("data") T data) {
        this.statusCode = statusCode == null ? Status.success() : statusCode;
        this.data = data;
    }

    public static <T> RpcResult<T> create(int status, String reason, T data) {
        return create(Status.create(status, reason), data);
    }

    public static <T> RpcResult<T> create(Status statusCode, T data) {
        return new RpcResult<>(statusCode, data);
    }

    public static <T> RpcResult<T> create(JsonResult<T> rpcResult) {
        return create(rpcResult.getStatus(), rpcResult.getMsg(), rpcResult.getData());
    }

    public static <T> RpcResult<T> success(T data) {
        return create(Status.success(), data);
    }

    public static <T> RpcResult<T> error(int status, String reason) {
        return create(Status.error(status, reason), null);
    }

    public static <T> RpcResult<T> error(String reason) {
        return create(Status.error(reason), null);
    }

    public static <T> RpcResult<T> error(StatusCodeException e) {
        Status statusCode = e.getStatusCode();
        return create(statusCode, null);
    }

    public T checkAndGet() throws StatusCodeException {
        if (isSuccess()) {
            return getData();
        }
        throw statusCode.asError();
    }

    @JsonIgnore
    public boolean isSuccess() {
        return statusCode.isSuccess();
    }

    public T getData() {
        return data;
    }

    public Status getStatusCode() {
        return statusCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpcResult)) return false;

        RpcResult<?> rpcResult = (RpcResult<?>) o;

        if (!statusCode.equals(rpcResult.statusCode)) return false;
        return data != null ? data.equals(rpcResult.data) : rpcResult.data == null;
    }

    @Override
    public int hashCode() {
        int result = statusCode.hashCode();
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RpcResult{" +
                "statusCode=" + statusCode +
                ", data=" + data +
                '}';
    }
}
