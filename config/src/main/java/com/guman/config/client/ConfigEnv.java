package com.guman.config.client;

import com.google.common.io.Files;
import com.guman.common.io.ResourceReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static com.guman.common.constant.CommonConstants.SPLITTER_COMMA;

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

    public static final String defaultConfigFileDir = new File("").getAbsolutePath();

    private final File configStoreHome;

    private final List<String> defaultServerAddresses;

    private static Logger logger = LoggerFactory.getLogger(ConfigEnv.class);

    public ConfigEnv() {
        super(new ClassPathResource("config_setting.properties"), true);
        setupProfile();
        this.defaultServerAddresses = setupDefaultServerAddresses();
        this.configStoreHome = setupHome();
    }

    private void setupProfile() {
        String profile = System.getProperty(profileKey);
        try {
            if (!StringUtils.hasText(profile)) {
                Resource resource = new ClassPathResource(profile);
                if (resource.exists()) {
                    profile = Files.readFirstLine(resource.getFile(), Charset.defaultCharset());
                }
            }
        } catch (IOException e) {
            logger.error("读取{}文件失败！，{}使用\"\"代替", profileKey, profile);
        }
        if (!StringUtils.hasText(profile)) {
            profile = "";
        }

        this.getTarget().put(profileKey, profile);
    }

    private File setupHome() {
        File home = new File(defaultConfigFileDir, "config");
        try {
            if (!home.exists()) {
                home.mkdirs();
            }
            if (!home.isDirectory()) {
                throw new IllegalStateException(home.getAbsolutePath() + "必须是目录!");
            }
            home.setReadable(true, true);
        }catch (Throwable e) {
            throw new IllegalStateException("初始化配置中心本地目录失败" + home.getAbsolutePath(), e);
        }
        this.getTarget().put(configStoreHomeKey, home.getAbsolutePath());
        return home;
    }

    private List<String> setupDefaultServerAddresses() {
        String defaultServerAddresses = getString(defaultServerAddressesKey);
        return SPLITTER_COMMA.splitToList(defaultServerAddresses);
    }


    public File getConfigStoreHome() {
        return configStoreHome;
    }
}
