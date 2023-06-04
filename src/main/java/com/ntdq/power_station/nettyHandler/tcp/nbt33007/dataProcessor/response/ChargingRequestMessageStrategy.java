package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.NBT33007SendMessageFactory;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @Description : 刷卡充电请求
 * @Author : Kang
 * @Date : 2023/4/20 21:07
 * @Version : 1.0
 */
public class ChargingRequestMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:刷卡充电请求 begin------------");
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

        //有效长度
        byte[] effectiveLength = messageDetail.getEffectiveLength();

        //信息体地址，获取充电口编号
        byte[] infoBodyAddress = messageDetail.getInfoBodyAddress();
        String hexString = ByteUtil.bytesToHexString(infoBodyAddress);
        hexString = hexString.replaceAll("(.{2})", "$1,");
        if (hexString.endsWith(",")) {
            hexString = hexString.substring(0, hexString.length() - 1);
        }
        String[] split = hexString.split(",");
        String chargingPortNumberHex = split[2] + split[1] + split[0];
        //充电口编号
        //int chargingPortNumber = Integer.parseInt(chargingPortNumberHex, 16);
        byte chargingPortNumber = Byte.parseByte(chargingPortNumberHex, 16);
        byte[] chargingPortNumberByte = new byte[]{chargingPortNumber};

        //解析消息体
        byte[] requestInfoBody = messageDetail.getInfoBody();
        int index = 0;
        //固定02，表示启动充电
        byte[] flag = ByteUtil.getByte(requestInfoBody, index++, 1);
        //16 位卡号 ASCII 码
        byte[] cardNumber = ByteUtil.getByte(requestInfoBody, index, 16);
        String cardNumberStr = new String(cardNumber,StandardCharsets.US_ASCII);
        index += 16;
        //控制信息，对于交流充电桩可不处理
        byte[] controlInfo = ByteUtil.getByte(requestInfoBody, index++, 1);
        //定时时间，对于交流充电桩可不处理
        byte[] Timing = ByteUtil.getByte(requestInfoBody, index, 4);
        index += 4;
        //最高允许电压，对于交流充电桩可不处理
        byte[] maximumAllowableVoltage = ByteUtil.getByte(requestInfoBody, index, 4);
        index += 4;
        //最高允许电流，对于交流充电桩可不处理
        byte[] maximumAllowableCurrent = ByteUtil.getByte(requestInfoBody, index, 4);
        index += 4;
        //充电控制数据，对于交流充电桩可不处理
        byte[] chargeControlData = ByteUtil.getByte(requestInfoBody, index, 4);
        index += 4;
        //密码长度
        byte[] passwordLength = ByteUtil.getByte(requestInfoBody, index++, 1);
        //密码个数
        String hexStr = ByteUtil.bytesToHexString(passwordLength);
        //int passwordCount = ByteUtil.bytesToInt(passwordLength);
        int passwordCount = Integer.parseInt(hexStr, 16);
        //密码
        byte[] password = ByteUtil.getByte(requestInfoBody, index, passwordCount);
        String passwordStr = new String(password);

        /**
         * 命令发送触发逻辑如下：
         * 主站接收到刷卡启动充电请求后进行校验，若校验通过，则直接下发启动充电命令（5.5），
         * 待接收到充电桩响应后再返回刷卡请求处理结果（可能成功或失败，取决于充电桩的响应结果）；
         * 若校验不通过，则直接返回刷卡请求处理结果（失败）。
         * -
         * 注意：这里是校验通过后发送启停控制的命令；
         * 若校验通过，则主站返回刷卡请求结果在启停控制消息中判断触发；
         * 若校验不通过，则直接返回刷卡请求处理结果（失败）。
         */

        // TODO 测试 假设校验通过
        boolean isPass = true;

        if (isPass){
            /** 第一步 通过账号密码进行校验，校验通过发送启动充电的命令 */

            //遥控性质，1 表示启动充电，0 表示停止充电
            byte[] remoteControlProperties = new byte[]{0x01};
            byte[] responseInfoBody = ByteUtil.byteMergerAll(chargingPortNumberByte, remoteControlProperties, controlInfo, Timing, maximumAllowableVoltage, maximumAllowableCurrent, chargeControlData, cardNumber);
            MessageDetail responseMessage = NBT33007SendMessageFactory.masterSendStartStopChargingCommand(messageDetail.getSourceAddress(), responseInfoBody);
            channel.writeAndFlush(responseMessage);
            log.error("启动充电命令,已发送！！！");
            //直接返回
            return;
        }
        /** 若校验不通过,进入第二步,则直接返回刷卡请求处理结果（失败）的命令 */
        MessageDetail failMessage = new MessageDetail();
        failMessage.setStart(messageDetail.getStart());
        failMessage.setControlRegion(messageDetail.getControlRegion());
        failMessage.setTargetAddress(messageDetail.getSourceAddress());
        failMessage.setSourceAddress(messageDetail.getTargetAddress());
        failMessage.setFrameType(messageDetail.getFrameType());
        failMessage.setInfoBodyCount(messageDetail.getInfoBodyCount());
        failMessage.setSendReason(messageDetail.getSendReason());
        failMessage.setPublicAddress(messageDetail.getPublicAddress());
        failMessage.setInfoBodyAddress(messageDetail.getInfoBodyAddress());
        //请求处理结果
        byte[] requestProcessingResult = new byte[]{0x00};  //失败
        //处理结果文字提示信息，ASCII码
        byte[] resultInfo = "账号密码校验失败".getBytes(StandardCharsets.US_ASCII);
        //结果字符长度，最大255字节
        int resultLength = resultInfo.length;
        byte[] resultLengthByte = new byte[]{(byte) resultLength};
        byte[] failResponseInfoBody = ByteUtil.byteMergerAll(requestProcessingResult,resultLengthByte,resultInfo);
        byte[] len = ByteUtil.intToByteArray(23 + resultLength);
        failMessage.setEffectiveLength(len);
        failMessage.setInfoBody(failResponseInfoBody);
        channel.writeAndFlush(failMessage);

        log.info("------------Message:刷卡充电请求 end-------------");
    }
}
