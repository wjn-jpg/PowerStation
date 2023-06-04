package com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator;

import com.ntdq.power_station.cache.NettyContext;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyChannelFutureDecorator extends NettyBaseDecorator {

    private final static Logger logger = LoggerFactory.getLogger(NettyChannelFutureDecorator.class);


    public NettyChannelFutureDecorator(INetty netty, NettyConfiguration nettyConfiguration) {
        this.iNetty = netty;
        this.nettyConfiguration = nettyConfiguration;
    }


    @Override
    public ChannelFuture connectAndBindServer() {
        ChannelFuture channelFuture = this.iNetty.connectAndBindServer();
        if (nettyConfiguration.getChannelHandlerPostProcessor() != null) {
            nettyConfiguration.getChannelHandlerPostProcessor().setChannelFutureAfterConnectOrBind(channelFuture);
        }
        if (channelFuture.isSuccess()) {
            channelFuture.channel().closeFuture().addListener((listener) -> {
                logger.info("监测到:{}断开连接...", nettyConfiguration.getServerName());
                this.iNetty.closeNettyGracefully();
                NettyContext.removeExtraThread(nettyConfiguration.getServerName());
                NettyContext.removeExtraThreadPool(nettyConfiguration.getServerName());
            });
        }
        return channelFuture;
    }
}
