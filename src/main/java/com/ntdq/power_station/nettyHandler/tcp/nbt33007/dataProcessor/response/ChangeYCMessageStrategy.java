package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description : 变化遥测
 * @Author : Kang
 * @Date : 2023/4/20 21:12
 * @Version : 1.0
 */

public class ChangeYCMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:变化遥测 begin------------");
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
         * 变化遥测
         * 当发送状态变位时，集中器向主站上送变化遥测
         * 68 1E 00 00 00 00 00 00 00 00 00 F9 A7 FF FF 1A 02 01 00 00 00
         * 02 00 00 //遥测地址
         * F0 55 00 //遥测值，前两个字节有效，第三字节保留为 0，遥测值为 0x55F0
         * 05 00 00 //遥测地址
         * 32 00 00 //遥测值，前两个字节有效，第三字节保留为 0，遥测值为 0x0032
         */

        //地址是否连续
        boolean isContinuous = false;

        List<Map<Integer, Integer>> ycList = new ArrayList<>();
        byte[] infoBody = messageDetail.getInfoBody();
        //信息体个数，最高位=1 表示遥测地址连续，低7位表示遥测个数
        //String binaryString = Integer.toBinaryString(messageDetail.getInfoBodyCount());
        String binaryString = ByteUtil.byteToBinary(messageDetail.getInfoBodyCount());
        log.info("信息体个数 = {}",binaryString);
        String[] split = binaryString.split("");
        if (Integer.parseInt(split[0]) == 1){
            //连续
            isContinuous = true;
        }
        //遥测个数
        int ycCount = Integer.parseInt(split[1])*64 +
                Integer.parseInt(split[2])*32 +
                Integer.parseInt(split[3])*16 +
                Integer.parseInt(split[4])*8 +
                Integer.parseInt(split[5])*4 +
                Integer.parseInt(split[6])*2 +
                Integer.parseInt(split[7]);

        int index = 0;
        for (int i = 0; i < ycCount; i++) {
            Map<Integer,Integer> ycMap = new HashMap<>();
            byte[] ycAddress = ByteUtil.getByte(infoBody, index, 3);
            //遥测地址
            String hexStrAddr = ByteUtil.bytesToHexString(ycAddress);
            hexStrAddr = hexStrAddr.replaceAll("(.{2})","$1,");
            if (hexStrAddr.endsWith(",")){
                hexStrAddr = hexStrAddr.substring(0,hexStrAddr.length()-1);
            }
            String[] hexSplitAddr = hexStrAddr.split(",");
            hexStrAddr = hexSplitAddr[2] + hexSplitAddr[1] + hexSplitAddr[0];
            int address = Integer.parseInt(hexStrAddr, 16);
            index += 3;
            byte[] ycValue = ByteUtil.getByte(infoBody, index, 3);
            //遥测值
            String hexStrValue = ByteUtil.bytesToHexString(ycValue);
            hexStrValue = hexStrValue.replaceAll("(.{2})","$1,");
            if (hexStrValue.endsWith(",")){
                hexStrValue = hexStrValue.substring(0,hexStrValue.length()-1);
            }
            String[] hexSplitValue = hexStrValue.split(",");
            hexStrValue = hexSplitValue[1] + hexSplitValue[0];
            int value = Integer.parseInt(hexStrValue, 16);
            index += 3;
            ycMap.put(address, value);
            ycList.add(ycMap);
            log.error("变化遥测地址{}的遥测值：{}",address,value);
        }
        log.error("变化遥测数据：{}",ycList);


        log.info("------------Message:变化遥测 end-------------");
    }
}
