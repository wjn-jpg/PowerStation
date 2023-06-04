package com.ntdq.power_station.socketStrategy.Decorator.NettyProcess;

import com.ntdq.power_station.nettyHandler.udp.power.HeartCheckHandler;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ITcpNettyServer extends INettyProcessor {

    private final static Logger logger = LoggerFactory.getLogger(ITcpNettyServer.class);

    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workerGroup;

    private static NettyConfiguration nettyConfiguration;

    public ITcpNettyServer(String host, Integer port, String serverName) {
        super(host, port, serverName);
    }

    @Override
    public AbstractBootstrap generateBootStrap() {
        if (bootstrap != null && workerGroup != null && bootstrap != null) {
            return bootstrap;
        }
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        this.bootstrap = serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class);
        return bootstrap;
    }

    static class ActiveMessage extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
//            logger.info("{}服务监测到连接端是否上线:{}", nettyConfiguration.getServerName(), ctx.channel().isActive());
//            if (nettyConfiguration.getChannelHandlerPostProcessor() != null) {
//                nettyConfiguration.getChannelHandlerPostProcessor().setChannelAfterServerHaveClient(ctx.channel());
//            }
        }
    }

    @Override
    public AbstractBootstrap initHandler() {
        //先添加一些默认的Handler new IdleStateHandler
        return bootstrap.handler(new ActiveMessage());
    }

    public void setNettyConfiguration(NettyConfiguration nettyConfiguration) {
        this.nettyConfiguration = nettyConfiguration;
    }

    @Override
    public ChannelFuture connectAndBindServer() {
        setNettyConfiguration(nettyConfiguration);
        if (bootstrap != null) {
            ServerBootstrap STRAP = (ServerBootstrap) bootstrap;
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
