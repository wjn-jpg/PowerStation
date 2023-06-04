package com.ntdq.power_station.build.service.domain.entity;

import java.io.Serializable;

/**
 * YC Model数据
 */
public class ModelYCAndYX implements Serializable {

    private int modelId;

    private int commonModelId;

    private int devMainType;

    private int devSubType;

    private String mpName;

    private String mpCode;

    private int mpNo;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getCommonModelId() {
        return commonModelId;
    }

    public void setCommonModelId(int commonModelId) {
        this.commonModelId = commonModelId;
    }

    public int getDevMainType() {
        return devMainType;
    }

    public void setDevMainType(int devMainType) {
        this.devMainType = devMainType;
    }

    public String getMpName() {
        return mpName;
    }

    public void setMpName(String mpName) {
        this.mpName = mpName;
    }

    public String getMpCode() {
        return mpCode;
    }

    public void setMpCode(String mpCode) {
        this.mpCode = mpCode;
    }

    public int getMpNo() {
        return mpNo;
    }

    public void setMpNo(int mpNo) {
        this.mpNo = mpNo;
    }

    public int getDevSubType() {
        return devSubType;
    }

    public void setDevSubType(int devSubType) {
        this.devSubType = devSubType;
    }
}
