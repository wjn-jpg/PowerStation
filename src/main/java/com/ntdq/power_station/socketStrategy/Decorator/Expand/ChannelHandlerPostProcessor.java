package com.ntdq.power_station.socketStrategy.Decorator.Expand;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;

/**
 * @author wang_ji_nian
 */
public interface ChannelHandlerPostProcessor {


    default void setChannelHandlerFieldAfterInit(ChannelHandler channelHandler) {

    }

    default void setChannelFutureAfterConnectOrBind(ChannelFuture channelFuture) {

    }


    default void setChannelAfterServerHaveClient(Channel channel) {

    }


}
