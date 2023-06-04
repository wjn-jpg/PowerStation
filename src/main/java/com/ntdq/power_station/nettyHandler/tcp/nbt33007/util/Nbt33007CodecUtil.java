package com.ntdq.power_station.nettyHandler.tcp.nbt33007.util;


import com.ntdq.power_station.nettyHandler.tcp.nbt33007.constant.Nbt33007FrameTypeConstant;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Description : NBT33007协议编解码工具类
 * @Author : Kang
 * @Date : 2023/4/19 18:39
 * @Version : 1.0
 */

public class Nbt33007CodecUtil {

    /**
     * 日志
     */
    private final static Logger log = LoggerFactory.getLogger(Nbt33007CodecUtil.class);

    /**
     * 将bytes 转换成报文消息实体
     * @param bytes 报文原始数据
     * @return
     */
    public static MessageDetail decode(byte[] bytes){
        log.error("原始报文：{}", Arrays.toString(bytes));
        String hexStr = ByteUtil.bytesToHexString(bytes);
        hexStr = hexStr.replaceAll("(.{2})","$1 ");
        if (hexStr.endsWith(" ")){
            hexStr = hexStr.substring(0,hexStr.length()-1);
        }
        log.error("原始报文hex：{}",hexStr);
        MessageDetail messageDetail = new MessageDetail();
        int index = 0;
        /** -----APCI----- */
        messageDetail.setStart(bytes[index++]);
        byte[] effectiveLength = ByteUtil.getByte(bytes, index, 2);
        index += 2;
        messageDetail.setEffectiveLength(effectiveLength);
        messageDetail.setControlRegion(ByteUtil.getByte(bytes,index,4));
        index += 4;
        messageDetail.setTargetAddress(ByteUtil.getByte(bytes,index,4));
        index += 4;
        messageDetail.setSourceAddress(ByteUtil.getByte(bytes,index,4));
        index += 4;
        String hexString = ByteUtil.bytesToHexString(effectiveLength);
        hexString = hexString.replaceAll("(.{2})","$1,");
        if (hexString.endsWith(",")){
            hexString = hexString.substring(0,hexString.length()-1);
        }
        String[] hexSplit = hexString.split(",");
        hexString = hexSplit[1] + hexSplit[0];
        int len = Integer.parseInt(hexString, 16);
        if (len <= 12){
            return messageDetail;
        }
        /** -----ASDU----- */
        messageDetail.setFrameType(bytes[index++]);
        messageDetail.setInfoBodyCount(bytes[index++]);
        messageDetail.setSendReason(ByteUtil.getByte(bytes,index,2));
        index += 2;
        messageDetail.setPublicAddress(ByteUtil.getByte(bytes,index,2));
        index += 2;

        /** 充电桩向主站请求计费模型 */
        if (messageDetail.getFrameType() == Nbt33007FrameTypeConstant.BILLING_MODEL){
            return messageDetail;
        }

        if (messageDetail.getFrameType() != Nbt33007FrameTypeConstant.FAULT_CODE &&
                messageDetail.getFrameType() != Nbt33007FrameTypeConstant.CHANGE_YX &&
                messageDetail.getFrameType() != Nbt33007FrameTypeConstant.CHANGE_YC &&
                messageDetail.getFrameType() != Nbt33007FrameTypeConstant.FAULT_ALARM_DETAILS){

            messageDetail.setInfoBodyAddress(ByteUtil.getByte(bytes,index,3));
            index += 3;
            /** 召唤充电桩档案-主站向下发请求: 没有信息体-->直接返回 */
            if (messageDetail.getFrameType() == Nbt33007FrameTypeConstant.CHARGING_PILE_ARCHIVES_INFO){
                return messageDetail;
            }

            messageDetail.setInfoBody(ByteUtil.getByte(bytes,index,len-21));
        }
        /** 故障主动上报、变位遥信、变化遥测: 没有信息体地址 */
        if (messageDetail.getFrameType() == Nbt33007FrameTypeConstant.FAULT_CODE ||
                messageDetail.getFrameType() == Nbt33007FrameTypeConstant.CHANGE_YX ||
                messageDetail.getFrameType() == Nbt33007FrameTypeConstant.CHANGE_YC ||
                messageDetail.getFrameType() == Nbt33007FrameTypeConstant.FAULT_ALARM_DETAILS){

            messageDetail.setInfoBody(ByteUtil.getByte(bytes,index,len-18));
        }

        return messageDetail;
    }

    /**
     * 将消息实体转换成bytes
     * @param messageDetail 一条报文的消息实体
     * @return bytes
     * @throws IOException
     */
    public static byte[] encode(MessageDetail messageDetail) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bytes.write(messageDetail.getStart());
        bytes.write(messageDetail.getEffectiveLength());
        bytes.write(messageDetail.getControlRegion());
        bytes.write(messageDetail.getTargetAddress());
        bytes.write(messageDetail.getSourceAddress());
        String hexString = ByteUtil.bytesToHexString(messageDetail.getEffectiveLength());
        hexString = hexString.replaceAll("(.{2})","$1,");
        if (hexString.endsWith(",")){
            hexString = hexString.substring(0,hexString.length()-1);
        }
        String[] hexSplit = hexString.split(",");
        hexString = hexSplit[1] + hexSplit[0];
        int len = Integer.parseInt(hexString, 16);
        if (len <= 12){
            return bytes.toByteArray();
        }
        bytes.write(messageDetail.getFrameType());
        bytes.write(messageDetail.getInfoBodyCount());
        bytes.write(messageDetail.getSendReason());
        bytes.write(messageDetail.getPublicAddress());
        bytes.write(messageDetail.getInfoBodyAddress());
        bytes.write(messageDetail.getInfoBody());
        return bytes.toByteArray();
    }
}
