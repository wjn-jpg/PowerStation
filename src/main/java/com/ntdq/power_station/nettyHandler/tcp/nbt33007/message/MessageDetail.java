package com.ntdq.power_station.nettyHandler.tcp.nbt33007.message;


/**
 * @Description : 一条报文对应的消息体
 * @Author : Kang
 * @Date : 2023/4/19 10:45
 * @Version : 1.0
 */
public class MessageDetail {

    /**
     * 启动字符 固定 1字节
     */
    private byte start = 0x68;

    /**
     * 报文从此长度本身以后的有效长度 2字节
     */
    //private int effectiveLength;
    private byte[] effectiveLength;

    /**
     * 控制域 4字节
     */
    private byte[] controlRegion;

    /**
     * 目标地址 4字节
     */
    private byte[] targetAddress;

    /**
     * 源地址 4字节
     */
    private byte[] sourceAddress;

    /**
     * 帧类型号 1字节
     */
    private byte frameType;

    /**
     * 信息体个数 1字节
     */
    //private int infoBodyCount;
    private byte infoBodyCount;

    /**
     * 传送原因 2字节
     */
    private byte[] sendReason;

    /**
     * 公共地址 2字节
     */
    private byte[] publicAddress;

    /**
     * 信息体地址 3字节
     */
    private byte[] infoBodyAddress;

    /**
     * 信息体 若干字节
     */
    private byte[] infoBody;

    public byte getStart() {
        return start;
    }

    public void setStart(byte start) {
        this.start = start;
    }

    public byte[] getEffectiveLength() {
        return effectiveLength;
    }

    public void setEffectiveLength(byte[] effectiveLength) {
        this.effectiveLength = effectiveLength;
    }

    public byte[] getControlRegion() {
        return controlRegion;
    }

    public void setControlRegion(byte[] controlRegion) {
        this.controlRegion = controlRegion;
    }

    public byte[] getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(byte[] targetAddress) {
        this.targetAddress = targetAddress;
    }

    public byte[] getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(byte[] sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public byte getFrameType() {
        return frameType;
    }

    public void setFrameType(byte frameType) {
        this.frameType = frameType;
    }

    public byte getInfoBodyCount() {
        return infoBodyCount;
    }

    public void setInfoBodyCount(byte infoBodyCount) {
        this.infoBodyCount = infoBodyCount;
    }

    public byte[] getSendReason() {
        return sendReason;
    }

    public void setSendReason(byte[] sendReason) {
        this.sendReason = sendReason;
    }

    public byte[] getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(byte[] publicAddress) {
        this.publicAddress = publicAddress;
    }

    public byte[] getInfoBodyAddress() {
        return infoBodyAddress;
    }

    public void setInfoBodyAddress(byte[] infoBodyAddress) {
        this.infoBodyAddress = infoBodyAddress;
    }

    public byte[] getInfoBody() {
        return infoBody;
    }

    public void setInfoBody(byte[] infoBody) {
        this.infoBody = infoBody;
    }
}
