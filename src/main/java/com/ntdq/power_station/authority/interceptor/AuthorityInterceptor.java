package com.ntdq.power_station.authority.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ntdq.power_station.authority.component.LoginUserContext;
import com.ntdq.power_station.authority.service.LoginUserRoleService;
import com.ntdq.power_station.authority.utils.TokenBuilder;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限拦截器
 *
 * @author wang_ji_nian
 */
public class AuthorityInterceptor implements BaseInterceptor {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    /**
     * 拦截的URL地址
     */
    private String requestInterceptUrl;

    /**
     * 过滤的URL地址
     */
    private String requestAllowUrl;

    /**
     * 路径正则处理
     */
    private PathMatcher pathMatcher = new AntPathMatcher();

    /**
     * tokenBuilder
     */
    private TokenBuilder tokenBuilder;

    /**
     * 登录用户角色
     */
    private LoginUserRoleService loginUserRoleService;

    public AuthorityInterceptor(TokenBuilder tokenBuilder, LoginUserRoleService loginUserRoleService) {
        this.tokenBuilder = tokenBuilder;
        this.loginUserRoleService = loginUserRoleService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求的URL
        String requestUrl = request.getRequestURI();
        logger.info("权限请求拦截器,请求地址:{}", request);
        if (this.checkRequestAllowUrl(requestUrl)) {
            return BaseInterceptor.super.preHandle(request, response, handler);
        }
        //非法请求资源
        if (!this.checkInterceptUrlArray(requestUrl)) {
            this.outputErrorResponse(response, "无效资源");
            return false;
        }
        String token = request.getHeader("token");
        //JWT解析Token查看Token是否有效
        DecodedJWT jwt = this.tokenBuilder.verify(token);
        if (ObjectUtils.isEmpty(jwt)) {
            this.outputErrorResponse(response, "非法访问");
            return false;
        }
        if (this.tokenBuilder.checkTokenCache(token)) {
            this.outputErrorResponse(response, "登陆已过期,请重新登陆");
            return false;
        }
        this.setLoginUserContext(token);
        return BaseInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        BaseInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除登陆信息
        LoginUserContext.clear();
    }

    private void setLoginUserContext(String token) {
        Claims claims = this.tokenBuilder.getClaims(token);
        String loginUserName = claims.get(TokenBuilder.CLAIMS_KEY_LOGIN_USER_NAME, String.class);
        Long loginUserId = claims.get(TokenBuilder.CLAIMS_KEY_LOGIN_USER_ID, Long.class);
        LoginUserContext.setLoginUser(loginUserName, loginUserId);
        //判断权限是否存在,不存在重新从DB中获得
        if (CollectionUtils.isEmpty(LoginUserContext.getLoginUserRoleList())) {
            //TODO
        }
    }

    /**
     * 判断请求的URL是否为过滤的URL
     *
     * @return 是否为过滤URL
     */
    private boolean checkRequestAllowUrl(String requestUrl) {
        String[] allowUrlArray = this.requestAllowUrl.split(",");
        return this.matchPath(allowUrlArray, requestUrl);
    }

    /**
     * Check 路径正则
     *
     * @param patternPathArray
     * @param requestUrl
     * @return
     */
    private boolean matchPath(String[] patternPathArray, String requestUrl) {
        for (String patternPath : patternPathArray) {
            if (this.pathMatcher.match(patternPath, requestUrl)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断请求URL是否为需要进行过滤
     * @param requestUrl
     * @return
     */
    private boolean checkInterceptUrlArray(String requestUrl) {
        String[] interceptUrlArray = this.requestInterceptUrl.split(",");
        return this.matchPath(interceptUrlArray, requestUrl);
    }

}
