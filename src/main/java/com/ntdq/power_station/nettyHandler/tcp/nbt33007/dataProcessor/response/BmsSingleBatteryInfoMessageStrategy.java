package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 上送BMS单体电池信息
 * @Author : Kang
 * @Date : 2023/4/20 21:24
 * @Version : 1.0
 */

public class BmsSingleBatteryInfoMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:上送BMS单体电池信息 begin------------");
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
        log.info("------------Message:上送BMS单体电池信息 end-------------");

        //有效长度
        byte[] effectiveLength = messageDetail.getEffectiveLength();
        //消息体
        byte[] infoBody = messageDetail.getInfoBody();
        int index = 0;
        //电池分组号（1 字节）
        byte[] groupNo = ByteUtil.getByte(infoBody, index++, 1);
        //本组电池单体总数（2 字节）
        byte[] singleTotal = ByteUtil.getByte(infoBody, index, 2);
        index += 2;
        //本帧起始单体电池序号（2 字节）
        byte[] singleNo = ByteUtil.getByte(infoBody, index, 2);
        index += 2;
        //本帧上送的单体个数 m（1 字节）
        byte[] singleCount = ByteUtil.getByte(infoBody, index++, 1);

        String hexString = ByteUtil.bytesToHexString(effectiveLength);
        hexString = hexString.replaceAll("(.{2})", "$1,");
        if (hexString.endsWith(",")){
            hexString = hexString.substring(0,hexString.length()-1);
        }
        String[] split = hexString.split(",");
        hexString = split[1] + split[0];
        int len = Integer.parseInt(hexString, 16);
        //包含所有的单体电压值，再进一步解析
        byte[] singleVoltageValueList = ByteUtil.getByte(infoBody, index, len-27);
        int indexs = 0;
        for (int i = 0; i < singleVoltageValueList.length/2; i++) {
            byte[] singleVoltageValue = ByteUtil.getByte(singleVoltageValueList, indexs, 2);
            String hexStr = ByteUtil.bytesToHexString(singleVoltageValue);
            hexStr = hexStr.replaceAll("(.{2})","$1,");
            if (hexStr.endsWith(",")){
                hexStr = hexStr.substring(0,hexStr.length()-1);
            }
            String[] split1 = hexStr.split(",");
            hexStr = split1[1] + split1[0];
            byte[] bytes = ByteUtil.byteMergerAll(new byte[]{0x00}, new byte[]{0x00}, new byte[]{singleVoltageValue[1]}, new byte[]{singleVoltageValue[0]});
            float voltageValue = ByteUtil.bytesToFloat(bytes, 2);
            log.error("单体电压值{}：float值{}",i,voltageValue);
            log.error("单体电压值{}：int值{}",i,Integer.parseInt(hexStr,16));
            log.error("单体电压值{}：hex值{}",i,hexStr);
        }
    }
}
