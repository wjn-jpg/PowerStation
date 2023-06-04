package com.ntdq.power_station.nettyHandler.tcp.nbt33007.socketContext;

import io.netty.channel.Channel;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description : channel容器
 * @Author : Kang
 * @Date : 2023/4/21 16:27
 * @Version : 1.0
 */
public class ChannelManager {

    /**
     * 全局map，保存通道
     */
    private static final ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>(128);

    public static ConcurrentHashMap<String, Channel> getChannelMap() {
        return channelMap;
    }

    /**
     *  获取channel
     */
    public static Channel getChannel(String channelId){
        if(CollectionUtils.isEmpty(channelMap)){
            return null;
        }
        return channelMap.get(channelId);
    }


    /**
     *  添加channel
     */
    public static void addChannel(String channelId,Channel channel){
        channelMap.put(channelId,channel);
    }

    /**
     *  移除channel
     */
    public static boolean removeChannel(String channelId){
        if(channelMap.containsKey(channelId)){
            channelMap.remove(channelId);
            return true;
        }
        return false;
    }

    /**
     * 返回channel列表
     * @return
     */
    public static List<String> list(){
        List<String> list = new ArrayList<>();
        if(CollectionUtils.isEmpty(channelMap)){
            return null;
        }
        for (Map.Entry<String, Channel> iterator : channelMap.entrySet()) {
            String channelId = iterator.getKey();
            Channel channel = iterator.getValue();
            list.add(channelId);
        }
        return list;

    }
}
