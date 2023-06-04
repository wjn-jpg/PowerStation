package com.ntdq.power_station.nettyHandler.tcp.init.server;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.codec.Nbt33007Decoder;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.codec.Nbt33007Encoder;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.handler.Nbt33007ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Component;

/**
 * @Description : 服务端channel初始化器
 * @Author : Kang
 * @Date : 2023/4/19 16:14
 * @Version : 1.0
 */
@Component
public class Nbt33007ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    public Nbt33007ServerChannelInitializer() {
        super();
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("decoder", new Nbt33007Decoder());
        ch.pipeline().addLast("encoder", new Nbt33007Encoder());
        ch.pipeline().addLast("nbt33007", new Nbt33007ServerHandler());
    }
}
