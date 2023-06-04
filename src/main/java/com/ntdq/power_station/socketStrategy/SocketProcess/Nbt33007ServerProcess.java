package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.Nbt33007BOBase;
import com.ntdq.power_station.nettyHandler.tcp.init.server.Nbt33007ServerChannelInitializer;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyChannelFutureDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyDecorator.NettyHandlerDecorator;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.INetty;
import com.ntdq.power_station.socketStrategy.Decorator.NettyProcess.ITcpNettyClient;
import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;

public class Nbt33007ServerProcess implements ServerProcess {
    private static Nbt33007BOBase nbt33007BOBase;


    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        nbt33007BOBase = (Nbt33007BOBase) appConfiguration;
        NettyConfiguration nettyConfiguration = new NettyConfiguration()
                .setHost(nbt33007BOBase.getServerIp())
                .setPort(nbt33007BOBase.getServerPort())
                .setHandlerClass(Nbt33007ServerChannelInitializer.class)
                .setServerName("Nbt33007客户端");

        INetty tcpNettyClient = new ITcpNettyClient(nettyConfiguration.getHost(), nettyConfiguration.getPort());
        tcpNettyClient = new NettyHandlerDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.initHandler();
        tcpNettyClient = new NettyChannelFutureDecorator(tcpNettyClient, nettyConfiguration);
        tcpNettyClient.connectAndBindServer();
    }

    @Override
    public void stopServer() {

    }
}
