package com.ntdq.power_station.nettyHandler.parseCore;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;

public class ConfigHolder {

    private ServerBaseConfiguration configuration;

    private byte[] bytes;


    public ConfigHolder(ServerBaseConfiguration configuration, byte[] bytes) {
        this.configuration = configuration;
        this.bytes = bytes;
    }

    public ServerBaseConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ServerBaseConfiguration configuration) {
        this.configuration = configuration;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
