package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 上送故障代码
 * @Author : Kang
 * @Date : 2023/4/20 21:01
 * @Version : 1.0
 */


public class FaultCodeMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:上送故障代码 begin------------");
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

        /**
         * 故障主动上报
         * 当新的故障发生，或者原来的故障消除时，立即主动上报故障信息
         * 68 16 00 00 00 00 00 00 00 00 00 F9 A7 01 00 12 01 01 00 00 00
         * 65 00 00 //故障代码值
         * 00 //故障状态--故障恢复
         * 68 16 00 00 00 00 00 00 00 00 00 F9 A7 01 00 12 01 01 00 00 00
         * 69 00 00 //故障代码值
         * 01 //故障状态--故障发生
         */

        //故障个数
        byte infoBodyCount = messageDetail.getInfoBodyCount();

        if (infoBodyCount != 0x00){
            //遥测值
            byte[] infoBody = messageDetail.getInfoBody();
            int count = 1;
            for (int i = 0; i < infoBody.length; i+=4) {
                byte[] bytes = ByteUtil.getByte(infoBody, i, 4);
                //故障代码
                byte[] errorCodeByte = ByteUtil.getByte(bytes, 0, 3);
                //故障状态
                byte errorStatusByte = bytes[3];//1-故障发生
                String hexString = ByteUtil.bytesToHexString(errorCodeByte);
                hexString = hexString.replaceAll("(.{2})","$1,");
                if (hexString.endsWith(",")){
                    hexString = hexString.substring(0,hexString.length()-1);
                }
                String[] hexSplit = hexString.split(",");
                hexString = hexSplit[2] + hexSplit[1] + hexSplit[0];
                log.error("故障代码{}：{}",count, Integer.parseInt(hexString,16));
                log.error("故障状态{}：{}",count,errorStatusByte);
                count++;
            }
        }else {
            log.info("没有故障...");
        }

        log.info("------------Message:上送故障代码 end-------------");
    }
}
