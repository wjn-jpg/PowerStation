package com.ntdq.power_station.remoteControl.vo;

public class RemoteVO {

    /**
     * 是否接受响应
     */
    private int receiveCode;


    /**
     * 是否调控成功
     */
    private int controlCode;


    /**
     * 控制设备名称
     */
    private String controlDeviceName;

    public RemoteVO(int receiveCode, int controlCode, String controlDeviceName) {
        this.receiveCode = receiveCode;
        this.controlCode = controlCode;
        this.controlDeviceName = controlDeviceName;
    }

    public static RemoteVO createResult(String deviceName, boolean success) {
        return new RemoteVO(200, Integer.parseInt(deviceName), success ? "调控成功" : "调控失败");
    }

    public int getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(int receiveCode) {
        this.receiveCode = receiveCode;
    }

    public int getControlCode() {
        return controlCode;
    }

    public void setControlCode(int controlCode) {
        this.controlCode = controlCode;
    }

    public String getControlDeviceName() {
        return controlDeviceName;
    }

    public void setControlDeviceName(String controlDeviceName) {
        this.controlDeviceName = controlDeviceName;
    }
}
