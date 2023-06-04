package com.ntdq.power_station.holder.mqtt;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author wang_ji_nian
 * @version 1.0
 */
@Component
public class SessionSocketHolder {

    private final static Logger logger = LoggerFactory.getLogger(SessionSocketHolder.class);

    /**
     * key是设备的id
     * value为对应的tcp socket套接字
     */
    private static final Map<String, ClientDto> CLIENT_CHANNEL_MAP = new ConcurrentHashMap<>();


    public void addClient(Channel channel, ClientDto clientDto) {
        if (clientDto == null) {
            logger.info("添加的连接客户端信息对象为null! 添加失败");
            return;
        }
        CLIENT_CHANNEL_MAP.put(channel.id().asShortText(), clientDto);
    }

    public void removeClient(Channel channel){
         CLIENT_CHANNEL_MAP.remove(channel.id().asShortText());
    }

    public void addTopic(Channel channel,String topic){
        ClientDto clientDto = CLIENT_CHANNEL_MAP.getOrDefault(channel.id().asShortText(), null);
        if (clientDto!=null){
            List<String> topicList = clientDto.getTopic();
            if (topicList==null){
                topicList = new ArrayList<>();
            }
            topicList.add(topic);
            clientDto.setTopic(topicList);
        }
    }

    /**
     *
     * @param topic
     * @param message
     */
    public void executeListenerTopic(Channel sendMessageChannel,String topic,String message){
        Set<Map.Entry<String, ClientDto>> chanelClientSet = CLIENT_CHANNEL_MAP.entrySet().stream()
                .filter(stringClientDtoEntry -> !stringClientDtoEntry.getKey().equals(sendMessageChannel.id().asShortText())
                        && stringClientDtoEntry.getValue().hasTopic(topic))
                .collect(Collectors.toSet());
        try {
            for (Map.Entry<String, ClientDto> stringClientDtoEntry : chanelClientSet) {
                ClientDto clientDto = stringClientDtoEntry.getValue();
                clientDto.sendMessage(topic,message);
            }
        }catch (InterruptedException e){
            logger.info("主题分发失败:{} 消息为:{}",topic,message);
        }
    }

}
