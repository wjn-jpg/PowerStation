package com.ntdq.power_station.socketStrategy.Decorator.NettyProcess;

import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ITcpNettyClient extends INettyProcessor {

    private final static Logger logger = LoggerFactory.getLogger(ITcpNettyServer.class);

    private NioEventLoopGroup bossGroup;

    public ITcpNettyClient(String host, Integer port) {
        super(host, port);
    }

    @Override
    public AbstractBootstrap generateBootStrap() {
        if (bossGroup != null && bootstrap != null) {
            return bootstrap;
        }
        bossGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap().group(bossGroup).channel(NioSocketChannel.class);
        return bootstrap;
    }


    @Override
    public AbstractBootstrap initHandler() {
        //先添加一些默认的Handler
        return bootstrap;
    }

    @Override
    public ChannelFuture connectAndBindServer() {
        if (bootstrap != null) {
            Bootstrap STRAP = (Bootstrap) bootstrap;
            try {
                if (this.channelFuture != null && channelFuture.isSuccess()) {
                    return this.channelFuture;
                }
                this.channelFuture = STRAP.connect(getHost(), getPort()).sync();
                logger.info("客户端连接{}服务成功:ip:{},port:{}", getServerName(), getHost(), getPort());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return channelFuture;
    }

    @Override
    public void closeNettyGracefully() {
        bossGroup.shutdownGracefully();
    }

}
