package com.ntdq.power_station.nettyHandler.tcp.init.client;

import com.ntdq.power_station.nettyHandler.tcp.modbus.handler.TCPModbusReqEncoder;
import com.ntdq.power_station.nettyHandler.tcp.modbus.handler.TCPModbusResDecoder;
import com.ntdq.power_station.nettyHandler.tcp.modbus.handler.TCPModbusResHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class TcpModBusClientHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder",new TCPModbusResDecoder());
        pipeline.addLast("encoder",new TCPModbusReqEncoder());
        pipeline.addLast("tcpModBus",new TCPModbusResHandler());
    }
}
