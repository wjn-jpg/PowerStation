package com.ntdq.power_station.config.netDomain.tcp;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ModbusServerBOBase extends ServerBaseConfiguration {

    @Value("${modbus.addr}")
    private String modbusIp;


    @Value("${modbus.port}")
    private int modbusPort;


    private Long CurrentThreadId;

    public String getModbusIp() {
        return modbusIp;
    }

    public void setModbusIp(String modbusIp) {
        this.modbusIp = modbusIp;
    }

    public int getModbusPort() {
        return modbusPort;
    }

    public void setModbusPort(int modbusPort) {
        this.modbusPort = modbusPort;
    }

    public Long getCurrentThreadId() {
        return CurrentThreadId;
    }

    public void setCurrentThreadId(Long currentThreadId) {
        CurrentThreadId = currentThreadId;
    }
}
