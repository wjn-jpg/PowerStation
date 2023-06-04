package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;


import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 充电机功率调控 - 充电机返回确认 - 帧类型，0x2E—充电最大功率控制
 * @Author : Kang
 * @Date : 2023/4/20 21:10
 * @Version : 1.0
 */

public class PowerRegulationMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:充电机功率调控 begin------------");
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
        log.info("------------Message:充电机功率调控 end-------------");

        byte sendReason = messageDetail.getSendReason()[0];
        if ((sendReason & 0xFF) == 4){
            log.info("充电机功率调控:成功！！！");
            //最大充电功率
            byte[] infoBody = messageDetail.getInfoBody();
            //double maximumChargingPower = ByteUtil.bytesToInt(infoBody) / 100;
            String hexString = ByteUtil.bytesToHexString(infoBody);
            hexString = hexString.replaceAll("(.{2})","$1,");
            if (hexString.endsWith(",")){
                hexString = hexString.substring(0,hexString.length()-1);
            }
            String[] split = hexString.split(",");
            hexString = split[3] + split[2] + split[1] + split[0];
            int maxPowerValue = Integer.parseInt(hexString, 16);
            double maximumChargingPower = maxPowerValue / 100.00;
            log.info("最大充电功率(double):{}kW",maximumChargingPower);
            //log.info("最大充电功率(int):{}kW",maxPowerValue);
        }
        if ((sendReason & 0xFF) == 6){
            log.info("充电机功率调控:失败！！！");
        }
    }
}
