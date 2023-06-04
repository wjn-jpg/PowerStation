package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.config.netDomain.udp.PowerStationServerBOBase;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.nettyHandler.udp.init.UdpChannelInitializer;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyChannelFutureDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyHandlerDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.IUdpNettyServer;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UdpServerProcess implements ServerProcess {

    private final static Logger logger = LoggerFactory.getLogger(UdpServerProcess.class);

    private static PowerStationServerBOBase powerStationServerBOBase;
    private Channel serverChannel;

    private NioEventLoopGroup bossGroup;

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        powerStationServerBOBase = (PowerStationServerBOBase) appConfiguration;
        Map<ChannelOption, Object> map = new HashMap<>();
        map.put(ChannelOption.SO_BROADCAST, true);
        NettyConfiguration nettyConfiguration = new NettyConfiguration().setHost(powerStationServerBOBase.getPowerStationAddr())
                .setPort(powerStationServerBOBase.getServerPort())
                .setServerName("充电桩UDP服务")
                .setOptionValueMap(map)
                .setHandlerClass(UdpChannelInitializer.class);

        INetty iUdpNettyServer = new IUdpNettyServer(nettyConfiguration.getHost(), nettyConfiguration.getPort(), nettyConfiguration.getServerName());
        iUdpNettyServer = new NettyHandlerDecorator(iUdpNettyServer, nettyConfiguration);
        iUdpNettyServer.initHandler();
        iUdpNettyServer = new NettyChannelFutureDecorator(iUdpNettyServer, nettyConfiguration);
        iUdpNettyServer.connectAndBindServer();
    }
//        @Override
//    public void startServer(ServerBaseConfiguration appConfiguration) {
//        PowerStationServerBOBase powerStationBO = null;
//        if (appConfiguration instanceof PowerStationServerBOBase){
//            powerStationBO = (PowerStationServerBOBase)  appConfiguration;
//        }else {
//            logger.info("对应的配置对象不匹配 启动充电塔服务失败...");
//            return;
//        }
//        if (Thread.currentThread().getId()!=powerStationBO.getCurrentThreadId()){
//            logger.info("此服务已启用,当前服务端通信方式:UDP");
//            return;
//        }
//        try {
//            bossGroup = new NioEventLoopGroup();
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(bossGroup).channel(NioDatagramChannel.class)
//                    .option(ChannelOption.SO_BROADCAST, true)//支持广播
//                    .handler(new UdpChannelInitializer());
//            ChannelFuture channelFuture = bootstrap.bind(powerStationBO.getPowerStationAddr(), powerStationBO.getServerPort()).sync();
//            if (channelFuture.isSuccess()){
//                logger.info("UDP服务端已经开启 当前服务IP:{},端口:{}", powerStationBO.getPowerStationAddr(), powerStationBO.getServerPort());
//                serverChannel = channelFuture.channel();
//                serverChannel.closeFuture().addListener((listener)->{
//                    bossGroup.shutdownGracefully();
//                    logger.info("UDP Server Stop...");
//                });
//            }
//        }catch (InterruptedException e){
//            e.printStackTrace();
//            this.stopServer();
//        }
//    }

    @Override
    public void stopServer() {
        if (serverChannel != null) {
            serverChannel.close();
            logger.info("关闭ServerChannel...");
        }
    }

}
