package com.ntdq.power_station.authority.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class LoginComponent {

    /**
     * 刷新Token缓存Key
     */
    public final String REFRESH_TOKEN_CACHE_KEY = "RefreshTokenCacheKey";

    private RedisTemplate redisTemplate;

    /**
     * 构造方法赋值redisTemp
     *
     * @param redisTemplate
     */
    public LoginComponent(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 生成Refresh Token
     *
     * @return
     */
    public String generateRefreshToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static class RefreshToken implements Serializable {

        private String refreshToken;

        private String loginToken;

    }


}
