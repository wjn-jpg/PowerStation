package com.ntdq.power_station.socketStrategy.SocketProcess.Temp;

import com.ntdq.power_station.IServer.server.MqttNettyTcpServer;
import com.ntdq.power_station.config.mqtt.MqttServerBOBase;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.holder.mqtt.SessionSocketHolder;
import com.ntdq.power_station.nettyHandler.tcp.init.server.MqttChannelInitializer;
import com.ntdq.power_station.nettyHandler.tcp.mqtt.MqttServerHandler;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandlerPostProcessor;
import com.ntdq.power_station.socketStrategy.SocketProcess.ServerProcess;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttServerProcess implements ServerProcess {
    private final static Logger logger = LoggerFactory.getLogger(MqttServerBOBase.class);

    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workerGroup;

    private Channel serverChannel;

    /**
     * 记录session的类
     */
    private static SessionSocketHolder sessionSocketHolder = new SessionSocketHolder();

    private static MqttServerBOBase mqttServerBOBase;

    public static void setHandlerField(ChannelHandler channelHandler) {
        if (channelHandler instanceof MqttChannelInitializer) {
            MqttChannelInitializer mqttChannelInitializer = (MqttChannelInitializer) channelHandler;
            SessionSocketHolder sessionSocketHolder = new SessionSocketHolder();
            MqttServerHandler mqttServerHandler = new MqttServerHandler(sessionSocketHolder, mqttServerBOBase);
            //mqttChannelInitializer.setMqttServerHandler(mqttServerHandler);
        }
    }

    static class MqttHandler implements ChannelHandlerPostProcessor {
        @Override
        public void setChannelFutureAfterConnectOrBind(ChannelFuture channelFuture) {
            if (channelFuture.isSuccess()) {
                MqttNettyTcpServer.setMqttStarted(true);
            }
        }

        @Override
        public void setChannelHandlerFieldAfterInit(ChannelHandler channelHandler) {
            if (channelHandler instanceof MqttChannelInitializer) {
                MqttChannelInitializer mqttChannelInitializer = (MqttChannelInitializer) channelHandler;
                SessionSocketHolder sessionSocketHolder = new SessionSocketHolder();
                MqttServerHandler mqttServerHandler = new MqttServerHandler(sessionSocketHolder, mqttServerBOBase);
                // mqttChannelInitializer.setMqttServerHandler(mqttServerHandler);
            }
        }
    }


//    @Override
//    public void startServer(ServerBaseConfiguration appConfiguration) {
//        mqttServerBOBase = (MqttServerBOBase) appConfiguration;
//        Map<ChannelOption, Object> map = new HashMap<>();
//        map.put(ChannelOption.SO_KEEPALIVE, true);
//        map.put(ChannelOption.TCP_NODELAY, true);
//        map.put(ChannelOption.SO_REUSEADDR, true);
//        map.put(ChannelOption.SO_BACKLOG, 1024);
//        NettyConfiguration configuration = new NettyConfiguration()
//                .setServerName("Mqtt服务端*")
//                .setOptionValueMap(map)
//                .setHandlerClass(MqttChannelInitializer.class)
//                .setChannelHandlerPostProcessor(new MqttHandler());
//        INetty iTcpNettyServer = new ITcpNettyServer(mqttServerBOBase.getMqttAddress(), mqttServerBOBase.getPort(), "Mqtt");
//        iTcpNettyServer = new NettyHandlerDecorator(iTcpNettyServer, configuration);
//        iTcpNettyServer.initHandler();
//        iTcpNettyServer = new NettyChannelFutureDecorator(iTcpNettyServer, configuration);
//        iTcpNettyServer.connectAndBindServer();
//        MqttNettyTcpServer.setMqttStarted(true);
//    }

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        MqttServerBOBase mqttTopicBO = null;
        if (appConfiguration instanceof MqttServerBOBase) {
            mqttTopicBO = (MqttServerBOBase) appConfiguration;
        } else {
            logger.info("Mqtt对应的配置对象不匹配...");
            return;
        }
        if (Thread.currentThread().getId() != mqttTopicBO.getCurrentProcessThread()) {
            logger.info("此服务已启用,当前服务端通信方式:MQTT");
        }
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
//                    .childOption(ChannelOption.SO_BACKLOG,1024)     //等待队列
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childHandler(new MqttChannelInitializer(sessionSocketHolder, mqttTopicBO));
            ChannelFuture bind = serverBootstrap.bind(mqttTopicBO.getPort()).sync();
            if (bind.isSuccess()) {
                serverChannel = bind.channel();
                logger.info("MQTT服务已启动 端口为:{}", mqttTopicBO.getPort());
                //告诉其他线程Mqtt服务已经启动
                MqttNettyTcpServer.setMqttStarted(true);
                bind.channel().closeFuture().addListener((listener) -> {
                    workerGroup.shutdownGracefully();
                    bossGroup.shutdownGracefully();
                });
            } else {
                logger.info("MQTT服务启动失败...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("成功开启MQTT服务...");

    }

    @Override
    public void stopServer() {

    }
}
