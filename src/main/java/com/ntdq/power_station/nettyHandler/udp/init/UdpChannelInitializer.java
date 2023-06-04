package com.ntdq.power_station.nettyHandler.udp.init;

import com.ntdq.power_station.nettyHandler.udp.power.BootNettyUdpSimpleChannelInboundHandler;
import com.ntdq.power_station.nettyHandler.udp.power.HeartCheckHandler;
import com.ntdq.power_station.nettyHandler.udp.power.PowerStationReportDecoder;
import io.netty.channel.*;
import io.netty.handler.codec.DatagramPacketDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.channel.socket.DatagramChannel;
import java.util.concurrent.TimeUnit;

public class UdpChannelInitializer extends ChannelInitializer<DatagramChannel> {

    private static final int readTimeout = 12000000;

    private static final int writeTimeout = 100000;


    @Override
    protected void initChannel(DatagramChannel ch){
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ChannelHandler[]{new IdleStateHandler((long)this.readTimeout, (long)this.writeTimeout, 0L, TimeUnit.MILLISECONDS)});
        pipeline.addLast("hearCheckHandler",new HeartCheckHandler());
        pipeline.addLast(new ChannelHandler[]{new DatagramPacketDecoder((MessageToMessageDecoder)new PowerStationReportDecoder())});
        pipeline.addLast(new ChannelHandler[]{new BootNettyUdpSimpleChannelInboundHandler()});
    }
}
