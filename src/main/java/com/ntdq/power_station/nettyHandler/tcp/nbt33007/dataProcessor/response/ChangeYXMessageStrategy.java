package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 主动上送变化遥信
 * @Author : Kang
 * @Date : 2023/4/20 21:04
 * @Version : 1.0
 */

public class ChangeYXMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:主动上送变化遥信 begin------------");
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
        log.info("信息体: {}",ByteUtil.bytesToHexString(messageDetail.getInfoBody()));

        boolean isContinuous = false;

        //信息体个数，最高位=0 表示遥信地址不连续，最高位 0，表示地址不连续，每个数据项都带地址，低7位表示遥信个数
        //String binaryString = Integer.toBinaryString(messageDetail.getInfoBodyCount());
        String binaryString = ByteUtil.byteToBinary(messageDetail.getInfoBodyCount());
        log.error("信息体个数 = {}",binaryString);
        String[] split = binaryString.split("");
        if (Integer.parseInt(split[0]) == 1){
            isContinuous = true;
        }
        //遥信个数
        int ycCount = Integer.parseInt(split[1])*64 +
                Integer.parseInt(split[2])*32 +
                Integer.parseInt(split[3])*16 +
                Integer.parseInt(split[4])*8 +
                Integer.parseInt(split[5])*4 +
                Integer.parseInt(split[6])*2 +
                Integer.parseInt(split[7]);
        //信息体长度
        int infoBodyLen = ycCount / 4;

        //遥信值
        byte[] infoBody = messageDetail.getInfoBody();
        for (int i = 0; i < infoBody.length; i+=4) {
            byte[] bytes = ByteUtil.getByte(infoBody, i, 4);
            String hexString = ByteUtil.bytesToHexString(bytes);
            hexString = hexString.replaceAll("(.{2})","$1,");
            if (hexString.endsWith(",")){
                hexString = hexString.substring(0,hexString.length()-1);
            }
            String[] hexSplit = hexString.split(",");
            hexString = hexSplit[2] + hexSplit[1] + hexSplit[0];
            int yxAddress = Integer.parseInt(hexString, 16);
            String hexValue = hexSplit[3];
            int yxValue = Integer.parseInt(hexValue, 16);
            log.info("变位遥信地址{}：遥信值{}",yxAddress,yxValue);
        }

        log.info("------------Message:主动上送变化遥信 end-------------");
    }
}
