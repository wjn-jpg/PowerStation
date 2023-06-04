package com.ntdq.power_station.nettyHandler.tcp.modbus.parm;

public class ModBusParam {

    /**
     * 设备名称
     */
    private String ip;

    /**
     * 线圈起始地址
     */
    private String address;

    /**
     * 线圈数量
     */
    private int quantity;

    /**
     * 写用得到的值
     */
    private int value;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
