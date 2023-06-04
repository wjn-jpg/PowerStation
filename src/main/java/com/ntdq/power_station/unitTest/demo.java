package com.ntdq.power_station.unitTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

import static com.ntdq.power_station.util.FeedConversionUtil.toFloat;

public class demo {
    public static void main1(String[] args) {
        EventLoopGroup group=new NioEventLoopGroup();
        try{
            Bootstrap b=new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST,true).handler(new ClientHandler());
            Channel ch=b.bind(0).sync().channel();
            ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("message from client: hello,world", CharsetUtil.UTF_8), new InetSocketAddress("127.0.0.1",8086)));
            if(!ch.closeFuture().await(60000)){
                System.out.println("超时");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        String dateN = "42C80000";
        System.out.println(Float.parseFloat(toFloat(dateN)));
    }
}
