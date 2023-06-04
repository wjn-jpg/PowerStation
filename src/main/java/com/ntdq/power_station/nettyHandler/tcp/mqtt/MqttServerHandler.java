package com.ntdq.power_station.nettyHandler.tcp.mqtt;

import static io.netty.handler.codec.mqtt.MqttQoS.AT_LEAST_ONCE;
import static io.netty.handler.codec.mqtt.MqttQoS.AT_MOST_ONCE;

import com.ntdq.power_station.config.mqtt.MqttServerBOBase;
import com.ntdq.power_station.nettyHandler.DefaultChannelHandler;
import com.ntdq.power_station.holder.mqtt.ClientDto;
import com.ntdq.power_station.holder.mqtt.SessionSocketHolder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * mqtt业务处理
 */
@ChannelHandler.Sharable
public class MqttServerHandler extends DefaultChannelHandler<MqttMessage> {

    private static final Logger logger = LoggerFactory.getLogger(MqttServerHandler.class);

    private final SessionSocketHolder sessionSocketHolder;

    private final MqttServerBOBase mqttServerBO;



    public MqttServerHandler(SessionSocketHolder sessionSocketHolder, MqttServerBOBase mqttServerBO) {
        this.sessionSocketHolder = sessionSocketHolder;
        this.mqttServerBO = mqttServerBO;
    }

    @Override
    protected void read(ChannelHandlerContext context, MqttMessage mqttMessage) {
        Channel clientChannel = context.channel();
        MqttMessageType mqttMessageType = mqttMessage.fixedHeader().messageType();
        logger.info("MQTT接收到的发送类型===>{}", mqttMessageType);
        switch (mqttMessageType) {
            /**
             * 客户端与服务端建立连接
             */
            case CONNECT:
                connect(clientChannel, (MqttConnectMessage) mqttMessage);
                break;
            /**
             * 客户端向服务端发送消息
             */
            case PUBLISH:
                publish(clientChannel, (MqttPublishMessage) mqttMessage);
                break;
            /**
             * 客户端订阅主题
             */
            case SUBSCRIBE:
                subscribe(clientChannel, (MqttSubscribeMessage) mqttMessage);
                break;
            /**
             * 客户端退订主题
             */
            case UNSUBSCRIBE:
                unSubScribe(clientChannel, (MqttUnsubscribeMessage) mqttMessage);
                break;
            /**
             * 客户端与服务端断开连接
             */
            case DISCONNECT:
                disConnect(clientChannel, mqttMessage);
                break;
            /**
             * 服务端向客户端推送qos1后 客户端返回响应告诉服务端我已经收到了你的推送消息
             */
            case PUBACK:
                puBackMessageForConfirm(clientChannel, (MqttPubAckMessage) mqttMessage);
                break;
            /**
             * 心跳包
             */
            case PINGREQ:
                pingReq(clientChannel, mqttMessage);
                break;
            /**
             * 其他的messageType
             */
            default:
                logger.info("不支持的服务消息类型:{}", mqttMessageType);
                break;
        }
    }


    /**
     * 客户端与服务端进行连接
     *
     * @param channel
     * @param connectMessage
     */
    private void connect(Channel channel, MqttConnectMessage connectMessage) {
        if (mqttServerBO.isNeedVerify()) {
            String clientName = connectMessage.payload().userName();
            String clientPassword = connectMessage.payload().password();
            if (!mqttServerBO.getUsername().equals(clientName) || !mqttServerBO.getPassword().equals(clientPassword)) {
                logger.info("检测到mqtt客户端的账号密码不正确...请使用正确的账户密码...");
                MqttConnAckMessage okResp = (MqttConnAckMessage) MqttMessageFactory.newMessage(new MqttFixedHeader(
                                MqttMessageType.CONNACK, false, AT_MOST_ONCE, false, 0),
                        new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD, true), null);
                channel.writeAndFlush(okResp);
                disConnect(channel, connectMessage);
                return;
            }
            logger.info("mqtt客户端:{} 连接成功...",channel.id());
        }
        //连接业务校验完成,Qos1类型，需要答复
        MqttConnAckMessage okResp = (MqttConnAckMessage) MqttMessageFactory.newMessage(new MqttFixedHeader(
                        MqttMessageType.CONNACK, false, AT_MOST_ONCE, false, 0),
                new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, true), null);
        channel.writeAndFlush(okResp);
        sessionSocketHolder.addClient(channel, ClientDto.buildClient(channel.id().asShortText(), channel, null));
    }

    /**
     * 客户端向服务端发布消息
     *
     * @param channel
     * @param publishMessage
     */
    private void publish(Channel channel, MqttPublishMessage publishMessage) {
        logger.info("此时的qosLevel为:{} topic为:{}", publishMessage.fixedHeader().qosLevel(), publishMessage.variableHeader().topicName());
        ByteBuf byteBuf = publishMessage.content().duplicate();
        byte[] tmp = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(tmp);
        try {
            String message = new String(tmp, "UTF-8");
            logger.info("客户端发送的消息:{}", message);
            sessionSocketHolder.executeListenerTopic(channel, publishMessage.variableHeader().topicName(), message);
            puBackMessageForConfirm(channel,publishMessage,"puBackACK");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 客户端订阅主题topic
     *
     * @param channel
     * @param subscribeMessage
     */
    private void subscribe(Channel channel, MqttSubscribeMessage subscribeMessage) {
        MqttMessage subAckMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.SUBACK, false, AT_MOST_ONCE, false, 0)
                , MqttMessageIdVariableHeader.from(subscribeMessage.variableHeader().messageId())
                , new MqttSubAckPayload(0));
        MqttSubscribePayload mqttSubscribePayload = subscribeMessage.payload();
        List<MqttTopicSubscription> mqttTopicSubscriptions = mqttSubscribePayload.topicSubscriptions();
        for (MqttTopicSubscription mqttTopicSubscription : mqttTopicSubscriptions) {
            sessionSocketHolder.addTopic(channel, mqttTopicSubscription.topicName());
            logger.info("客户端{}订阅主题{}成功!", channel.id().asShortText(), mqttTopicSubscription.topicName());
        }
        channel.writeAndFlush(subAckMessage);
    }

    /**
     * 客户端退订主题topic
     *
     * @param channel
     * @param mqttUnsubscribeMessage
     */
    private void unSubScribe(Channel channel, MqttUnsubscribeMessage mqttUnsubscribeMessage) {
        MqttUnsubAckMessage mqttMessage = (MqttUnsubAckMessage) MqttMessageFactory.newMessage(new MqttFixedHeader(MqttMessageType.UNSUBACK, false, AT_LEAST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(mqttUnsubscribeMessage.variableHeader().messageId()), null);
        channel.writeAndFlush(mqttMessage);
    }

    /**
     * 客户端与服务端断开连接
     *
     * @param channel
     * @param mqttMessage
     */
    private void disConnect(Channel channel, MqttMessage mqttMessage) {
        if (channel.isActive()) {
            channel.close();
            logger.debug("MQTT channel:{} 已经关闭", channel.id().asShortText());
            logger.info("channel的msg:{}", mqttMessage.toString());
            sessionSocketHolder.removeClient(channel);
        }
    }

    /**
     * 客户端QOS1的消息类型 就是需要服务端响应包 否则客户端一直阻塞
     * @param channel
     * @param message
     */
    private void puBackMessageForConfirm(Channel channel, MqttPubAckMessage message) {
        MqttPubAckMessage sendMessage = (MqttPubAckMessage) MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBACK, false, MqttQoS.AT_LEAST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(message.variableHeader().messageId()),
                "ack return!");
        channel.writeAndFlush(sendMessage);
    }

    /**
     * 客户端发布消息 等待mqtt服务端确认
     * @param channel
     * @param mqttPublishMessage
     * @param payload
     */
    private void puBackMessageForConfirm(Channel channel,MqttPublishMessage mqttPublishMessage,String payload){

//        MqttPubAckMessage mqttAckReturnMessage = (MqttPubAckMessage) MqttMessageFactory.newMessage(
//                new MqttFixedHeader(MqttMessageType.PUBACK, false, MqttQoS.AT_LEAST_ONCE, false, 0),
//                MqttMessageIdVariableHeader.from(mqttPublishMessage.variableHeader().packetId()),
//                payload);
//        channel.writeAndFlush(mqttAckReturnMessage);
//        MqttMessage mqttAckReturnMessage = MqttMessageFactory.newMessage(new MqttFixedHeader(MqttMessageType.PUBLISH, false, AT_LEAST_ONCE, false, 0),
//                MqttMessageIdVariableHeader.from(mqttPublishMessage.variableHeader().packetId()),
//                payload);
//        channel.writeAndFlush(mqttAckReturnMessage);
    }

    /**
     * 添加客户端信息在缓存中
     *
     * @param channel
     * @param message
     */
    private void addClient(Channel channel, String message) {

    }

    /**
     * 客户端向服务端发送心跳检测
     *
     * @param channel
     * @param mqttMessage
     */
    private void pingReq(Channel channel, MqttMessage mqttMessage) {
        logger.info("MQTT 心跳包已经接受 来源channelID:{}", channel.id());
        MqttMessage pingResp = new MqttMessage(new MqttFixedHeader(MqttMessageType.PINGRESP, false, AT_MOST_ONCE, false, 0));
        channel.writeAndFlush(pingResp);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        sessionSocketHolder.removeClient(ctx.channel());
        logger.info("删除channel容器id:{}", ctx.channel().id());
    }

}
