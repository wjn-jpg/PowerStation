package com.ntdq.power_station.nettyHandler.tcp.modbus.util;

import io.netty.channel.Channel;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端通道管理
 */
public class ModBusChannelManager {

    private static final ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();


    public static Channel getChannel(String channelName){
        if(CollectionUtils.isEmpty(channelMap)){
            return null;
        }
        return channelMap.get(channelName);
    }

    /**
     *  添加channel
     */
    public static void addChannel(String channelName,Channel channel){
        channelMap.put(channelName,channel);
    }

    public static boolean remove(String channelName){
        if (channelMap.contains(channelName)){
            channelMap.remove(channelName);
            return true;
        }
        return false;
    }

}
