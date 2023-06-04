package com.ntdq.power_station.domain.powerStation;

import java.util.Date;


public abstract class ByteMessage {

    /**
     * 原生字节数据
     */
    private final byte[] byteData;

    /**
     * 发送方ID
     */
    private Object channelRec;

    /**
     * 消息记录时间
     */
    private final Date messageRecordTime;


    public ByteMessage(byte[] byteData){
     this.byteData = byteData;
     messageRecordTime = new Date();
     parseByteData();
    }

    /**
     * 交由子类去实现
     */
    protected abstract void parseByteData();

    public byte[] getByteData() {
        return byteData;
    }

    public Object getChannelRec() {
        return channelRec;
    }

    public Date getMessageRecordTime() {
        return messageRecordTime;
    }
}
