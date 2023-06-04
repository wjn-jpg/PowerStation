package com.ntdq.power_station.socketStrategy.Decorator.Failed;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于配置参数 和 添加对应的handler
 */
public class TcpNettyDecorator implements TcpNetty{

    private AbstractBootstrap commonBootstrap;

    private TcpNetty tcpNetty;

    public TcpNettyDecorator(TcpNetty tcpNetty, Map<ChannelOption, Object> channelOptionMap) {
        this.tcpNetty = tcpNetty;
        commonBootstrap = tcpNetty.generateBootStrap();
        if (commonBootstrap instanceof Bootstrap) {
            Bootstrap serverBootstrap = (Bootstrap) commonBootstrap;
            for (Map.Entry<ChannelOption, ?> channelOptionEntry : channelOptionMap.entrySet()) {
                if (channelOptionEntry.getValue() instanceof Integer) {
                    Integer value = (Integer) channelOptionEntry.getValue();
                    serverBootstrap.option(channelOptionEntry.getKey(), value);
                } else if (channelOptionEntry.getValue() instanceof Boolean) {
                    Boolean value = (Boolean) channelOptionEntry.getValue();
                    serverBootstrap.option(channelOptionEntry.getKey(),value);
                }
            }
        }
    }


    public static void main(String[] args) {
        TcpNetty tcpNetty = new TcpNettyClient();
        Map<ChannelOption,Object> map = new HashMap<>();
        map.put(ChannelOption.SO_BACKLOG,128);
        map.put(ChannelOption.SO_KEEPALIVE,true);
        new TcpNettyDecorator(new TcpNettyClient(),map);
    }


    @Override
    public AbstractBootstrap generateBootStrap() {
        return tcpNetty.generateBootStrap();
    }
}
