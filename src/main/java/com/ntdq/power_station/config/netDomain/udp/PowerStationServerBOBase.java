package com.ntdq.power_station.config.netDomain.udp;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PowerStationServerBOBase extends ServerBaseConfiguration {

    @Value("${power.addr}")
    private String powerStationAddr;


    @Value("${power.port}")
    private int ServerPort;


    private long currentThreadId;

    public String getPowerStationAddr() {
        return powerStationAddr;
    }

    public void setPowerStationAddr(String powerStationAddr) {
        this.powerStationAddr = powerStationAddr;
    }

    public int getServerPort() {
        return ServerPort;
    }

    public void setServerPort(int serverPort) {
        ServerPort = serverPort;
    }

    public long getCurrentThreadId() {
        return currentThreadId;
    }

    public void setCurrentThreadId(long currentThreadId) {
        this.currentThreadId = currentThreadId;
    }
}
