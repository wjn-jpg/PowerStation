package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @Description : 充电卡扣值 - 充电桩返回控制执行结果
 * @Author : Kang
 * @Date : 2023/4/20 21:08
 * @Version : 1.0
 */

public class DeductionValueMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:充电卡扣值 begin------------");
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

        /**
         * 充电桩返回控制执行结果：
         * 68 2d 00 00 00 00 00 00 00 00 00 F9 A7 01 00 96 01
         * 04 00 //传送原因，04—确认，表示扣值成功；若失败，传送原因为 06
         * 00 00 //公共地址
         * 00 00 00 //信息体地址，固定为 0
         * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
         * XX XX XX XX //扣值前卡余额，FLOAT
         * XX XX XX XX //扣值后卡余额，FLOAT
         */
        byte sendReason = messageDetail.getSendReason()[0];
        byte[] infoBody = messageDetail.getInfoBody();
        int index = 0;
        //卡号
        byte[] cardNumber = ByteUtil.getByte(infoBody, index, 16);
        String cardNumberStr = new String(cardNumber, StandardCharsets.US_ASCII);
        index += 16;
        //扣值前卡余额，FLOAT
        byte[] beforeBalance = ByteUtil.getByte(infoBody, index, 4);
        float beforeBalanceValue = ByteUtil.bytesToFloat(beforeBalance);
        index += 4;
        //扣值后卡余额，FLOAT
        byte[] afterBalance = ByteUtil.getByte(infoBody, index, 4);
        float afterBalanceValue = ByteUtil.bytesToFloat(afterBalance);

        if ((sendReason & 0xFF) == 4) {
            log.info("充电卡扣值: 成功!!!");
            log.info("卡号为: {}", cardNumberStr);
            log.info("扣值前卡余额为: {}", beforeBalanceValue);
            log.info("扣值后卡余额为: {}", afterBalanceValue);
        }
        if ((sendReason & 0xFF) == 6) {
            log.info("充电卡扣值: 失败!!!");
            log.info("卡号为: {}", cardNumberStr);
            log.info("扣值前卡余额为: {}", beforeBalanceValue);
            log.info("扣值后卡余额为: {}", afterBalanceValue);
        }


        log.info("------------Message:充电卡扣值 end-------------");
    }
}
