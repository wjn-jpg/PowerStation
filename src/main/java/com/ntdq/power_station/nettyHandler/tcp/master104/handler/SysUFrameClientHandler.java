package com.ntdq.power_station.nettyHandler.tcp.master104.handler;

import com.ntdq.power_station.domain.enumDo.UControlEnum;
import com.ntdq.power_station.nettyHandler.DefaultChannelHandler;
import com.ntdq.power_station.nettyHandler.tcp.master104.constant.BasicInstruction104;
import com.ntdq.power_station.nettyHandler.tcp.master104.core.Iec104ThreadLocal;
import com.ntdq.power_station.nettyHandler.tcp.master104.domain.MessageDetail;
import com.ntdq.power_station.util.ByteUtil;
import com.ntdq.power_station.util.Iec104Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * U形帧 解析
 *
 * @author wang_ji_nian
 */
public class SysUFrameClientHandler extends DefaultChannelHandler<ByteBuf> {

    private static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(SysUFrameClientHandler.class);

    @Override
    protected void read(ChannelHandlerContext context, ByteBuf msg) {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        if (isSysInstruction(bytes)) { //如果不是U帧 则说明可能是I帧
            UControlEnum ucontrol = Iec104Util.getUcontrol(ByteUtil.getByte(bytes, 2, 4));
            if (ucontrol != null) { //如果是U 就直接退回 如果是I就要交给下一个Handler
                logger.info("检测到U帧数据准备进行对应命令发送,此事U帧类型:{}", ucontrol.name());
                processUHandler(context, msg, ucontrol);
                return;
            }
        }
        //如果不是S 则可能是U帧 要将数据传给下一个U帧解析handler
        msg.writeBytes(bytes);
        context.fireChannelRead(msg);
    }

    /**
     * 判断是否为U帧的长度
     *
     * @param bytes
     * @return
     */
    private boolean isSysInstruction(byte[] bytes) {
        return bytes.length == 6;
    }

    /**
     * 处理U帧
     *
     * @param context
     * @param result
     * @param uControlEnum
     */
    private void processUHandler(ChannelHandlerContext context, ByteBuf result, UControlEnum uControlEnum) {
        result.readBytes(new byte[result.readableBytes()]);
        if (uControlEnum == UControlEnum.TESTFR_YES) {
            logger.info("收到测试确认指令 testYes");
//            MessageDetail messageDetail = new MessageDetail(1);
            context.channel().writeAndFlush(BasicInstruction104.TESTFR_YES);
        } else if (uControlEnum == UControlEnum.STOPDT_YES) {
            logger.info("收到停止确认指令");
        } else if (uControlEnum == UControlEnum.STARTDT_YES) {
            logger.info("收到启动指令确认指令");
            Iec104ThreadLocal.getScheduledTaskPool().stopSendStartFrame();
            MessageDetail messageDetail = new MessageDetail(1);
            context.channel().writeAndFlush(messageDetail);
        }
    }

    private void processUHandler1(ChannelHandlerContext context, ByteBuf result, UControlEnum uControlEnum) {
        result.readBytes(new byte[result.readableBytes()]);
        if (uControlEnum == UControlEnum.TESTFR_YES) {
            logger.info("收到测试确认指令");
        } else if (uControlEnum == UControlEnum.STOPDT_YES) {
            logger.info("收到停止确认指令");
        } else if (uControlEnum == UControlEnum.STARTDT_YES) {
            logger.info("收到启动指令确认指令");
            Iec104ThreadLocal.getScheduledTaskPool().stopSendStartFrame();
            MessageDetail messageDetail = new MessageDetail(1);
            logger.info("发送总召唤:{}", messageDetail.getSendData());
            context.channel().writeAndFlush(messageDetail);
//            context.channel().writeAndFlush(BasicInstruction104.ALL);
        } else if (uControlEnum == UControlEnum.TESTFR) {
            logger.info("发送测试确认指令");
            context.channel().writeAndFlush(BasicInstruction104.TESTFR_YES);
        } else if (uControlEnum == UControlEnum.STARTDT) {
            logger.info("发送总召唤...");
            context.channel().writeAndFlush(BasicInstruction104.ALL);
        }
    }


    public static void main(String[] args) {
        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("我的");
            }
        }, 0L, 3L, TimeUnit.SECONDS);
    }
}
