package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import io.netty.channel.Channel;

/**
 * @Description : 接收消息处理的策略
 * @Author : Kang
 * @Date : 2023/4/20 19:57
 * @Version : 1.0
 */
public interface ReceiveMessageStrategy {

    void receiveMessage(Channel channel, MessageDetail messageDetail);
}
