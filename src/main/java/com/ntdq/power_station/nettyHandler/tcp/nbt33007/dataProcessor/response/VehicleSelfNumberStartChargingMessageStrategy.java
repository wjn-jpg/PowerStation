package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.NBT33007SendMessageFactory;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Description : 车辆自编号启动充电
 * @Author : Kang
 * @Date : 2023/4/20 21:32
 * @Version : 1.0
 */

public class VehicleSelfNumberStartChargingMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:车辆自编号启动充电 begin------------");
        log.info("启动字符: {}",Integer.toHexString(messageDetail.getStart()));
        log.info("有效长度: {}",messageDetail.getEffectiveLength());
        log.info("控制域: {}", ByteUtil.bytesToHexString(messageDetail.getControlRegion()));
        log.info("目标地址: {}",ByteUtil.bytesToHexString(messageDetail.getTargetAddress()));
        log.info("源地址: {}",ByteUtil.bytesToHexString(messageDetail.getSourceAddress()));
        log.info("帧类型号: {}",Integer.toHexString(messageDetail.getFrameType()));
        log.info("信息体个数 hex: {}",Integer.toHexString(messageDetail.getInfoBodyCount()));
        log.info("信息体个数: {}",messageDetail.getInfoBodyCount());
        log.info("传送原因: {}",ByteUtil.bytesToHexString(messageDetail.getSendReason()));
        log.info("公共地址: {}",ByteUtil.bytesToHexString(messageDetail.getPublicAddress()));
        log.info("信息体地址: {}",ByteUtil.bytesToHexString(messageDetail.getInfoBodyAddress()));
        log.info("信息体: {}",ByteUtil.bytesToHexString(messageDetail.getInfoBody()));
        log.info("------------Message:车辆自编号启动充电 end-------------");

        byte[] effectiveLength = messageDetail.getEffectiveLength();
        byte[] infoBody = messageDetail.getInfoBody();
        //车辆自编号长度
        byte len = infoBody[0];
        //车辆自编号，ASCII 码
        byte[] carNumber = ByteUtil.getByte(infoBody, 1, len & 0xFF);
        log.error("车辆自编号:{}",new String(carNumber, StandardCharsets.US_ASCII));

        boolean isCheck = false;

        if (isCheck) {
            //TODO 主站校验，如果检验通过，则直接下发 5.5 的充电启动控制命令；
            //TODO 模拟数据
            byte[] responseInfoBody = new byte[]{};
            MessageDetail responseMessage = NBT33007SendMessageFactory.masterSendStartStopChargingCommand(messageDetail.getSourceAddress(), responseInfoBody);
            channel.writeAndFlush(responseMessage);
            log.error("校验失败，开始启动充电...");
        }else {
            //TODO 如果检验不通过，则回复如下报文：
            MessageDetail responseMessage = new MessageDetail();
            BeanUtils.copyProperties(messageDetail,responseMessage);
            responseMessage.setEffectiveLength(new byte[]{0x16, 0x00});
            responseMessage.setTargetAddress(messageDetail.getSourceAddress());
            responseMessage.setSourceAddress(messageDetail.getTargetAddress());
            responseMessage.setInfoBody(new byte[]{0x00});
            log.error("校验失败，无法启动充电...");
        }
    }
}
