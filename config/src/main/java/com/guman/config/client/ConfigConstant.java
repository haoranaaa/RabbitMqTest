package com.guman.config.client;

/**
 * @author duanhaoran
 * @since 2019/4/15 2:22 PM
 */
public class ConfigConstant {
    public static final int CONNECTION_TIMEOUT = 1000;

    public static final int REQUEST_TIMEOUT = 2000;

    public static final String APPLICATION_NAME = "appName";

    public static final String NAME_NAME = "dataId";

    public static final String PROFILE_NAME = "profile";

    public static final String PID_NAME = "pid";

    public static final String PORT_NAME = "port";

    public static final String VERSION_NAME = "version";

    public static final String CONFIG_OPERATOR_TYPE = "typeCode";

    public static final String CHECKSUM_NAME = "checksum";

    public static final String REMARKS_NAME = "remarks";

    public static final String DATA_NAME = "data";

    public static final String FEATURE_NAME = "feature";

    public static final String TOKEN_NAME = "token";

    public static final String CHECK_UPDATE_URL = "/config/srv/%s/release/_check/v1";

    public static final String LOAD_DATA_URL = "/config/srv/%s/snapshot/_fetch/v1";

    public static final String FORCE_RELOAD_URL = "/config/srv/%s/release/_fetch/v1";

    public static final String VIEW_DATA_URL = "/config/srv/%s/release/_view/v1";

    public static final String RECORD_LOADING_URL = "/config/srv/%s/record/v1";

    public static final String UPDATE_DATA_URL = "/config/srv/%s/update/v1";

    public static final String BATCH_UPDATE_DATA_URL = "/config/srv/%s/batchUpdate/v1";

    public static final String BYTEBUF_ALLOCATOR_POOL = "pooled";


    public static final String CONFIG_SETTING = "config_setting.properties";

}
