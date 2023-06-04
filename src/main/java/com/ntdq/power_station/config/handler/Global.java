package com.ntdq.power_station.config.handler;

import com.ntdq.power_station.domain.enumDo.SocketStyle;
import com.ntdq.power_station.nettyHandler.DefaultChannelHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Global {

    private static final Map<SocketStyle, DefaultChannelHandler> styleHandler = new ConcurrentHashMap<>(4);


    public static void put(SocketStyle socketStyle,DefaultChannelHandler defaultChannelHandler){
        styleHandler.put(socketStyle,defaultChannelHandler);
    }

    public DefaultChannelHandler getHandler(SocketStyle socketStyle){
        return styleHandler.getOrDefault(socketStyle,null);
    }


}
