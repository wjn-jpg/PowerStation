package com.ntdq.power_station.holder.mqtt;

import io.netty.handler.codec.mqtt.MqttQoS;

/**
 * mqtt请求消息体
 */
public class MqttRequest {

    /**
     * 易变
     */
    private boolean mutable = true;

    /**
     * 消息具体内容
     */
    private byte[] payload;

    /**
     * 最多一次的确认消息
     * 客户端返回
     */
    private MqttQoS qos = MqttQoS.AT_MOST_ONCE;//AT_LEAST

    /**
     * 是否保留
     */
    private boolean retained = false;

    /**
     * 是否重复
     */
    private boolean dup = false;
    private int messageId;

    public MqttRequest() {
        this.setPayload(new byte[0]);
    }

    public MqttRequest(byte[] payload) {
        this.setPayload(payload);
    }
    public MqttRequest(byte[] payload,MqttQoS qos) {
        this.setPayload(payload);
        this.setQos(qos);
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public void clearPayload() {
        this.checkMutable();
        this.payload = new byte[0];
    }

    public void setPayload(byte[] payload) {
        this.checkMutable();
        if (payload == null) {
            throw new NullPointerException();
        } else {
            this.payload = payload;
        }
    }

    public boolean isRetained() {
        return this.retained;
    }

    public void setRetained(boolean retained) {
        this.checkMutable();
        this.retained = retained;
    }

    public MqttQoS getQos() {
        return qos;
    }

    public void setQos(MqttQoS qos) {
        this.qos = qos;
    }

    public boolean isMutable() {
        return mutable;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }

    protected void checkMutable() throws IllegalStateException {
        if (!this.mutable) {
            throw new IllegalStateException();
        }
    }

    public boolean isDup() {
        return dup;
    }

    public void setDup(boolean dup) {
        this.dup = dup;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return new String(this.payload);
    }



}
