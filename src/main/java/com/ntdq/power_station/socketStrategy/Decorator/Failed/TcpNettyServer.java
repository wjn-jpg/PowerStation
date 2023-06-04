package com.ntdq.power_station.socketStrategy.Decorator.Failed;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TcpNettyServer implements TcpNetty {
    @Override
    public AbstractBootstrap generateBootStrap() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        return new ServerBootstrap().group(bossGroup,workerGroup).channel(NioServerSocketChannel.class);
    }
}
