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
 * @Description : 上送充电记录
 * @Author : Kang
 * @Date : 2023/4/20 21:06
 * @Version : 1.0
 */

public class ChargingRecordMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:上送充电记录 begin------------");
        log.info("启动字符: {}", Integer.toHexString(messageDetail.getStart()));
        log.info("有效长度: {}", messageDetail.getEffectiveLength());
        log.info("控制域: {}", ByteUtil.bytesToHexString(messageDetail.getControlRegion()));
        log.info("目标地址: {}", ByteUtil.bytesToHexString(messageDetail.getTargetAddress()));
        log.info("源地址: {}", ByteUtil.bytesToHexString(messageDetail.getSourceAddress()));
        log.info("帧类型号: {}", Integer.toHexString(messageDetail.getFrameType()));
        log.info("信息体个数 hex: {}", Integer.toHexString(messageDetail.getInfoBodyCount()));
        log.info("信息体个数: {}", messageDetail.getInfoBodyCount());
        log.info("传送原因: {}", ByteUtil.bytesToHexString(messageDetail.getSendReason()));
        log.info("公共地址: {}", ByteUtil.bytesToHexString(messageDetail.getPublicAddress()));
        log.info("信息体地址: {}", ByteUtil.bytesToHexString(messageDetail.getInfoBodyAddress()));
        log.info("信息体: {}", ByteUtil.bytesToHexString(messageDetail.getInfoBody()));

        byte[] effectiveLength = messageDetail.getEffectiveLength();
        String hexString = ByteUtil.bytesToHexString(effectiveLength);
        hexString = hexString.replaceAll("(.{2})", "$1,");
        if (hexString.endsWith(",")) {
            hexString = hexString.substring(0, hexString.length() - 1);
        }
        String[] split = hexString.split(",");
        hexString = split[1] + split[0];
        int len = Integer.parseInt(hexString, 16);

        byte[] infoBody = messageDetail.getInfoBody();
        int infoBodyLen = len - 21;

        int index = 0;
        //充电开始时间
        byte[] startTimeByte = ByteUtil.getByte(infoBody, index, 6);
        index += 6;
        int s = startTimeByte[0] & 0xFF;
        int m = startTimeByte[1] & 0xFF;
        int h = startTimeByte[2] & 0xFF;
        int d = startTimeByte[3] & 0xFF;
        int M = startTimeByte[4] & 0xFF;
        int y = startTimeByte[5] & 0xFF;
        log.error("充电开始时间:20{}-{}-{} {}:{}:{}", y, M, d, h, m, s);
        //充电结束时间
        byte[] endTimeByte = ByteUtil.getByte(infoBody, index, 6);
        index += 6;
        int ss = endTimeByte[0] & 0xFF;
        int mm = endTimeByte[1] & 0xFF;
        int hh = endTimeByte[2] & 0xFF;
        int dd = endTimeByte[3] & 0xFF;
        int MM = endTimeByte[4] & 0xFF;
        int yy = endTimeByte[5] & 0xFF;
        log.error("充电结束时间:20{}-{}-{} {}:{}:{}", yy, MM, dd, hh, mm, ss);
        //16位卡号
        byte[] cardNumberByte = ByteUtil.getByte(infoBody, index, 16);
        index += 16;
        log.error("16位卡号:{}", new String(cardNumberByte, StandardCharsets.US_ASCII));
        //充电前电表读数
        byte[] fontValueByte = ByteUtil.getByte(infoBody, index, 4);
        index += 4;
        float fontValue = ByteUtil.bytesToFloat(fontValueByte);
        log.error("充电前电表读数:{}", fontValue);
        //充电后电表读数
        byte[] afterValueByte = ByteUtil.getByte(infoBody, index, 4);
        index += 4;
        float afterValue = ByteUtil.bytesToFloat(afterValueByte);
        log.error("充电后电表读数:{}", afterValue);
        //本次充电电量
        byte[] capacityByte = ByteUtil.getByte(infoBody, index, 4);
        index += 4;
        float capacity = ByteUtil.bytesToFloat(capacityByte);
        log.error("本次充电电量:{}", capacity);
        //本次充电金额
        byte[] amountByte = ByteUtil.getByte(infoBody, index, 4);
        index += 4;
        float amount = ByteUtil.bytesToFloat(amountByte);
        log.error("本次充电金额:{}", amount);
        //充电前卡余额
        byte[] fontAmountByte = ByteUtil.getByte(infoBody, index, 4);
        index += 4;
        float fontAmount = ByteUtil.bytesToFloat(fontAmountByte);
        log.error("充电前卡余额:{}", fontAmount);
        //充电后卡余额
        byte[] afterAmountByte = ByteUtil.getByte(infoBody, index, 4);
        index += 4;
        float afterAmount = ByteUtil.bytesToFloat(afterAmountByte);
        log.error("充电后卡余额:{}", afterAmount);

        // TODO ----------------------- 报文长度不固定，待处理 2023-04-25 不够4位导致解析报错 已修改待测试 --------------------------------

        //本次充电峰电量
        int temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] peakPowerByte = ByteUtil.getByte(infoBody, index, 4);
            index += 4;
            float peakPower = ByteUtil.bytesToFloat(peakPowerByte);
            log.error("本次充电峰电量:{}", peakPower);
        }

        //本次充电谷电量
        temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] valleyPowerByte = ByteUtil.getByte(infoBody, index, 4);
            index += 4;
            float valleyPower = ByteUtil.bytesToFloat(valleyPowerByte);
            log.error("本次充电谷电量:{}", valleyPower);
        }

        //本次充电尖电量
        temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] tipPowerByte = ByteUtil.getByte(infoBody, index, 4);
            index += 4;
            float tipPower = ByteUtil.bytesToFloat(tipPowerByte);
            log.error("本次充电尖电量:{}", tipPower);
        }

        //本次充电平电量
        temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] flatPowerByte = ByteUtil.getByte(infoBody, index, 4);
            index += 4;
            float flatPower = ByteUtil.bytesToFloat(flatPowerByte);
            log.error("本次充电平电量:{}", flatPower);
        }

        //本次充电峰时段充电费用
        temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] peakPowerAmountByte = ByteUtil.getByte(infoBody, index, 4);
            index += 4;
            float peakPowerAmount = ByteUtil.bytesToFloat(peakPowerAmountByte);
            log.error("本次充电峰时段充电费用:{}", peakPowerAmount);
        }

        //本次充电谷时段充电费用
        temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] valleyPowerAmountByte = ByteUtil.getByte(infoBody, index, 4);
            index += 4;
            float valleyPowerAmount = ByteUtil.bytesToFloat(valleyPowerAmountByte);
            log.error("本次充电谷时段充电费用:{}", valleyPowerAmount);
        }

        //本次充电尖时段充电费用
        temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] tipPowerAmountByte = ByteUtil.getByte(infoBody, index, 4);
            index += 4;
            float tipPowerAmount = ByteUtil.bytesToFloat(tipPowerAmountByte);
            log.error("本次充电尖时段充电费用:{}", tipPowerAmount);
        }

        //本次充电平时段充电费用
        temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] flatPowerAmountByte = ByteUtil.getByte(infoBody, index, 4);
            index += 4;
            float flatPowerAmount = ByteUtil.bytesToFloat(flatPowerAmountByte);
            log.error("本次充电平时段充电费用:{}", flatPowerAmount);
        }

        //中止SOC：数据分辨率：1%/位，0%偏移量；数据范围：0%~100%
        temp = index;
        temp++;
        if (temp <= infoBodyLen) {
            byte[] stopSOCByte = ByteUtil.getByte(infoBody, index, 1);
            index++;
        }

        //SHORT 类型，2字节充电中止原因
        temp = index;
        temp += 2;
        if (temp <= infoBodyLen) {
            byte[] stopReasonByte = ByteUtil.getByte(infoBody, index, 2);
            index += 2;
        }

        //充电中止原因的解释或辅助参数信息
        temp = index;
        temp += 4;
        if (temp <= infoBodyLen) {
            byte[] explainByte = ByteUtil.getByte(infoBody, index, 4);
        }

        log.error("------------Message:上送充电记录 end-------------");

        /** 主站收到充电记录后向集中器发送确认 */
        byte[] sendReason = new byte[]{0x04, 0x00};
        MessageDetail message = new MessageDetail();
        BeanUtils.copyProperties(messageDetail, message);
        message.setSendReason(sendReason);
        channel.writeAndFlush(message);
    }
}
