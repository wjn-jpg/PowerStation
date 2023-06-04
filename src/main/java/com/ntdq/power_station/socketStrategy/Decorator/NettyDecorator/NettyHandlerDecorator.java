package com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator;

import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.ITcpNettyClient;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;

import java.util.HashMap;
import java.util.Map;

public class NettyHandlerDecorator extends NettyBaseDecorator {


    public NettyHandlerDecorator(INetty netty, NettyConfiguration configuration) {
        super(netty, configuration);
    }

    @Override
    public AbstractBootstrap initHandler() {
        try {
            iNetty.initHandler();
            if (this.commonBootstrap instanceof Bootstrap) {
                Bootstrap bootstrap = (Bootstrap) commonBootstrap;
                if (bootstrap.config().handler() != null) {
                    return bootstrap;
                }
                ChannelHandler channelHandler = nettyConfiguration.getHandlerClass().newInstance();
                if (nettyConfiguration.getChannelHandlerPostProcessor() != null) {
                    nettyConfiguration.getChannelHandlerPostProcessor().setChannelHandlerFieldAfterInit(channelHandler);
                }
                return bootstrap.handler(channelHandler);
            } else if (this.commonBootstrap instanceof ServerBootstrap) {
                ServerBootstrap serverBootstrap = (ServerBootstrap) commonBootstrap;
                //防止重复添加InitHandler
                if (serverBootstrap.config().childHandler() != null) {
                    return serverBootstrap;
                }
                ChannelHandler channelHandler = nettyConfiguration.getHandlerClass().newInstance();
                if (nettyConfiguration.getChannelHandlerPostProcessor() != null) {
                    nettyConfiguration.getChannelHandlerPostProcessor().setChannelHandlerFieldAfterInit(channelHandler);
                }
                return serverBootstrap.childHandler(channelHandler);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {
        INetty iTcpNettyClient = new ITcpNettyClient("127.0.0.1", 9999);
        NettyConfiguration nettyConfiguration = new NettyConfiguration();
        Map<ChannelOption, Object> map = new HashMap<>();
        nettyConfiguration.setOptionValueMap(map);
        iTcpNettyClient = new NettyHandlerDecorator(iTcpNettyClient, nettyConfiguration);

    }
}
