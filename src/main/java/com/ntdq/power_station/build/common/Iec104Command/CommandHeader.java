package com.ntdq.power_station.build.common.Iec104Command;

import java.nio.ByteBuffer;

/**
 * 104协议通过头
 */
public class CommandHeader {

    /**
     * 启动帧
     */
    private byte startFrame = 0x68;

    /**
     * 长度 除了byteheader为13长度
     */
    private int length;

    /**
     * 0x32 代表50  ASDU类型50
     */
    private byte controlField;

    /**
     * 目标地址 一般在商家网络配置里面配置 1
     */
    private int destinationAddress;

    /**
     * 源地址 2
     */
    private int sourceAddress;

    /**
     * 也为30
     * ASDU 类型（C_SE_NC_1 为 50 比如遥调 50
     */
    private byte typeIdentifier;

    /**
     * 公共地址 一般是0？ RTU？
     */
    private int commonAddress;

    /**
     * 传输原因 06正常 03 突发
     * 本次为06
     */
    private byte causeOfTransmission;

    /**
     * 信息体地址 25089
     */
    private int informationObjectAddress;


    /**
     * 调节功率
     */
    private float power;

    public CommandHeader(byte startFrame, int length, byte controlField, int destinationAddress, int sourceAddress,
                         byte typeIdentifier, int commonAddress, byte causeOfTransmission, int informationObjectAddress
            , float power) {
        this.startFrame = startFrame;
        this.length = length;
        this.controlField = controlField;
        this.destinationAddress = destinationAddress;
        this.sourceAddress = sourceAddress;
        this.typeIdentifier = typeIdentifier;
        this.commonAddress = commonAddress;
        this.causeOfTransmission = causeOfTransmission;
        this.informationObjectAddress = informationObjectAddress;
        this.power = power;
    }

    public byte[] build() {
        ByteBuffer buffer = ByteBuffer.allocate(18);
        byte[] header = new byte[14];
        header[0] = startFrame;
        header[1] = (byte) ((length >> 8) & 0xFF);
        header[2] = (byte) (length & 0xFF);
        header[3] = controlField;
        header[4] = (byte) ((destinationAddress >> 8) & 0xFF);
        header[5] = (byte) (destinationAddress & 0xFF);
        header[6] = (byte) ((sourceAddress >> 8) & 0xFF);
        header[7] = (byte) (sourceAddress & 0xFF);
        header[8] = typeIdentifier;
        header[9] = (byte) ((commonAddress >> 8) & 0xFF);
        header[10] = (byte) (commonAddress & 0xFF);
        header[11] = causeOfTransmission;
        header[12] = (byte) ((informationObjectAddress >> 8) & 0xFF);
        header[13] = (byte) (informationObjectAddress & 0xFF);
        buffer.put(header);
        buffer.putFloat(power);
        return buffer.array();
    }

    public byte getStartFrame() {
        return startFrame;
    }

    public void setStartFrame(byte startFrame) {
        this.startFrame = startFrame;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte getControlField() {
        return controlField;
    }

    public void setControlField(byte controlField) {
        this.controlField = controlField;
    }

    public int getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(int destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public int getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public byte getTypeIdentifier() {
        return typeIdentifier;
    }

    public void setTypeIdentifier(byte typeIdentifier) {
        this.typeIdentifier = typeIdentifier;
    }

    public int getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(int commonAddress) {
        this.commonAddress = commonAddress;
    }

    public byte getCauseOfTransmission() {
        return causeOfTransmission;
    }

    public void setCauseOfTransmission(byte causeOfTransmission) {
        this.causeOfTransmission = causeOfTransmission;
    }

    public int getInformationObjectAddress() {
        return informationObjectAddress;
    }

    public void setInformationObjectAddress(int informationObjectAddress) {
        this.informationObjectAddress = informationObjectAddress;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }
}
