package com.ntdq.power_station.socketStrategy.Decorator.NettyProcess;

import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IUdpNettyServer extends INettyProcessor {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(IUdpNettyServer.class);
    private NioEventLoopGroup bossGroup;

    public IUdpNettyServer(String host, Integer port, String serverName) {
        super(host, port, serverName);
    }

    @Override
    public AbstractBootstrap generateBootStrap() {
        bossGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        return bootstrap.group(bossGroup).channel(NioDatagramChannel.class);
    }

    @Override
    public AbstractBootstrap initHandler() {
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
                this.channelFuture = STRAP.bind(getHost(), getPort()).sync();
                logger.info("{}服务端启动成功,ip:{}.port:{}", getServerName(), getHost(), getPort());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return channelFuture;
    }
}
