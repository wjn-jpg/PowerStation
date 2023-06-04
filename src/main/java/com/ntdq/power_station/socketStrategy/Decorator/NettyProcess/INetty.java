package com.ntdq.power_station.socketStrategy.Decorator.NettyProcess;

import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.ChannelFuture;

public interface INetty {

    AbstractBootstrap generateBootStrap();

    AbstractBootstrap initHandler();

    ChannelFuture connectAndBindServer();

    default void closeNettyGracefully(){

    }

}
