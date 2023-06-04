package com.ntdq.power_station.nettyHandler.tcp.modbus.domain;

import java.util.Arrays;

public class ModBusPayload {

    private short functionCode;
    private short dataLength;//数据字节的长度
    private byte[] data;

    public void setFunctionCode(short code) {
        functionCode = code;
    }

    public short getFunctionCode() {
        return functionCode;
    }

    public short getDataLength() {
        return dataLength;
    }

    public void setDataLength(short dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ModBusPayload{" +
                "functionCode=" + functionCode +
                ", dataLength=" + dataLength +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
