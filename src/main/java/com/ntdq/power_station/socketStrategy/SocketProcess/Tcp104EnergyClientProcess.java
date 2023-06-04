package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.Energy104ClientBOBase;
import com.ntdq.power_station.config.netDomain.tcp.PhotovoltaicServerBOBase;
import com.ntdq.power_station.domain.enumDo.DeviceEnum;
import com.ntdq.power_station.nettyHandler.tcp.init.client.Iec104ClientInitializer;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandler104PostProcessor;
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

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Tcp104EnergyClientProcess implements ServerProcess {
    private final static Logger logger = LoggerFactory.getLogger(PhotovoltaicServerBOBase.class);

    private NioEventLoopGroup bossGroup;


    private Channel iec104ClientChannel;

    private static Energy104ClientBOBase energy104ClientBOBase;

    private boolean isKeepAlive;

    static class EnergyHandler implements ChannelHandlerPostProcessor {
        @Override
        public void setChannelHandlerFieldAfterInit(ChannelHandler channelHandler) {
            if (channelHandler instanceof Iec104ClientInitializer) {
                Iec104ClientInitializer iec104ClientInitializer = (Iec104ClientInitializer) channelHandler;
                iec104ClientInitializer.setConfiguration(energy104ClientBOBase);
                iec104ClientInitializer.putDeviceClientHandlerManual(energy104ClientBOBase);
            }
        }

//        @Override
//        public void setChannelFutureAfterConnectOrBind(ChannelFuture channelFuture) {
//            Iec104ClientInitializer.getClientHandler(energy104ClientBOBase.getDeviceEnum()).setChannel(channelFuture.channel());
//        }
    }

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        energy104ClientBOBase = (Energy104ClientBOBase) appConfiguration;
        energy104ClientBOBase.setDeviceEnum(DeviceEnum.Energy);
        NettyConfiguration nettyConfiguration = new NettyConfiguration();
        HashMap<ChannelOption, Object> channelOptionMap = new HashMap<>();
        channelOptionMap.put(ChannelOption.SO_BACKLOG, 128);
        channelOptionMap.put(ChannelOption.SO_KEEPALIVE, true);
        nettyConfiguration.setOptionValueMap(channelOptionMap)
                .setHandlerClass(Iec104ClientInitializer.class)
                .setChannelHandlerPostProcessor(new EnergyHandler())
                .setServerName("104储能服务");

        INetty tcpNettyClient = new ITcpNettyClient(energy104ClientBOBase.getAddr104(), energy104ClientBOBase.getPort104());
        tcpNettyClient = new NettyHandlerDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.initHandler();
        tcpNettyClient = new NettyChannelFutureDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.connectAndBindServer();

    }
    //    @Override
//    public void startServer(ServerBaseConfiguration appConfiguration) {
//        Energy104ClientBOBase energy104ClientBOBase = null;
//        if (appConfiguration instanceof Energy104ClientBOBase) {
//            energy104ClientBOBase = (Energy104ClientBOBase) appConfiguration;
//            serverBaseConfiguration = energy104ClientBOBase;
//        } else {
//            logger.info("对应的配置对象不匹配 启动104客户端失败...");
//            return;
//        }
//        if (Thread.currentThread().getId() != energy104ClientBOBase.getCurrentThreadId() && isKeepAlive) {
//            logger.info("此服务已启用,当前服务端通信方式:104");
//            return;
//        }
//        try {
//            bossGroup = new NioEventLoopGroup();
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(bossGroup).channel(NioSocketChannel.class)
//                    .option(ChannelOption.SO_BACKLOG, 128)
//                    .option(ChannelOption.SO_KEEPALIVE, true)
////                    .handler(new Iec104ClientInitializer(energy104ClientBOBase));
//                    .handler(new Iec104ClientInitializer());
//            ChannelFuture connect = null;
//            try {
//                //sync方法等待connect建立连接完毕 不然会异步
//                connect = bootstrap.connect(energy104ClientBOBase.getAddr104(), energy104ClientBOBase.getPort104()).sync();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            connect.addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                    if (!channelFuture.isSuccess()) {
//                        logger.info("监测到104服务连接断开 尝试重连...");
//                        //TODO
//                        reconnect();
//                    }
//                }
//            });
//            if (connect.isSuccess()) {
//                iec104ClientChannel = connect.channel();
//                isKeepAlive = true;
//                logger.info("104客户端已连接 地址为:{} 端口为:{}", energy104ClientBOBase.getAddr104(), energy104ClientBOBase.getPort104());
//                iec104ClientChannel.closeFuture().addListener((listener) -> {
//                    logger.info("监测到104客户端断开连接...");
//                    isKeepAlive = false;
//                    bossGroup.shutdownGracefully();
//                });
//            } else {
//                logger.info("104客户端连接失败...");
//                reconnect();
//            }
//        } catch (Exception e) {
//            logger.info("初始连接失败... 尝试重新连接");
////            this.stopServer();
//            e.printStackTrace();
//            reconnect();
//        }
//    }

    @Override
    public void stopServer() {
        if (iec104ClientChannel.isActive()) {
            iec104ClientChannel.close();
        }
    }


    private void reconnect() {
        bossGroup.schedule(new Runnable() {
            @Override
            public void run() {
                logger.info("重连...");
                if (isKeepAlive) {
                    return;
                }
                startServer(energy104ClientBOBase);
            }
        }, 3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.0.159", 2409);
        System.out.println(socket.getChannel());
    }
}
