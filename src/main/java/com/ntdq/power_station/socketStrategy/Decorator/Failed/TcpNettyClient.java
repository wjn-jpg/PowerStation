package com.ntdq.power_station.socketStrategy.Decorator.Failed;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TcpNettyClient implements TcpNetty{


    @Override
    public AbstractBootstrap generateBootStrap() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        return new Bootstrap().group(bossGroup).channel(NioSocketChannel.class);
    }
}
