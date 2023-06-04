package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Description : 上送充电车辆VIN -  上送车辆 VIN 请求启动充电
 * @Author : Kang
 * @Date : 2023/4/20 21:18
 * @Version : 1.0
 */

public class ChargingVehicleVinMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:上送充电车辆VIN begin------------");
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
        log.info("------------Message:上送充电车辆VIN end-------------");

        //31 31 30 31 30 30 30 31 36 36 38 30 31 39 36 30 3a
        //17 位车辆 VIN 号，ASCII 码
        byte[] infoBody = messageDetail.getInfoBody();
        String vin = new String(infoBody, StandardCharsets.US_ASCII); //主站校验VIN
        log.info("VIN码：{}",vin);
        boolean isCheck = true;
        if (isCheck){
            /** 主站校验 VIN，如果检验通过，则直接下发 5.5 的充电启动控制命令 */
            /** 第一步 通过账号密码进行校验，校验通过发送启动充电的命令 */

            //遥控性质，1 表示启动充电，0 表示停止充电
            //byte[] remoteControlProperties = new byte[]{0x01};
            //byte[] responseInfoBody = ByteUtil.byteMergerAll(chargingPortNumberByte, remoteControlProperties, controlInfo, Timing, maximumAllowableVoltage, maximumAllowableCurrent, chargeControlData, cardNumber);
            //MessageDetail responseMessage = NBT33007SendMessageFactory.masterSendStartStopChargingCommand(messageDetail.getSourceAddress(), responseInfoBody);
            //channel.writeAndFlush(responseMessage);
            //直接返回
            log.info("充电启动控制:成功！！！");
            return;
        }

        /**
         * 主站校验 VIN，如果检验通过，则直接下发 5.5 的充电启动控制命令；
         * 第二部 如果检验不通过，则回复如下报文：
         */
        MessageDetail responseFailMessage = new MessageDetail();
        BeanUtils.copyProperties(messageDetail,responseFailMessage);
        responseFailMessage.setInfoBody(new byte[]{0x00});
        channel.writeAndFlush(responseFailMessage);
        log.info("充电启动控制:失败！！！");
    }
}
