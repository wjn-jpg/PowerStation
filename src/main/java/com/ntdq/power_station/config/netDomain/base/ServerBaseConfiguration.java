package com.ntdq.power_station.config.netDomain.base;

import com.ntdq.power_station.domain.enumDo.DeviceEnum;
import com.ntdq.power_station.exception.NoSuchConfigParseException;
import com.ntdq.power_station.nettyHandler.parseCore.parse.IParse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerBaseConfiguration {

    @Value("${socket.style.message}")
    private String socketStyleMessage;

    private DeviceEnum deviceEnum;

    public String getSocketStyleMessage() {
        return socketStyleMessage;
    }

    public void setSocketStyleMessage(String socketStyleMessage) {
        this.socketStyleMessage = socketStyleMessage;
    }

    public void setDeviceEnum(DeviceEnum deviceEnum) {
        this.deviceEnum = deviceEnum;
    }

    public DeviceEnum getDeviceEnum() {
        return deviceEnum;
    }

    public IParse getParse() {
        throw new NoSuchConfigParseException("没有对应的配置数据解析器具!");
    }


    public boolean isExistSocket(String socketStyleMessage) {
        boolean isExist = false;
        if (socketStyleMessage != null) {
            String[] message = this.socketStyleMessage.split(",");
            for (String s : message) {
                if (s.equalsIgnoreCase(socketStyleMessage)) {
                    isExist = true;
                    return isExist;
                }
            }
        }
        return isExist;
    }
}
