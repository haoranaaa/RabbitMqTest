package com.guman.common.json;

import org.springframework.core.NestedRuntimeException;

/**
 * @author duanhaoran
 * @since 2019/4/15 8:01 PM
 */
public class JsonException extends NestedRuntimeException {

    private static final long serialVersionUID = -9198606590046525595L;

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
