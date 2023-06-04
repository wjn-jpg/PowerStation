package com.ntdq.power_station.socketStrategy.Decorator.config;

import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandlerPostProcessor;
import com.ntdq.power_station.socketStrategy.Decorator.Function.ChannelFutureContextProcessor;
import com.ntdq.power_station.socketStrategy.Decorator.Function.ChannelHandlerFieldSetProcessor;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import sun.nio.ch.Net;

import java.util.List;
import java.util.Map;

public class NettyConfiguration {

    private Map<ChannelOption, Object> optionValueMap;


    private Class<? extends ChannelHandler> handlerClass;

    private String host;

    private Integer port;

    private String serverName;

    public Map<ChannelOption, Object> getOptionValueMap() {
        return optionValueMap;
    }

    private Map<String, Object> fieldObjMap;

    private ChannelHandlerFieldSetProcessor channelHandlerFieldSetProcessor;

    private ChannelFutureContextProcessor channelFutureContextProcessor;

    private ChannelHandlerPostProcessor channelHandlerPostProcessor;

    public ChannelFutureContextProcessor getChannelFutureContextProcessor() {
        return channelFutureContextProcessor;
    }

    public NettyConfiguration setChannelFutureContextProcessor(ChannelFutureContextProcessor channelFutureContextProcessor) {
        this.channelFutureContextProcessor = channelFutureContextProcessor;
        return this;
    }

    public ChannelHandlerFieldSetProcessor getChannelHandlerFieldSetProcessor() {
        return channelHandlerFieldSetProcessor;
    }

    public NettyConfiguration setChannelHandlerFieldSetProcessor(ChannelHandlerFieldSetProcessor channelHandlerFieldSetProcessor) {
        this.channelHandlerFieldSetProcessor = channelHandlerFieldSetProcessor;
        return this;
    }

    public Map<String, Object> getFieldObjMap() {
        return fieldObjMap;
    }

    public void setFieldObjMap(Map<String, Object> fieldObjMap) {
        this.fieldObjMap = fieldObjMap;
    }

    public NettyConfiguration setOptionValueMap(Map<ChannelOption, Object> optionValueMap) {
        this.optionValueMap = optionValueMap;
        return this;
    }

    public Class<? extends ChannelHandler> getHandlerClass() {
        return handlerClass;
    }

    public NettyConfiguration setHandlerClass(Class<? extends ChannelHandler> handlerClass) {
        this.handlerClass = handlerClass;
        return this;
    }

    public String getHost() {
        return host;
    }

    public NettyConfiguration setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public NettyConfiguration setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getServerName() {
        return serverName;
    }

    public NettyConfiguration setServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    public NettyConfiguration setChannelHandlerPostProcessor(ChannelHandlerPostProcessor channelHandlerPostProcessor) {
        this.channelHandlerPostProcessor = channelHandlerPostProcessor;
        return this;
    }

    public ChannelHandlerPostProcessor getChannelHandlerPostProcessor() {
        return channelHandlerPostProcessor;
    }
}
