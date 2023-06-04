package com.ntdq.power_station.nettyHandler.tcp.master104.handler;

import com.ntdq.power_station.nettyHandler.tcp.master104.constant.Iec104Constant;
import com.ntdq.power_station.nettyHandler.tcp.master104.core.Iec104ThreadLocal;
import com.ntdq.power_station.nettyHandler.tcp.master104.domain.MessageDetail;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MessageDetailEncoder extends MessageToMessageEncoder<MessageDetail> {

    private final static Logger logger = LoggerFactory.getLogger(MessageDetailEncoder.class);


    @Override
    protected void encode(ChannelHandlerContext ctx, MessageDetail messageDetail, List<Object> out) throws Exception {
        //不是S确认帧 那么就可能是总召 或者 遥控的I帧
        if (messageDetail.getMessageType() == Iec104Constant.SEND_NUMBER_ALL) { //!=S
            //无论是总召 还是 yk指令 都要调用一下getSend方法 让send序列号++
            short send = Iec104ThreadLocal.getControlSendAndRecCountPool().getSend();
            logger.info("总召帧 发送序列号:{}", send);
            messageDetail.setSend(send - 1);
            logger.info("发送总召唤:{}",messageDetail.getSendData());
        } else if (messageDetail.getMessageType() == Iec104Constant.SEND_NUMBER_YK || messageDetail.getMessageType() == Iec104Constant.SEND_NUMBER_YK_50) {
            short send = Iec104ThreadLocal.getControlSendAndRecCountPool().getSend();
            Short accept = Iec104ThreadLocal.getControlSendAndRecCountPool().getAccept();
            messageDetail.setSend(send);
            messageDetail.setAccept(accept);
            messageDetail.getMessageYK().build();
        }
        //如果是S帧直接发送
        out.add(messageDetail.getSendData());
    }
}
