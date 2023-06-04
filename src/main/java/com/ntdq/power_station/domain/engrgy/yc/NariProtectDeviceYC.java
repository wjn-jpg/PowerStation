package com.ntdq.power_station.domain.engrgy.yc;

import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

/**
 * 南瑞保护装置设备
 */
@ModelDeviceInfo(mainType = 7, modelType = 3, subType = 12)
public class NariProtectDeviceYC {

    /**
     * 1#开关柜A相电压
     */
    @ModelArguments(code = "APhaseVOfSwitch", mean = "开关柜A相电压", number = 1)
    private float APhaseVOfSwitch;

    /**
     * 1#开关柜B相电压
     */
    @ModelArguments(code = "BPhaseVOfSwitch", mean = "开关柜B相电压", number = 2)
    private float BPhaseVOfSwitch;

    /**
     * 1#开关柜C相电压
     */
    @ModelArguments(code = "CPhaseVOfSwitch", mean = "开关柜C相电压", number = 3)
    private float CPhaseVOfSwitch;

    /**
     * 1#开关柜AB电压
     */
    @ModelArguments(code = "ABVoltageOfSwitch", mean = "开关柜AB电压", number = 4)
    private float ABVoltageOfSwitch;

    /**
     * 1#开关柜BC电压
     */
    @ModelArguments(code = "BCVoltageOfSwitch", mean = "开关柜BC电压", number = 5)
    private float BCVoltageOfSwitch;

    /**
     * 1#开关柜CA电压
     */
    @ModelArguments(code = "CAVoltageOfSwitch", mean = "开关柜CA电压", number = 6)
    private float CAVoltageOfSwitch;

    /**
     * 1#开关柜A相电流
     */
    @ModelArguments(code = "APhaseCOfSwitch", mean = "开关柜A相电流", number = 7)
    private float APhaseCOfSwitch;

    /**
     * 1#开关柜B相电流
     */
    @ModelArguments(code = "BPhaseCOfSwitch", mean = "开关柜B相电流", number = 8)
    private float BPhaseCOfSwitch;

    /**
     * 1#开关柜C相电流
     */
    @ModelArguments(code = "CPhaseCOfSwitch", mean = "开关柜C相电流", number = 9)
    private float CPhaseCOfSwitch;

    /**
     * 1#逆功率一次有功功率
     */
    @ModelArguments(code = "RPowerPrimaryAPower", mean = "逆功率一次有功功率", number = 10)
    private float RPowerPrimaryAPower;

    /**
     * 1#逆功率一次无功功率
     */
    @ModelArguments(code = "RPowerPrimaryRPower", mean = "逆功率一次无功功率", number = 11)
    private float RPowerPrimaryRPower;

    /**
     * 1#电能质量A相电压
     */
    @ModelArguments(code = "PowerQualityAPVoltage", mean = "电能质量A相电压", number = 12)
    private float PowerQualityAPVoltage;

    /**
     * 1#电能质量B相电压
     */
    @ModelArguments(code = "PowerQualityBPVoltage", mean = "电能质量B相电压", number = 13)
    private float PowerQualityBPVoltage;

    /**
     * 1#电能质量C相电压
     */
    @ModelArguments(code = "PowerQualityCPVoltage", mean = "电能质量C相电压", number = 14)
    private float PowerQualityCPVoltage;
    /**
     * 1#电能质量AB电压
     */
    @ModelArguments(code = "PowerQualityABPVoltage", mean = "电能质量AB电压", number = 15)
    private float PowerQualityABPVoltage;

    /**
     * 1#电能质量BC电压
     */
    @ModelArguments(code = "PowerQualityBCPVoltage", mean = "电能质量BC电压", number = 16)
    private float PowerQualityBCPVoltage;

    /**
     * 1#电能质量CA电压
     */
    @ModelArguments(code = "PowerQualityACPVoltage", mean = "电能质量CA电压", number = 17)
    private float PowerQualityACPVoltage;

    /**
     * 1#电能质量A相电流
     */
    @ModelArguments(code = "PowerQualityAPCurrent", mean = "电能质量A相电流", number = 18)
    private float PowerQualityAPCurrent;

    /**
     * 1#电能质量B相电流
     */
    @ModelArguments(code = "PowerQualityBPCurrent", mean = "电能质量B相电流", number = 19)
    private float PowerQualityBPCurrent;

    /**
     * 1#电能质量C相电流
     */
    @ModelArguments(code = "PowerQualityCPCurrent", mean = "电能质量C相电流", number = 20)
    private float PowerQualityCPCurrent;

    /**
     * 1#电能质量一次有功功率
     */
    @ModelArguments(code = "PowerQualityPAPower", mean = "电能质量一次有功功率", number = 21)
    private float PowerQualityPAPower;
    /**
     * 1#电能质量一次无功功率
     */
    @ModelArguments(code = "PowerQualityPRPower", mean = "电能质量一次无功功率", number = 22)
    private float PowerQualityPRPower;

    /**
     * 1#变压器温度
     */
    @ModelArguments(code = "TransformerTemp", mean = "变压器温度", number = 23)
    private float TransformerTemp;

    /**
     * 1#开关柜正向有功电度
     */
    @ModelArguments(code = "PositiveAEleOfSwitch", mean = "开关柜正向有功电度", number = 24)
    private float PositiveAEleOfSwitch;

    /**
     * 1#开关柜反向有功电度
     */
    @ModelArguments(code = "ReverseAEleOfSwitch", mean = "开关柜反向有功电度", number = 25)
    private float ReverseAEleOfSwitch;

    /**
     * 1#开关柜正向无功电度
     */
    @ModelArguments(code = "PositiveREleOfSwitch", mean = "开关柜正向无功电度", number = 26)
    private float PositiveREleOfSwitch;

    /**
     * 1#开关柜反向无功电度
     */
    @ModelArguments(code = "ReverseREleOfSwitch", mean = "开关柜反向无功电度", number = 27)
    private float ReverseREleOfSwitch;

    public float getAPhaseVOfSwitch() {
        return APhaseVOfSwitch;
    }

    public void setAPhaseVOfSwitch(float APhaseVOfSwitch) {
        this.APhaseVOfSwitch = APhaseVOfSwitch;
    }

    public float getBPhaseVOfSwitch() {
        return BPhaseVOfSwitch;
    }

    public void setBPhaseVOfSwitch(float BPhaseVOfSwitch) {
        this.BPhaseVOfSwitch = BPhaseVOfSwitch;
    }

    public float getCPhaseVOfSwitch() {
        return CPhaseVOfSwitch;
    }

    public void setCPhaseVOfSwitch(float CPhaseVOfSwitch) {
        this.CPhaseVOfSwitch = CPhaseVOfSwitch;
    }

    public float getABVoltageOfSwitch() {
        return ABVoltageOfSwitch;
    }

    public void setABVoltageOfSwitch(float ABVoltageOfSwitch) {
        this.ABVoltageOfSwitch = ABVoltageOfSwitch;
    }

    public float getBCVoltageOfSwitch() {
        return BCVoltageOfSwitch;
    }

    public void setBCVoltageOfSwitch(float BCVoltageOfSwitch) {
        this.BCVoltageOfSwitch = BCVoltageOfSwitch;
    }

    public float getCAVoltageOfSwitch() {
        return CAVoltageOfSwitch;
    }

    public void setCAVoltageOfSwitch(float CAVoltageOfSwitch) {
        this.CAVoltageOfSwitch = CAVoltageOfSwitch;
    }

    public float getAPhaseCOfSwitch() {
        return APhaseCOfSwitch;
    }

    public void setAPhaseCOfSwitch(float APhaseCOfSwitch) {
        this.APhaseCOfSwitch = APhaseCOfSwitch;
    }

    public float getBPhaseCOfSwitch() {
        return BPhaseCOfSwitch;
    }

    public void setBPhaseCOfSwitch(float BPhaseCOfSwitch) {
        this.BPhaseCOfSwitch = BPhaseCOfSwitch;
    }

    public float getCPhaseCOfSwitch() {
        return CPhaseCOfSwitch;
    }

    public void setCPhaseCOfSwitch(float CPhaseCOfSwitch) {
        this.CPhaseCOfSwitch = CPhaseCOfSwitch;
    }

    public float getRPowerPrimaryAPower() {
        return RPowerPrimaryAPower;
    }

    public void setRPowerPrimaryAPower(float RPowerPrimaryAPower) {
        this.RPowerPrimaryAPower = RPowerPrimaryAPower;
    }

    public float getRPowerPrimaryRPower() {
        return RPowerPrimaryRPower;
    }

    public void setRPowerPrimaryRPower(float RPowerPrimaryRPower) {
        this.RPowerPrimaryRPower = RPowerPrimaryRPower;
    }

    public float getPowerQualityAPVoltage() {
        return PowerQualityAPVoltage;
    }

    public void setPowerQualityAPVoltage(float powerQualityAPVoltage) {
        PowerQualityAPVoltage = powerQualityAPVoltage;
    }

    public float getPowerQualityBPVoltage() {
        return PowerQualityBPVoltage;
    }

    public void setPowerQualityBPVoltage(float powerQualityBPVoltage) {
        PowerQualityBPVoltage = powerQualityBPVoltage;
    }

    public float getPowerQualityCPVoltage() {
        return PowerQualityCPVoltage;
    }

    public void setPowerQualityCPVoltage(float powerQualityCPVoltage) {
        PowerQualityCPVoltage = powerQualityCPVoltage;
    }

    public float getPowerQualityABPVoltage() {
        return PowerQualityABPVoltage;
    }

    public void setPowerQualityABPVoltage(float powerQualityABPVoltage) {
        PowerQualityABPVoltage = powerQualityABPVoltage;
    }

    public float getPowerQualityBCPVoltage() {
        return PowerQualityBCPVoltage;
    }

    public void setPowerQualityBCPVoltage(float powerQualityBCPVoltage) {
        PowerQualityBCPVoltage = powerQualityBCPVoltage;
    }

    public float getPowerQualityACPVoltage() {
        return PowerQualityACPVoltage;
    }

    public void setPowerQualityACPVoltage(float powerQualityACPVoltage) {
        PowerQualityACPVoltage = powerQualityACPVoltage;
    }

    public float getPowerQualityAPCurrent() {
        return PowerQualityAPCurrent;
    }

    public void setPowerQualityAPCurrent(float powerQualityAPCurrent) {
        PowerQualityAPCurrent = powerQualityAPCurrent;
    }

    public float getPowerQualityBPCurrent() {
        return PowerQualityBPCurrent;
    }

    public void setPowerQualityBPCurrent(float powerQualityBPCurrent) {
        PowerQualityBPCurrent = powerQualityBPCurrent;
    }

    public float getPowerQualityCPCurrent() {
        return PowerQualityCPCurrent;
    }

    public void setPowerQualityCPCurrent(float powerQualityCPCurrent) {
        PowerQualityCPCurrent = powerQualityCPCurrent;
    }

    public float getPowerQualityPAPower() {
        return PowerQualityPAPower;
    }

    public void setPowerQualityPAPower(float powerQualityPAPower) {
        PowerQualityPAPower = powerQualityPAPower;
    }

    public float getPowerQualityPRPower() {
        return PowerQualityPRPower;
    }

    public void setPowerQualityPRPower(float powerQualityPRPower) {
        PowerQualityPRPower = powerQualityPRPower;
    }

    public float getTransformerTemp() {
        return TransformerTemp;
    }

    public void setTransformerTemp(float transformerTemp) {
        TransformerTemp = transformerTemp;
    }

    public float getPositiveAEleOfSwitch() {
        return PositiveAEleOfSwitch;
    }

    public void setPositiveAEleOfSwitch(float positiveAEleOfSwitch) {
        PositiveAEleOfSwitch = positiveAEleOfSwitch;
    }

    public float getReverseAEleOfSwitch() {
        return ReverseAEleOfSwitch;
    }

    public void setReverseAEleOfSwitch(float reverseAEleOfSwitch) {
        ReverseAEleOfSwitch = reverseAEleOfSwitch;
    }

    public float getPositiveREleOfSwitch() {
        return PositiveREleOfSwitch;
    }

    public void setPositiveREleOfSwitch(float positiveREleOfSwitch) {
        PositiveREleOfSwitch = positiveREleOfSwitch;
    }

    public float getReverseREleOfSwitch() {
        return ReverseREleOfSwitch;
    }

    public void setReverseREleOfSwitch(float reverseREleOfSwitch) {
        ReverseREleOfSwitch = reverseREleOfSwitch;
    }
}
