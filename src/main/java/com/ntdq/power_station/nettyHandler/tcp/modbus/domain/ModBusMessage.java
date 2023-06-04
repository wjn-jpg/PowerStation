package com.ntdq.power_station.nettyHandler.tcp.modbus.domain;

public class ModBusMessage {

    private ModBusHeader modBusHeader;

    private ModBusPayload modBusPayload;


    public ModBusHeader getModBusHeader() {
        return modBusHeader;
    }

    public void setModBusHeader(ModBusHeader modBusHeader) {
        this.modBusHeader = modBusHeader;
    }

    public ModBusPayload getModBusPayload() {
        return modBusPayload;
    }

    public void setModBusPayload(ModBusPayload modBusPayload) {
        this.modBusPayload = modBusPayload;
    }

    @Override
    public String toString() {
        return "ModBusMessage{" +
                "modBusHeader=" + modBusHeader +
                ", modBusPayload=" + modBusPayload +
                '}';
    }
}
