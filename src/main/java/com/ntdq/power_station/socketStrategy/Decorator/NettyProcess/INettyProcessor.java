package com.ntdq.power_station.socketStrategy.Decorator.NettyProcess;

import com.ntdq.power_station.socketStrategy.Decorator.config.NettyInfo;

public abstract class INettyProcessor extends NettyInfo implements INetty {

    public INettyProcessor(String host, Integer port) {
        super(host, port);
    }

    public INettyProcessor(String host, Integer port, String serverName) {
        super(host, port, serverName);
    }


}
