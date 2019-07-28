package com.guman.raft.model;

import javafx.util.Pair;

/**
 * @author duanhaoran
 * @since 2019/7/28 7:38 PM
 */
public class ClientPairReq extends Pair<String, String> {

    private Integer type;

    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public ClientPairReq(String key, String value) {
        super(key, value);
    }

    public enum Type {
        PUT(0), GET(1);
        int code;

        Type(int code) {
            this.code = code;
        }

        public static Type value(int code ) {
            for (Type type : values()) {
                if (type.code == code) {
                    return type;
                }
            }
            return null;
        }
    }


}
