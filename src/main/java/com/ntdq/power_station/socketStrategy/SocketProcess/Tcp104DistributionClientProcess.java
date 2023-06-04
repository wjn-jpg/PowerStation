package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.Distribution104ClientBOBase;
import com.ntdq.power_station.config.netDomain.tcp.PhotovoltaicServerBOBase;
import com.ntdq.power_station.domain.enumDo.DeviceEnum;
import com.ntdq.power_station.nettyHandler.tcp.init.client.Iec104ClientInitializer;
import com.ntdq.power_station.nettyHandler.tcp.master104.handler.Iec104ClientHandler;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.ChannelHandlerPostProcessor;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyChannelFutureDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyHandlerDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.ITcpNettyClient;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;

import java.util.HashMap;
import java.util.Map;

public class Tcp104DistributionClientProcess implements ServerProcess {

    private static Distribution104ClientBOBase distribution104ClientBOBase;

    public static void setHandlerField(ChannelHandler channelHandler) {
        if (channelHandler instanceof Iec104ClientInitializer) {
            Iec104ClientInitializer iec104ClientInitializer = (Iec104ClientInitializer) channelHandler;
            iec104ClientInitializer.setConfiguration(distribution104ClientBOBase);
        }
    }

    static class DistributionHandler implements ChannelHandlerPostProcessor {
        @Override
        public void setChannelHandlerFieldAfterInit(ChannelHandler channelHandler) {
            if (channelHandler instanceof Iec104ClientInitializer) {
                Iec104ClientInitializer iec104ClientInitializer = (Iec104ClientInitializer) channelHandler;
                iec104ClientInitializer.setConfiguration(distribution104ClientBOBase);
            }
        }
    }

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        distribution104ClientBOBase = (Distribution104ClientBOBase) appConfiguration;
        distribution104ClientBOBase.setDeviceEnum(DeviceEnum.Distribution);
        HashMap<ChannelOption, Object> channelOptionMap = new HashMap<>();
        channelOptionMap.put(ChannelOption.SO_BACKLOG, 128);
        channelOptionMap.put(ChannelOption.SO_KEEPALIVE, true);
        NettyConfiguration nettyConfiguration = new NettyConfiguration()
                .setHandlerClass(Iec104ClientInitializer.class)
                .setOptionValueMap(channelOptionMap)
                .setChannelHandlerPostProcessor(new DistributionHandler())
//                .setChannelHandlerFieldSetProcessor(Tcp104DistributionClientProcess::setHandlerField)
                .setServerName("104变电客户端");

        INetty tcpNettyClient = new ITcpNettyClient(distribution104ClientBOBase.getAddr104(), distribution104ClientBOBase.getPort104());
        tcpNettyClient = new NettyHandlerDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.initHandler();
        tcpNettyClient = new NettyChannelFutureDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.connectAndBindServer();

    }

    @Override
    public void stopServer() {

    }
}
