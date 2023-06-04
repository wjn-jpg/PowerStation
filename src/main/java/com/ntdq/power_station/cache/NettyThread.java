package com.ntdq.power_station.cache;

import com.ntdq.power_station.domain.enumDo.DeviceEnum;

public class NettyThread extends Thread {

    /**
     * 线程标识
     */
    private final DeviceEnum deviceEnum;


    public NettyThread(DeviceEnum deviceEnum, Runnable runnable) {
        super(runnable);
        this.deviceEnum = deviceEnum;
        setName(deviceEnum.name());
    }

    @Override
    public synchronized void start() {
        NettyContext.addThreadMapByNetty(deviceEnum.name(), this);
        super.start();
    }


}
