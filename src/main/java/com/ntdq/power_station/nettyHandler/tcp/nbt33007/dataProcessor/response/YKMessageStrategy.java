package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 遥控
 * @Author : Kang
 * @Date : 2023/4/20 21:30
 * @Version : 1.0
 */

public class YKMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:遥控 begin------------");
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

        byte[] sendReason = messageDetail.getSendReason();
        byte[] infoBody = messageDetail.getInfoBody();
        String hexString = ByteUtil.bytesToHexString(sendReason);
        hexString = hexString.replaceAll("(.{2})", "$1,");
        if (hexString.endsWith(",")){
            hexString = hexString.substring(0,hexString.length()-1);
        }
        String[] split = hexString.split(",");
        hexString = split[1] + split[0];
        int reason = Integer.parseInt(hexString, 16);
        byte funCode = infoBody[0];
        byte he = infoBody[1];

        if (reason == 4){
            log.error("遥控处理成功...");
            log.error("遥控功能码：{}",funCode & 0xFF);
            log.error("合/分：{}",he & 0xFF);
        }
        if (reason == 6){
            log.error("遥控处理失败...");
        }

        log.info("------------Message:遥控 end-------------");
    }
}
