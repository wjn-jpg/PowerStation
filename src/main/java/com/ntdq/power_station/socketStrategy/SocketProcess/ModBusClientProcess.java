package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.mqtt.MqttServerBOBase;
import com.ntdq.power_station.config.netDomain.tcp.ModbusServerBOBase;
import com.ntdq.power_station.nettyHandler.tcp.init.client.TcpModBusClientHandlerInitializer;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandlerPostProcessor;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyChannelFutureDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyHandlerDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.ITcpNettyClient;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModBusClientProcess implements ServerProcess {

    private final static Logger logger = LoggerFactory.getLogger(MqttServerBOBase.class);

    private NioEventLoopGroup bossGroup;

    private Channel modBusClientChannel;

    private boolean isKeepAlive;

    private static ModbusServerBOBase modbusServerBOBase;

    private static final List<Thread> threadList = new ArrayList<>();

    static class ModBusHandler implements ChannelHandlerPostProcessor {

    }

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        modbusServerBOBase = (ModbusServerBOBase) appConfiguration;
        HashMap<ChannelOption, Object> channelOptionMap = new HashMap<>();
        channelOptionMap.put(ChannelOption.SO_BACKLOG, 128);
        channelOptionMap.put(ChannelOption.SO_KEEPALIVE, true);
        NettyConfiguration nettyConfiguration = new NettyConfiguration()
                .setOptionValueMap(channelOptionMap)
                .setHandlerClass(TcpModBusClientHandlerInitializer.class)
                .setChannelHandlerPostProcessor(new ModBusHandler())
                .setServerName("ModBus服务");

        INetty tcpNettyClient = new ITcpNettyClient(modbusServerBOBase.getModbusIp(), modbusServerBOBase.getModbusPort());
        tcpNettyClient = new NettyHandlerDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.initHandler();
        tcpNettyClient = new NettyChannelFutureDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.connectAndBindServer();
    }

    //    @Override
//    public void startServer(ServerBaseConfiguration appConfiguration) {
//        ModbusServerBOBase modbusServerBOBase = null;
//        if (appConfiguration instanceof ModbusServerBOBase) {
//            modbusServerBOBase = (ModbusServerBOBase) appConfiguration;
//        } else {
//            logger.info("ModBus对应的配置对象不匹配...");
//            return;
//        }
//        if (Thread.currentThread().getId() != modbusServerBOBase.getCurrentThreadId()) {
//            logger.info("此服务已启用,当前服务端通信方式:ModBus");
//        }
//        bossGroup = new NioEventLoopGroup();
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(bossGroup).channel(NioSocketChannel.class)
//                .option(ChannelOption.SO_BACKLOG, 128)
//                .option(ChannelOption.SO_KEEPALIVE, true)
//                .handler(new TcpModBusClientHandlerInitializer());
//        ChannelFuture connect = null;
//        try {
//            //sync方法等待connect建立连接完毕 不然会异步
//            connect = bootstrap.connect(modbusServerBOBase.getModbusIp(), modbusServerBOBase.getModbusPort()).sync();
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info("ModBus客户端连接失败...");
//        }
//        connect.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                if (!channelFuture.isSuccess()) {
//                    logger.info("监测到ModBus服务连接断开 尝试重连...");
//                }
//            }
//        });
//        if (connect.isSuccess()) {
//            modBusClientChannel = connect.channel();
//            isKeepAlive = true;
//            logger.info("ModBus服务已连接 地址为:{} 端口为:{}", modbusServerBOBase.getModbusIp(),modbusServerBOBase.getModbusPort());
//            modBusClientChannel.closeFuture().addListener((listener) -> {
//                logger.info("监测到ModBus客户端断开连接...");
//                isKeepAlive = false;
//                bossGroup.shutdownGracefully();
//            });
//        } else {
//            logger.info("ModBus服务器连接失败...");
//        }
//    }

    @Override
    public void stopServer() {
        //关闭一系列线程
    }

}
