package com.ntdq.power_station.socketStrategy.Decorator;

import com.ntdq.power_station.config.mqtt.MqttServerBOBase;
import com.ntdq.power_station.config.netDomain.tcp.Energy104ClientBOBase;
import com.ntdq.power_station.domain.enumDo.DeviceEnum;
import com.ntdq.power_station.holder.mqtt.SessionSocketHolder;
import com.ntdq.power_station.nettyHandler.parseCore.parse.Energy104Parser;
import com.ntdq.power_station.nettyHandler.tcp.init.client.Iec104ClientInitializer;
import com.ntdq.power_station.nettyHandler.tcp.init.client.TcpModBusClientHandlerInitializer;
import com.ntdq.power_station.nettyHandler.tcp.init.server.MqttChannelInitializer;
import com.ntdq.power_station.nettyHandler.tcp.master104.handler.Iec104ClientHandler;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusMessage;
import com.ntdq.power_station.nettyHandler.tcp.mqtt.MqttServerHandler;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandler104PostProcessor;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandlerPostProcessor;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.My104Handler;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyChannelFutureDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyHandlerDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.ITcpNettyClient;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import com.ntdq.power_station.socketStrategy.SocketProcess.MqttServerProcess;
import com.ntdq.power_station.socketStrategy.SocketProcess.Tcp104EnergyClientProcess;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;

import java.util.HashMap;
import java.util.Map;

public class DecoratorDemo {

    public static Channel channel;

    private static Energy104ClientBOBase energy104ClientBOBase;

    public static void setHandlerField(ChannelHandler channelHandler) {
        if (channelHandler instanceof MqttChannelInitializer) {
            MqttChannelInitializer mqttChannelInitializer = (MqttChannelInitializer) channelHandler;
            SessionSocketHolder sessionSocketHolder = new SessionSocketHolder();
            MqttServerHandler mqttServerHandler = new MqttServerHandler(sessionSocketHolder, null);
            //mqttChannelInitializer.setMqttServerHandler(mqttServerHandler);
        }
    }

    static class DemoHandler implements ChannelHandlerPostProcessor {
        @Override
        public void setChannelHandlerFieldAfterInit(ChannelHandler channelHandler) {
            if (channelHandler instanceof  Iec104ClientInitializer){
                Iec104ClientInitializer iec104ClientInitializer = (Iec104ClientInitializer) channelHandler;
                iec104ClientInitializer.setConfiguration(energy104ClientBOBase);
            }
        }
    }

    public static ChannelFuture channelFuture;

    public static void setChannelFuture(ChannelFuture channelFuture) {
        DecoratorDemo.channelFuture = channelFuture;
    }

    public static void main1(String[] args) {
        Tcp104EnergyClientProcess mqttServerProcess = new Tcp104EnergyClientProcess();
        Energy104ClientBOBase energy104ClientBOBase = new Energy104ClientBOBase();
        energy104ClientBOBase.setAddr104("192.168.0.74");
        energy104ClientBOBase.setPort104(2404);
        energy104ClientBOBase.setEnergy104Parser(new Energy104Parser());
        mqttServerProcess.startServer(energy104ClientBOBase);
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        energy104ClientBOBase = new Energy104ClientBOBase();
        energy104ClientBOBase.setDeviceEnum(DeviceEnum.Energy);
        energy104ClientBOBase.setAddr104("127.0.0.1");
        energy104ClientBOBase.setPort104(2404);
        Map<ChannelOption, Object> map = new HashMap<>();
        map.put(ChannelOption.SO_BACKLOG, 128);
        map.put(ChannelOption.SO_KEEPALIVE, true);
        NettyConfiguration nettyConfiguration = new NettyConfiguration()
                .setOptionValueMap(map)
                .setHandlerClass(Iec104ClientInitializer.class)
                .setServerName(DeviceEnum.Energy.name())
                .setChannelHandlerPostProcessor(new DemoHandler());
        INetty tcpNettyClient = new ITcpNettyClient("127.0.0.1", 2404);
        tcpNettyClient = new NettyHandlerDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient = new NettyChannelFutureDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.initHandler();
        tcpNettyClient.connectAndBindServer();

//        try {
//            Thread.sleep(5000);
//            if (channelFuture.channel() != null) {
//                ModBusMessage modBusMessage = new ModBusMessage();
//                channelFuture.channel().writeAndFlush(modBusMessage);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        NettyConfiguration configuration = new NettyConfiguration();
//        configuration.setOptionValueMap(map);
//        configuration.setHandlerClass(MqttChannelInitializer.class);
//        configuration.setChannelHandlerFieldSetProcessor(DecoratorDemo::setHandlerField);
//        configuration.setServerName("Mqtt服务端*");
//
//
//        /**
//         * 装饰器模式的本质就是每个new的对象里面都可以有上一个对象的指针 从而实现多次方法调用
//         */
//        INetty iTcpNettyServer = new ITcpNettyServer("127.0.0.1", 8765, "Mqtt");
//        iTcpNettyServer = new NettyHandlerDecorator(iTcpNettyServer, configuration);
//        iTcpNettyServer.initHandler();
//        iTcpNettyServer = new NettyChannelFutureDecorator(iTcpNettyServer, configuration);
//        iTcpNettyServer.connectAndBindServer();


    }
}
