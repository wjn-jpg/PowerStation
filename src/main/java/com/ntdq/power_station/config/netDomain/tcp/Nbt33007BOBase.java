package com.ntdq.power_station.config.netDomain.tcp;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class Nbt33007BOBase extends ServerBaseConfiguration {

    /**
     * 服务端ip
     */
    @Value("${Nbt33007.server.ip}")
    private String serverIp;

    /**
     * 服务端口
     */
    @Value("${Nbt33007.server.port}")
    private Integer serverPort;

    /**
     * 客户端ip
     */
    @Value("${Nbt33007.client.ip}")
    private String clientIp;

    /**
     * 客户端端口
     */
    @Value("${Nbt33007.client.port}")
    private Integer clientPort;


    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Integer getClientPort() {
        return clientPort;
    }

    public void setClientPort(Integer clientPort) {
        this.clientPort = clientPort;
    }
}
