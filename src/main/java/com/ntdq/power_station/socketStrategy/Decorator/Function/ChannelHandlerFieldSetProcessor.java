package com.ntdq.power_station.socketStrategy.Decorator.Function;

import io.netty.channel.ChannelHandler;
import jdk.nashorn.internal.objects.annotations.Function;

@FunctionalInterface
public interface ChannelHandlerFieldSetProcessor {

    void fillChannelHandlerField(ChannelHandler channelHandler);

}
