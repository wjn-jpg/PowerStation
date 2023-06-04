package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @Description : 卡信息查询
 * @Author : Kang
 * @Date : 2023/4/20 21:16
 * @Version : 1.0
 */

public class CardInfoQueryMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:卡信息查询 begin------------");
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

        /**
         * 查询卡信息
         * 充电桩查询卡信息：
         * 68 XX XX 00 00 00 00 00 00 00 00 F9 A7 FF FF 13 01 03 00 00 00
         * 00 00 00 //信息体地址，固定为 0
         * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
         * 06 //密码长度
         * 31 32 33 34 35 36 // 密码，ASCII 码
         * 主站返回卡信息：
         * 68 XX XX 00 00 00 00 F9 A7 FF FF 00 00 00 00 13 01
         * 00 00 00 //信息体地址，固定为 0
         * 04 00 //传送原因，04—确认
         * 00 00 //公共地址
         * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
         * 02 // 卡状态
         * XX XX XX XX //卡余额，FLOAT
         *
         * 注：
         * 状态：0-卡不存在；1-卡未激活；2-正常；3-黑名单；4-已挂失；5-已注销
         */

        //解析
        byte[] infoBody = messageDetail.getInfoBody();
        int index = 0;
        //卡号
        byte[] cardNumber = ByteUtil.getByte(infoBody, index, 16);
        String cardNumberStr = new String(cardNumber, StandardCharsets.US_ASCII);
        log.error("卡号：{}",cardNumberStr);
        index += 16;
        //密码长度
        byte[] passwordLen = ByteUtil.getByte(infoBody, index++, 1);
        //密码个数
        //int passwordCount = ByteUtil.bytesToInt(passwordLen);
        int passwordCount = Integer.parseInt(ByteUtil.bytesToHexString(passwordLen), 16);
        //密码 ASCII码
        byte[] password = ByteUtil.getByte(infoBody, index, passwordCount);
        String passwordStr = new String(password, StandardCharsets.US_ASCII);
        log.error("密码：{}",passwordStr);

        /** 获取到卡号密码 --> 进行业务处理 -->  发消息确认 */

        //TODO 此处状态 new byte[]{0x02} 临时，后期根据业务更改
        //卡状态
        byte[] status = new byte[]{0x02};   // 0-卡不存在；1-卡未激活；2-正常；3-黑名单；4-已挂失；5-已注销
        //卡余额 TODO 模拟数据
        byte[] balance = new byte[]{0x23,0x23,0x32,0x32};
        byte[] responseInfoBody = ByteUtil.byteMergerAll(cardNumber, status, balance);

        MessageDetail responseMessage = new MessageDetail();
        responseMessage.setStart(messageDetail.getStart());
        //byte[] len = ByteUtil.intToByteArray(21 + responseInfoBody.length);
        byte[] len = ByteUtil.intToBytesBig(21 + responseInfoBody.length);
        String hexString = ByteUtil.bytesToHexString(len);
        hexString = hexString.replaceAll("(.{2})","$1,");
        if (hexString.endsWith(",")){
            hexString = hexString.substring(0,hexString.length()-1);
        }
        String[] split = hexString.split(",");
        hexString = split[1] + split[0];
        byte[] length = ByteUtil.hexStringToByte(hexString);
        responseMessage.setEffectiveLength(length);
        responseMessage.setControlRegion(messageDetail.getControlRegion());
        responseMessage.setTargetAddress(messageDetail.getSourceAddress());
        responseMessage.setSourceAddress(messageDetail.getTargetAddress());
        responseMessage.setFrameType(messageDetail.getFrameType());
        responseMessage.setInfoBodyCount(messageDetail.getInfoBodyCount());
        byte[] reason = new byte[]{0x04,0x00};  //传送原因，04—激活确认
        responseMessage.setSendReason(reason);
        responseMessage.setPublicAddress(messageDetail.getPublicAddress());
        responseMessage.setInfoBodyAddress(messageDetail.getInfoBodyAddress());
        responseMessage.setInfoBody(responseInfoBody);

        channel.writeAndFlush(responseMessage);


        log.info("------------Message:卡信息查询 end-------------");
    }
}
