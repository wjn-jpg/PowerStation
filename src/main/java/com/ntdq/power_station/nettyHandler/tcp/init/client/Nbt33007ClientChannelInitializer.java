package com.ntdq.power_station.nettyHandler.tcp.init.client;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.codec.Nbt33007Decoder;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.codec.Nbt33007Encoder;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.handler.Nbt33007ClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class Nbt33007ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("decoder",new Nbt33007Decoder());
        socketChannel.pipeline().addLast("encoder",new Nbt33007Encoder());
        socketChannel.pipeline().addLast("nbt33007",new Nbt33007ClientHandler());
    }
}
