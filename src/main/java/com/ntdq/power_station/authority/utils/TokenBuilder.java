package com.ntdq.power_station.authority.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public final class TokenBuilder {

    private final static Logger logger = LoggerFactory.getLogger(TokenBuilder.class);

    public final static String CLAIMS_KEY_LOGIN_USER_NAME = "loginUserName";

    public final static String CLAIMS_KEY_PLATFORM = "platform";

    public final static String CLAIMS_KEY_LOGIN_USER_ID = "loginUserId";
    /**
     * 默认有效时间为8个小时
     */
    @Value("${loginUser.token.timeout}")
    private long timeout = 8 * 3600000L;

    /**
     * 签发人
     */
    @Value("${loginUser.token.subject}")
    private String subject = "ntdq";

    /**
     * 密钥
     */
//    @Value("${loginUser.token.secretKey}")
    private String secretKey = "com.secretKey";

    /**
     * 数据签名加密方式
     */
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    /**
     * 接受方信息
     */
    private String audience = "User";

    private RedisTemplate redisTemplate;

    public TokenBuilder(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置接受方信息
     *
     * @param audience 接受方信息
     * @return 当前TokenBuilder对象
     */
    public TokenBuilder setAudience(String audience) {
        this.audience = audience;
        return this;
    }

    /**
     * 根据用户名和平台类型生成Token
     *
     * @param loginUserName 登陆用户名
     * @param platform      平台类型
     * @return Token
     */
    public String generateToken(String loginUserName, String platform,Long loginUserId) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put(CLAIMS_KEY_LOGIN_USER_NAME, loginUserName);
        claims.put(CLAIMS_KEY_PLATFORM, platform);
        claims.put(CLAIMS_KEY_LOGIN_USER_ID, loginUserId);
        logger.info("有效时间:{},签发人:{},接受方信息:{},算法密钥:{}", new Object[]{this.timeout, this.audience, this.subject, this.secretKey});
        //生成JWT的时间
        long currentTimeMillis = System.currentTimeMillis();
        //jwt的签发时间
        Date currentDate = new Date(currentTimeMillis);
        JwtBuilder builder = Jwts.builder()
                .setAudience(this.audience)
                .setIssuedAt(currentDate)
                //生成签发人
                .setSubject(this.subject)
                //创建payload的私有声明
                .setClaims(claims)
                //使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey);
        long expMillis = 0L;
        if (timeout > 0) {
            expMillis = currentTimeMillis + timeout;
            Date expiration = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(expiration);
        }
        String tokenValue = builder.compact();
        //设置Token Value 缓存
        this.redisTemplate.opsForValue().set(platform + "*" + loginUserName, tokenValue, expMillis, TimeUnit.MILLISECONDS);
        return tokenValue;
    }


    /**
     * 校验token是否正确
     */
    public DecodedJWT verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    //在token失效前提供一个安全窗口期，使前端有机会刷新token
                    //注意这里的单位为秒
                    .acceptExpiresAt(5 * 60)
                    .build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public Claims getClaims(String tokenValue) {
        Claims claims = Jwts.parser()
                // 设置对应的盐
                .setSigningKey(this.secretKey)
                //Token Value
                .parseClaimsJws(tokenValue)
                .getBody();
        return claims;
    }

    public String getLoginUserName(String tokenValue) {
        Claims claims = this.getClaims(tokenValue);
        return claims.get(CLAIMS_KEY_LOGIN_USER_NAME, String.class);
    }

    public boolean checkTokenCache(String token) {
        Claims claims = this.getClaims(token);
        String loginUserName = claims.get(CLAIMS_KEY_LOGIN_USER_NAME, String.class);
        String platform = claims.get(CLAIMS_KEY_PLATFORM, String.class);
        Object cacheToken = this.redisTemplate.opsForValue().get(platform + "*" + loginUserName);
        return ObjectUtils.isEmpty(cacheToken);
    }

    public void removeToken(String token) {
        Claims claims = this.getClaims(token);
        String loginUserName = claims.get(CLAIMS_KEY_LOGIN_USER_NAME, String.class);
        String platform = claims.get(CLAIMS_KEY_PLATFORM, String.class);
        this.redisTemplate.delete(platform + "*" + loginUserName);
    }

}
