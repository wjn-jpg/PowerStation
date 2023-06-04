package com.ntdq.power_station.holder.mqtt;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.mqtt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 客户端缓存信息类
 */
public class ClientDto {

    private static final Logger logger = LoggerFactory.getLogger(ClientDto.class);

    /**
     * 客户端的id
     */
    private String id;

    /**
     * 对应的套接字
     */
    private Channel channel;

    /**
     * 当前客户订阅的主题
     */
    private List<String> topic;


    private ClientDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public boolean hasTopic(String topic) {
        return this.topic.contains(topic);
    }

    public static ClientDto buildClient(String id, Channel channel, List<String> topic) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(id);
        clientDto.setChannel(channel);
        clientDto.setTopic(topic);
        return clientDto;
    }

    public ChannelFuture sendMessage(String topic, String message) throws InterruptedException {
        if (channel != null && channel.isActive()) {
            MqttRequest mqttRequest = new MqttRequest(message.getBytes());
            MqttMessage mqttMessage = MqttMessageFactory.newMessage(
                    new MqttFixedHeader(MqttMessageType.PUBLISH
                            , mqttRequest.isDup()
                            , mqttRequest.getQos()
                            , mqttRequest.isRetained()
                            , 0), new MqttPublishVariableHeader(topic, 0),
                    Unpooled.buffer().writeBytes(mqttRequest.getPayload()));
            logger.info("mqtt转发主题消息成功:{}", message);
            return channel.writeAndFlush(mqttMessage).sync();
        } else {
            logger.info("mqtt转发主题消息失败:{}", "当前通信通道未建立...");
            return null;
        }
    }

}
