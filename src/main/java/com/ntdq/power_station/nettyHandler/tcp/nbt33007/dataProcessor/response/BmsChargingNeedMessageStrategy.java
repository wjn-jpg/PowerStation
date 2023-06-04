package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 上送BMS充电需求 - 充电过程中上送BMS信息 - 电池充电需求
 * @Author : Kang
 * @Date : 2023/4/20 21:20
 * @Version : 1.0
 */

public class BmsChargingNeedMessageStrategy implements ReceiveMessageStrategy {


    private final static Logger log = LoggerFactory.getLogger(TotalCallMessageStrategy.class);

    @Override
    public void receiveMessage(Channel channel, MessageDetail messageDetail) {
        log.info("-----------Message:上送BMS充电需求 begin------------");
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
        log.info("------------Message:上送BMS充电需求 end-------------");

        byte[] infoBody = messageDetail.getInfoBody();
        int index = 0;
        //2 字节电压需求，分辨率 0.1V。
        byte[] voltageRequirement = ByteUtil.getByte(infoBody, index, 2);
        log.error("电压需求:{}",new String(voltageRequirement));
        index+=2;
        //2 字节电流需求，分辨率 0.1A，-400A 偏移量。
        byte[] currentRequirement = ByteUtil.getByte(infoBody, index++, 2);
        log.error("电流需求:{}",new String(currentRequirement));
        //1 字节充电模式。0x01-恒压充电；0x02-恒流充电。
        byte[] chargingMethod = ByteUtil.getByte(infoBody, index, 1);
        int method = infoBody[infoBody.length - 1] & 0xFF;
        if (method == 1){
            log.error("字节充电模式:恒压充电");
        }
        if (method == 2){
            log.error("字节充电模式:恒流充电");
        }
    }
}
