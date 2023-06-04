package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.IServer.server.MqttNettyTcpServer;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.Distribution104ClientBOBase;
import com.ntdq.power_station.config.netDomain.tcp.PhotovoltaicServerBOBase;
import com.ntdq.power_station.domain.enumDo.DeviceEnum;
import com.ntdq.power_station.holder.mqtt.SessionSocketHolder;
import com.ntdq.power_station.nettyHandler.tcp.init.client.Iec104ClientInitializer;
import com.ntdq.power_station.nettyHandler.tcp.init.server.MqttChannelInitializer;
import com.ntdq.power_station.nettyHandler.tcp.mqtt.MqttServerHandler;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandlerPostProcessor;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyChannelFutureDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyHandlerDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.ITcpNettyClient;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.ITcpNettyServer;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Tcp104PhotovoltaicServerProcess implements ServerProcess {

    private final static Logger logger = LoggerFactory.getLogger(PhotovoltaicServerBOBase.class);

    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workerGroup;

    private Channel iec104ServerChannel;

    private static PhotovoltaicServerBOBase photovoltaicConfiguration;

    private boolean isKeepAlive;

    static class PhotovoltaicHandler implements ChannelHandlerPostProcessor {
        @Override
        public void setChannelHandlerFieldAfterInit(ChannelHandler channelHandler) {
            if (channelHandler instanceof Iec104ClientInitializer) {
                Iec104ClientInitializer iec104ClientInitializer = (Iec104ClientInitializer) channelHandler;
                iec104ClientInitializer.setConfiguration(photovoltaicConfiguration);
                iec104ClientInitializer.putDeviceClientHandlerManual(photovoltaicConfiguration);
            }
        }

//        @Override
//        public void setChannelAfterServerHaveClient(Channel channel) {
//            Iec104ClientInitializer.getClientHandler(photovoltaicConfiguration.getDeviceEnum()).setChannel(channel);
//        }
    }


    public void startServer(ServerBaseConfiguration appConfiguration) {
        photovoltaicConfiguration = (PhotovoltaicServerBOBase) appConfiguration;
        photovoltaicConfiguration.setDeviceEnum(DeviceEnum.Photovoltaic);
        Map<ChannelOption, Object> channelOptionMap = new HashMap<>();
        channelOptionMap.put(ChannelOption.SO_BACKLOG, 128);
        channelOptionMap.put(ChannelOption.SO_KEEPALIVE, true);
        NettyConfiguration nettyConfiguration = new NettyConfiguration()
                .setHandlerClass(Iec104ClientInitializer.class)
                .setOptionValueMap(channelOptionMap)
                .setChannelHandlerPostProcessor(new PhotovoltaicHandler())
//                .setChannelHandlerFieldSetProcessor(Tcp104DistributionClientProcess::setHandlerField)
                .setServerName("104光伏服务端");

        INetty iTcpNettyServer = new ITcpNettyServer(photovoltaicConfiguration.getAddr104(), photovoltaicConfiguration.getPort104(), "PhotovoltaicServer");
        iTcpNettyServer = new NettyHandlerDecorator(iTcpNettyServer, nettyConfiguration);
        iTcpNettyServer.initHandler();
        iTcpNettyServer = new NettyChannelFutureDecorator(iTcpNettyServer, nettyConfiguration);
        iTcpNettyServer.connectAndBindServer();
    }


    //    @Override
    public void startServer1(ServerBaseConfiguration appConfiguration) {
        PhotovoltaicServerBOBase iec104Client = null;
        if (appConfiguration instanceof PhotovoltaicServerBOBase) {
            iec104Client = (PhotovoltaicServerBOBase) appConfiguration;
            photovoltaicConfiguration = iec104Client;
            iec104Client.setDeviceEnum(DeviceEnum.Photovoltaic);
        } else {
            logger.info("对应的配置对象不匹配 启动充电塔服务失败...");
            return;
        }
        if (Thread.currentThread().getId() != iec104Client.getCurrentThreadId() && isKeepAlive) {
            logger.info("此服务已启用,当前服务端通信方式:104");
            return;
        }
        try {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
//                    .childHandler(new Iec104ClientInitializer(iec104Client));
                    .childHandler(new Iec104ClientInitializer(iec104Client));
            ChannelFuture bind = bootstrap.bind(iec104Client.getPort104()).sync();
            bind.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        logger.info("监测到104服务连接断开 尝试重连...");
                        //TODO
                        reconnect();
                    }
                }
            });
            if (bind.isSuccess()) {
                iec104ServerChannel = bind.channel();
                isKeepAlive = true;
                InetAddress localHost = InetAddress.getLocalHost();
                logger.info("104服务已建立 地址为:{} 端口为:{}", localHost.getHostAddress(), iec104Client.getPort104());
                iec104ServerChannel.closeFuture().addListener((listener) -> {
                    logger.info("监测到104客户端连接关闭...");
                    isKeepAlive = false;
                    bossGroup.shutdownGracefully();
                });
            } else {
                logger.info("104服务器上线失败...");
                reconnect();
            }
        } catch (Exception e) {
            logger.info("初始连接失败... 尝试重新连接");
//            this.stopServer();
            e.printStackTrace();
            reconnect();
        }
    }

    @Override
    public void stopServer() {
        if (iec104ServerChannel.isActive()) {
            iec104ServerChannel.close();
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
                startServer(photovoltaicConfiguration);
            }
        }, 3, TimeUnit.SECONDS);
    }


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.0.185", 2404);
        System.out.println(socket.getChannel());
    }
}
