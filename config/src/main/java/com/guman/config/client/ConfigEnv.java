package com.guman.config.client;

import com.guman.common.io.ResourceReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author duanhaoran
 * @since 2019/4/12 9:04 PM
 */
public class ConfigEnv extends ResourceReader {

    public static final String pollPeriodInSecondsKey = "scheduler.poll.periodInSeconds";

    public static final String checkOverridePeriodInSecondsKey = "scheduler.checkOverride.periodInSeconds";

    public static final String profileKey = "wconfig.profile";

    public static final String configStoreHomeKey = "config.store.home";

    public static final String configContainerThreadKey = "config.container.threads";

    public static final String clientConnectionTimeoutKey = "client.connection.timeout";

    public static final String clientRequestTimeoutKey = "client.request.timeout";

    public static final String defaultServerAddressesKey = "default.servers";

    public static final String clientByteBufAllocatorKey = "client.byteBuf.allocator";

    public ConfigEnv() {
        super(new ClassPathResource("config_setting.properties"), true);

    }
}
