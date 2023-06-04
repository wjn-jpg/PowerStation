package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.NBT33007SendMessageFactory;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 主站下发计费模型
 * @Author : Kang
 * @Date : 2023/4/20 21:27
 * @Version : 1.0
 */

public class BillingModelMessageStrategy implements ReceiveMessageStrategy {

    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:主站下发计费模型 begin------------");
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

        /**
         * XXH 时段个数 N
         * XXH 时段 1 标识。1：峰时段；
         * 2：尖时段；3：平时段；4：
         * 谷时段
         * XXH 时段 1 起始时，0~23
         * XXH 时段 1 起始分，0~59
         * XXH 时段 1 结束时，0~23
         * XXH 时段 1 结束分，0~59
         * XXH 时段 2 标识。1：峰时段；
         * 2：尖时段；3：平时段；4：
         * 谷时段
         * …… ……
         * XXH 时段 N 标识。1：峰时段；
         * 2：尖时段；3：平时段；4：
         * 谷时段
         * XXH 时段 N 起始时，0~23
         * XXH 时段 N 起始分，0~59
         * XXH 时段 N 结束时，0~23
         * XXH 时段 N 结束分，0~59
         * XXH
         * XXH
         * XXH
         * XXH
         * 峰时段电价，保留小数点
         * 后 5 位。例如 1.35081 元/
         * 度则发送 135801
         * XXH
         * XXH
         * XXH
         * XXH
         * 尖时段电价，保留小数点
         * 后 5 位。例如 1.35081 元/
         * 度则发送 135801
         * XXH
         * XXH
         * XXH
         * XXH
         * 平时段电价，保留小数点
         * 后 5 位。例如 1.35081 元/
         * 度则发送 135801
         * XXH
         * XXH
         * XXH
         * XXH
         * 谷时段电价，保留小数点
         * 后 5 位。例如 1.35081 元/
         * 度则发送 135801
         * XXH
         * XXH
         * XXH
         * XXH
         * 充电服务费，保留小数点
         * 后 5 位。例如 1.35081 元/
         * 度则发送 135801
         */

        /** 计费模型下发,主站向充电桩回复计费模型 */
        //时段个数 1字节
        //时段标识 1字节（多个）时段 1 标识。1：峰时段；2：尖时段；3：平时段；4：谷时段
        //XXH 时段 ？ 起始时，0~23 1字节
        //XXH 时段 ？ 起始分，0~59 1字节
        //XXH 时段 ？ 结束时，0~23 1字节
        //XXH 时段 ？ 结束分，0~59 1字节
        //峰时段电价 4字节
        //尖时段电价 4字节
        //平时段电价 4字节
        //谷时段电价 4字节
        //充电服务费 4字节

        // TODO 模拟数据
        byte[] infoBody = new byte[]{0x02, 0x01, 0x01, 0x00 ,0x04, 0x00, 0x05, 0x00 ,0x08, 0x00, 0x01, 0x02, 0x03, 0x04, 0x01, 0x02, 0x03, 0x04, 0x01, 0x02, 0x03, 0x04, 0x01, 0x02, 0x03, 0x04, 0x01, 0x02, 0x03, 0x04};
        byte[] effectiveLength = new byte[]{0x33, 0x00};
        MessageDetail responseMessage = NBT33007SendMessageFactory.masterResponseBillingModel(effectiveLength, messageDetail.getSourceAddress(), infoBody);
        channel.writeAndFlush(responseMessage);
        log.error("主站向充电桩回复计费模型：命令已发送！！！");

        log.info("------------Message:主站下发计费模型 end-------------");
    }
}
