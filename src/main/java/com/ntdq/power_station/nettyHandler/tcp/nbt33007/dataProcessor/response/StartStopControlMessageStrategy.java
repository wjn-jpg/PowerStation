package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @Description : 充电启停控制 - 集中器接收到命令后应答（传送原因改为 4 表示启动成功，6 表示失败
 * @Author : Kang
 * @Date : 2023/4/20 21:05
 * @Version : 1.0
 */

public class StartStopControlMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:充电启停控制 begin------------");
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

        byte sendReason = messageDetail.getSendReason()[0];
        if ((sendReason & 0xFF) == 4){
            log.info("充电启停控制: 启动成功!!!");
            /** 启动成功 - 返回刷卡请求处理成功结果  待接收到充电桩响应后再返回刷卡请求处理结果（可能成功或失败，取决于充电桩的响应结果） */
            MessageDetail successMessage = new MessageDetail();
            successMessage.setStart(messageDetail.getStart());
            successMessage.setControlRegion(messageDetail.getControlRegion());
            successMessage.setTargetAddress(messageDetail.getSourceAddress());
            successMessage.setSourceAddress(messageDetail.getTargetAddress());
            successMessage.setFrameType(messageDetail.getFrameType());
            successMessage.setInfoBodyCount(messageDetail.getInfoBodyCount());
            byte[] reason = new byte[]{0x03,0x00};
            successMessage.setSendReason(reason);
            successMessage.setPublicAddress(messageDetail.getPublicAddress());
            successMessage.setInfoBodyAddress(messageDetail.getInfoBodyAddress());
            //请求处理结果
            byte[] requestProcessingResult = new byte[]{0x01};  //成功
            //处理结果文字提示信息，ASCII码
            byte[] resultInfo = "充电启动成功".getBytes(StandardCharsets.US_ASCII);
            //结果字符长度，最大255字节
            int resultLength = resultInfo.length;
            byte[] resultLengthByte = new byte[]{(byte) resultLength};
            byte[] failResponseInfoBody = ByteUtil.byteMergerAll(requestProcessingResult,resultLengthByte,resultInfo);
            byte[] len = ByteUtil.intToByteArray(23 + resultLength);
            successMessage.setEffectiveLength(len);
            successMessage.setInfoBody(failResponseInfoBody);
            channel.writeAndFlush(successMessage);
        }
        if ((sendReason & 0xFF) == 6){
            log.info("充电启停控制: 启动失败!!!");
            /** 启动失败 - 返回刷卡请求处理失败结果 */
            MessageDetail failMessage = new MessageDetail();
            failMessage.setStart(messageDetail.getStart());
            failMessage.setControlRegion(messageDetail.getControlRegion());
            failMessage.setTargetAddress(messageDetail.getSourceAddress());
            failMessage.setSourceAddress(messageDetail.getTargetAddress());
            failMessage.setFrameType(messageDetail.getFrameType());
            failMessage.setInfoBodyCount(messageDetail.getInfoBodyCount());
            byte[] reason = new byte[]{0x03,0x00};
            failMessage.setSendReason(reason);
            failMessage.setPublicAddress(messageDetail.getPublicAddress());
            failMessage.setInfoBodyAddress(messageDetail.getInfoBodyAddress());
            //请求处理结果
            byte[] requestProcessingResult = new byte[]{0x00};  //失败
            //处理结果文字提示信息，ASCII码
            byte[] resultInfo = "充电启动失败".getBytes(StandardCharsets.US_ASCII);
            //结果字符长度，最大255字节
            int resultLength = resultInfo.length;
            byte[] resultLengthByte = new byte[]{(byte) resultLength};
            byte[] failResponseInfoBody = ByteUtil.byteMergerAll(requestProcessingResult,resultLengthByte,resultInfo);
            byte[] len = ByteUtil.intToByteArray(23 + resultLength);
            failMessage.setEffectiveLength(len);
            failMessage.setInfoBody(failResponseInfoBody);
            channel.writeAndFlush(failMessage);
        }
        log.info("------------Message:充电启停控制 end-------------");
    }
}
