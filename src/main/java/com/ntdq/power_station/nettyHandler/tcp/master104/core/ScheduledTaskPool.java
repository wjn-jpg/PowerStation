package com.ntdq.power_station.nettyHandler.tcp.master104.core;

import com.ntdq.power_station.nettyHandler.tcp.master104.constant.BasicInstruction104;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wang_ji_nian
 * 管理104其他发送命令线程
 * 1 启动链路线程
 * 2 发送S帧线程
 */
public class ScheduledTaskPool {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskPool.class);

    /**
     * 通过context对象给104服务端发送命令
     */
    private ChannelHandlerContext context;

    /**
     * 发送启动链路的线程
     */
    private Thread sendStartThread;

    /**
     * 发送启动链路线程状态
     */
    private Boolean sendStartStatus = false;

    /**
     * 启动指令收到确认后固定时间内发送总召唤指令
     */
    private Thread generalCallThread;


    /**
     * 为context初值
     * @param context
     */
    public ScheduledTaskPool(ChannelHandlerContext context){
        this.context = context;
    }

    /**
     * 发送启动帧
     */
    public void sendStartFrame(){
        synchronized (sendStartStatus){
            if (sendStartThread!=null){
                sendStartStatus = true;
                sendStartThread.start();
            }else if (sendStartThread==null){
                sendStartStatus = true;
                sendStartThread = new Thread(()->{
                    while (sendStartStatus){
                        if (context!=null){
                            context.channel().writeAndFlush(BasicInstruction104.STARTDT);
                            logger.info("发送启动链路指令:{}",BasicInstruction104.STARTDT);
                            sendStartStatus = false;
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                sendStartThread.start();
            }
        }
    }

    /**
     * 停止发送确认帧
     */
    public void stopSendStartFrame(){
        if (sendStartThread!=null){
            sendStartStatus = false;
        }
    }



}
