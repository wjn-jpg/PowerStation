package com.ntdq.power_station.authority.interceptor;

import com.ntdq.power_station.common.rest.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * 通用拦截器
 * @author wang_ji_nian
 */
public interface BaseInterceptor extends HandlerInterceptor {

    /**
     * 返回JSON格式数据
     * @param servletResponse
     * @param message
     * @throws IOException
     */
    default void outputErrorResponse(ServletResponse servletResponse,String message) throws IOException{
        RestResponse<String> restResponse = RestResponse.createError(message, String.class);
        Gson gson = new GsonBuilder().create();
        String responseJson = gson.toJson(restResponse);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(responseJson);
        out.close();
    }


    default String getRequestBody(HttpServletRequest request){
        String param = "";
        try (InputStream is = request.getInputStream(); InputStreamReader isr = new InputStreamReader(is, Charset.defaultCharset()); BufferedReader br = new BufferedReader(isr)) {
            StringBuffer stringBuffer = new StringBuffer();
            String s = "";
            while ((s = br.readLine()) != null) {
                stringBuffer.append(s);
            }
            param = stringBuffer.toString();
            param = URLDecoder.decode(param, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }


}
