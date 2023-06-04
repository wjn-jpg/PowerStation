package com.ntdq.power_station.nettyHandler.tcp.master104.core;

/**
 * @author wang_ji_nian
 */
public class Iec104ThreadLocal {

    /**
     * 定时发送启动链路指令
     */
    private static ThreadLocal<ScheduledTaskPool> scheduledTaskPoolThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<ControlSendAndRecCountPool> controlSendAndRecCountPoolThreadLocal = new ThreadLocal<>();

    public static void setScheduledTaskPool(ScheduledTaskPool scheduledTaskPool){
        scheduledTaskPoolThreadLocal.set(scheduledTaskPool);
    }

    public static ScheduledTaskPool getScheduledTaskPool(){
        return scheduledTaskPoolThreadLocal.get();
    }

    public static void setControlSendAndRecCountPoolThreadLocal(ControlSendAndRecCountPool controlSendAndRecCountPool){
        controlSendAndRecCountPoolThreadLocal.set(controlSendAndRecCountPool);
    }

    public static ControlSendAndRecCountPool getControlSendAndRecCountPool(){
        return controlSendAndRecCountPoolThreadLocal.get();
    }
}
