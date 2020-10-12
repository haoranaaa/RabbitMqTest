package com.guman.springbootguman.aspect;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author duanhaoran
 * @since 2020/4/8 11:34 AM
 */
@Component
public class UserRedisKeyInfoContainer {

    private static final Logger logger = LoggerFactory.getLogger(UserRedisKeyInfoContainer.class);

    private static final String UID_FORMAT = "uid:%s";

    private static final String TEAM_FORMAT = "team:%s";

    private static final Integer INITIAL_SIZE = 1024;

    private Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS)
            .recordStats()
            .concurrencyLevel(20)
            .initialCapacity(INITIAL_SIZE)
            .build();

    private ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void init() {
        schedule.scheduleAtFixedRate(this::monitor, 1, 10, TimeUnit.SECONDS);
    }

    public void setUserValue(String uid, String roomId, String redisGatewayKey) {
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(redisGatewayKey)) {
            return;
        }
        logger.info("set uid:{}, roomId:{}, redisGatewayKey:{} to container.", uid, roomId, redisGatewayKey);
        cache.put(String.format(UID_FORMAT, uid), redisGatewayKey);
        if (!StringUtils.isEmpty(roomId)) {
            cache.put(String.format(TEAM_FORMAT, roomId), redisGatewayKey);
        }
    }

    public void delUserKey(String uid, String roomId, String redisGatewayKey) {
        String key = String.format(UID_FORMAT, uid);
        cache.invalidate(key);
        if (!StringUtils.isEmpty(roomId)) {
            cache.invalidate(String.format(TEAM_FORMAT, roomId));
        }
    }

    public String getUidRedisInfoValue(String uid) {
        if (uid == null) {
            return null;
        }
        return cache.getIfPresent(String.format(UID_FORMAT, uid));
    }

    public String getTeamRedisInfoValue(String roomId) {
        if (roomId == null) {
            return null;
        }
        return cache.getIfPresent(String.format(TEAM_FORMAT, roomId));
    }

    private void monitor() {
        logger.info("userRedisKey container stat : {}", cache.stats().toString());
    }

}
