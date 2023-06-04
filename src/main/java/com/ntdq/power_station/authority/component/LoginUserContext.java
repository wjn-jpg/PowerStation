package com.ntdq.power_station.authority.component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ntdq.power_station.authority.utils.SpringContextUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class LoginUserContext {

    private final static ThreadLocal<LoginUserInfo> threadLocal = new ThreadLocal<>();

    public static class LoginUserInfo implements Serializable {

        private String loginUserName;

        private Long loginUserId;

        public LoginUserInfo(String loginUserName, Long loginUserId) {
            this.loginUserName = loginUserName;
            this.loginUserId = loginUserId;
        }

        public String getLoginUserName() {
            return loginUserName;
        }

        public void setLoginUserName(String loginUserName) {
            this.loginUserName = loginUserName;
        }

        public Long getLoginUserId() {
            return loginUserId;
        }

        public void setLoginUserId(Long loginUserId) {
            this.loginUserId = loginUserId;
        }
    }

    public static void setLoginUser(String loginUserName, Long loginUserId) {
        LoginUserInfo loginUserInfo = new LoginUserInfo(loginUserName, loginUserId);
        threadLocal.set(loginUserInfo);
    }

    public static String getLoginUserName() {
        try {
            return threadLocal.get().getLoginUserName();
        } catch (Exception ex) {

        }
        return "admin";
    }

    public static Long getLoginUserId() {
        try {
            return threadLocal.get().getLoginUserId();
        } catch (Exception ex) {

        }
        return 1L;
    }

    public static void clear() {
        threadLocal.remove();
    }

    /**
     * 登录用户角色编码List
     *
     * @return 用户角色编码List
     */
    public static List<String> getLoginUserRoleList() {
        RedisTemplate redisTemplate = SpringContextUtil.getBean("redisTemplate",RedisTemplate.class);
        String loginUserName = getLoginUserName();
        Object loginUserRoleCodeListJson = redisTemplate.opsForValue().get(loginUserName + "*Role");
        if (ObjectUtils.isEmpty(loginUserRoleCodeListJson)) {
            return new ArrayList<>();
        }
        JSONArray jsonArray = JSONObject.parseArray(loginUserRoleCodeListJson.toString());
        return jsonArray.toJavaList(String.class);
    }

    public void initLoginUserRole(String loginUserName, List<String> loginUserRoleCodeList){
        if(CollectionUtils.isEmpty(getLoginUserRoleList())){
            setLoginUserRoleList(loginUserName,loginUserRoleCodeList);
        }
    }

    public static void setLoginUserRoleList(String loginUserName, List<String> loginUserRoleCodeList) {
        RedisTemplate redisTemplate = SpringContextUtil.getBean(RedisTemplate.class);
        String loginUserRoleCodeListJson = new Gson().toJson(loginUserRoleCodeList);
        redisTemplate.opsForValue().set(loginUserName + "*Role", loginUserRoleCodeListJson);
    }

    public static void removeLoginUserRoleList(String loginUserName){
        RedisTemplate redisTemplate = SpringContextUtil.getBean(RedisTemplate.class);
        redisTemplate.delete(loginUserName + "*Role");
    }

}
