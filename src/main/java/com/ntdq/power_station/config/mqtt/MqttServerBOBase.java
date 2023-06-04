package com.ntdq.power_station.config.mqtt;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqttServerBOBase extends ServerBaseConfiguration {

    private long currentProcessThread;

    @Value("${mqtt.server.addr}")
    private String mqttAddress;
    @Value("${mqtt.server.port}")
    private int port;

    @Value("${mqtt.server.username}")
    private String username;

    @Value("${mqtt.server.password}")
    private String password;

    @Value("${mqtt.server.isNeedVerify}")
    private boolean isNeedVerify;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNeedVerify() {
        return isNeedVerify;
    }

    public void setNeedVerify(boolean needVerify) {
        isNeedVerify = needVerify;
    }

    public String getMqttAddress() {
        return mqttAddress;
    }

    public void setMqttAddress(String mqttAddress) {
        this.mqttAddress = mqttAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getCurrentProcessThread() {
        return currentProcessThread;
    }

    public void setCurrentProcessThread(long currentProcessThread) {
        this.currentProcessThread = currentProcessThread;
    }
}
