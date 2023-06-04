package com.ntdq.power_station.domain;

import java.io.Serializable;

/**
 * 点表配置
 */
public class PointConfiguration implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 电表地址
     */
    private Integer address;

    /**
     * 对应字段名
     */
    private String fieldName;


    private String fieldCode;

    /**
     * 设备类型 光伏 储能 充电桩
     */
    private Integer deviceType;

    /**
     * 数据类型 遥信 遥测 遥控 遥调
     */
    private String pointType;

    private Integer deviceSubType;

    private String deviceName;

    public Integer getDeviceSubType() {
        return deviceSubType;
    }

    public void setDeviceSubType(Integer deviceSubType) {
        this.deviceSubType = deviceSubType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }


    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }



    @Override
    public String toString() {
        return "PointConfiguration{" +
                "id=" + id +
                ", address=" + address +
                ", fieldName='" + fieldName + '\'' +
                ", deviceType=" + deviceType +
                ", pointType='" + pointType + '\'' +
                '}';
    }
}
