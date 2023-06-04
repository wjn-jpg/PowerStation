package com.ntdq.power_station.socketStrategy.Decorator.Expand;

import com.ntdq.power_station.nettyHandler.tcp.init.client.Iec104ClientInitializer;
import io.netty.channel.ChannelHandler;

/**
 * @author wang_ji_nian
 */
public abstract class ChannelHandler104PostProcessor implements ChannelHandlerPostProcessor,YcAndYxProcess {

    @Override
    public void setChannelHandlerFieldAfterInit(ChannelHandler channelHandler) {
        Iec104ClientInitializer iec104ClientInitializer = (Iec104ClientInitializer) channelHandler;
        iec104ClientInitializer.setYcAndYxProcess(this);
    }


}
