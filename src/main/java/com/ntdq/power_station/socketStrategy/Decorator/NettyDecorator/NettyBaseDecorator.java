package com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator;

import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;

import java.util.Map;

public class NettyBaseDecorator implements INetty {

    protected AbstractBootstrap commonBootstrap;

    protected INetty iNetty;

    protected NettyConfiguration nettyConfiguration;

    public NettyBaseDecorator() {
    }

    public NettyBaseDecorator(INetty netty, NettyConfiguration nettyConfiguration) {
        Map<ChannelOption, Object> optionValueMap = nettyConfiguration.getOptionValueMap();
        this.iNetty = netty;
        this.nettyConfiguration = nettyConfiguration;
        commonBootstrap = netty.generateBootStrap();
        if (commonBootstrap instanceof Bootstrap) {
            Bootstrap bootstrap = (Bootstrap) commonBootstrap;
            for (Map.Entry<ChannelOption, ?> channelOptionEntry : optionValueMap.entrySet()) {
                if (channelOptionEntry.getValue() instanceof Integer) {
                    Integer value = (Integer) channelOptionEntry.getValue();
                    bootstrap.option(channelOptionEntry.getKey(), value);
                } else if (channelOptionEntry.getValue() instanceof Boolean) {
                    Boolean value = (Boolean) channelOptionEntry.getValue();
                    bootstrap.option(channelOptionEntry.getKey(), value);
                }
            }
        } else if (commonBootstrap instanceof ServerBootstrap) {
            ServerBootstrap serverBootstrap = (ServerBootstrap) commonBootstrap;
            for (Map.Entry<ChannelOption, ?> channelOptionEntry : optionValueMap.entrySet()) {
                if (channelOptionEntry.getValue() instanceof Integer) {
                    Integer value = (Integer) channelOptionEntry.getValue();
                    serverBootstrap.option(channelOptionEntry.getKey(), value);
                } else if (channelOptionEntry.getValue() instanceof Boolean) {
                    Boolean value = (Boolean) channelOptionEntry.getValue();
                    serverBootstrap.option(channelOptionEntry.getKey(), value);
                }
            }
        }
    }


    @Override
    public AbstractBootstrap generateBootStrap() {
        return iNetty.generateBootStrap();
    }

    @Override
    public AbstractBootstrap initHandler() {
        return iNetty.initHandler();
    }

    @Override
    public ChannelFuture connectAndBindServer() {
        return iNetty.connectAndBindServer();
    }

    @Override
    public void closeNettyGracefully() {
        iNetty.closeNettyGracefully();
    }
}
