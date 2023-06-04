package com.ntdq.power_station.socketStrategy.Decorator.Function;

import io.netty.channel.ChannelFuture;

@FunctionalInterface
public interface ChannelFutureContextProcessor {

    void setChannelFuture(ChannelFuture channelFuture);

}
