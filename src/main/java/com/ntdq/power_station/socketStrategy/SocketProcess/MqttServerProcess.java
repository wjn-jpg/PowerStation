package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.config.mqtt.MqttServerBOBase;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.nettyHandler.tcp.init.server.MqttChannelInitializer;
import com.ntdq.power_station.holder.mqtt.SessionSocketHolder;
import com.ntdq.power_station.IServer.server.MqttNettyTcpServer;
import com.ntdq.power_station.nettyHandler.tcp.mqtt.MqttServerHandler;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandlerPostProcessor;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyChannelFutureDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyHandlerDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.ITcpNettyServer;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MqttServerProcess implements ServerProcess {

    private final static Logger logger = LoggerFactory.getLogger(MqttServerBOBase.class);

    private static MqttServerBOBase mqttServerBOBase;

    public static void setHandlerField(ChannelHandler channelHandler) {
        if (channelHandler instanceof MqttChannelInitializer) {
            MqttChannelInitializer mqttChannelInitializer = (MqttChannelInitializer) channelHandler;
            SessionSocketHolder sessionSocketHolder = new SessionSocketHolder();
            MqttServerHandler mqttServerHandler = new MqttServerHandler(sessionSocketHolder, mqttServerBOBase);
            mqttChannelInitializer.setMqttServerHandler(mqttServerHandler);
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
                mqttChannelInitializer.setMqttServerHandler(mqttServerHandler);
            }
        }
    }


    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        mqttServerBOBase = (MqttServerBOBase) appConfiguration;
        Map<ChannelOption, Object> map = new HashMap<>();
        map.put(ChannelOption.SO_KEEPALIVE, true);
        map.put(ChannelOption.TCP_NODELAY, true);
        map.put(ChannelOption.SO_REUSEADDR, true);
        map.put(ChannelOption.SO_BACKLOG, 1024);
        NettyConfiguration configuration = new NettyConfiguration()
                .setServerName("Mqtt服务端*")
                .setOptionValueMap(map)
                .setHandlerClass(MqttChannelInitializer.class)
                .setChannelHandlerPostProcessor(new MqttHandler());
        INetty iTcpNettyServer = new ITcpNettyServer(mqttServerBOBase.getMqttAddress(), mqttServerBOBase.getPort(), "Mqtt");
        iTcpNettyServer = new NettyHandlerDecorator(iTcpNettyServer, configuration);
        iTcpNettyServer.initHandler();
        iTcpNettyServer = new NettyChannelFutureDecorator(iTcpNettyServer, configuration);
        iTcpNettyServer.connectAndBindServer();
    }

    @Override
    public void stopServer() {

    }

}
