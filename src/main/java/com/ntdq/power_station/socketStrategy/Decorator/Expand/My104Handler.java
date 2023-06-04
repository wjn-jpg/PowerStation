package com.ntdq.power_station.socketStrategy.Decorator.Expand;

import com.ntdq.power_station.nettyHandler.tcp.init.client.Iec104ClientInitializer;
import io.netty.channel.ChannelHandler;

public class My104Handler extends ChannelHandler104PostProcessor {
    @Override
    public void processYXOrYC(Integer address, String value) {
        System.out.println("地址:" + address + " 值:" + value);
    }

}
