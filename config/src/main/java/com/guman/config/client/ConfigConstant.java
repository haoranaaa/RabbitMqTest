package com.guman.config.client;

/**
 * @author duanhaoran
 * @since 2019/4/15 2:22 PM
 */
public class ConfigConstant {
    public static final int CONNECTION_TIMEOUT = 1000;

    public static final int REQUEST_TIMEOUT = 2000;

    public static final String CHECK_UPDATE_URL = "/wconfig/srv/%s/release/_check/v1";

    public static final String LOAD_DATA_URL = "/wconfig/srv/%s/snapshot/_fetch/v1";

    public static final String FORCE_RELOAD_URL = "/wconfig/srv/%s/release/_fetch/v1";

    public static final String RECORD_LOADING_URL = "/wconfig/srv/%s/record/v1";

    public static final String UPDATE_DATA_URL = "/wconfig/srv/%s/update/v1";

    public static final String BATCH_UPDATE_DATA_URL = "/wconfig/srv/%s/batchUpdate/v1";

    public static final String BYTEBUF_ALLOCATOR_POOL = "pooled";
}
