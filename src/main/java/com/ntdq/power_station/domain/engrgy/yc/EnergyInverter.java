package com.ntdq.power_station.domain.engrgy.yc;

import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

/**
 * @author wang_ji_nian
 * 储能逆变器
 */
@ModelDeviceInfo(mainType = 7, modelType = 3)
public class EnergyInverter {

    /**
     * 电池堆
     */
    private EnergyBatteryStack energyBatteryStack;

    //INFO逆变器信息

    /**
     * 直流进线电压
     */
    @ModelArguments(code = "DCIncomingLineVoltage", mean = "直流进线电压", number = 0)
    private float DCIncomingLineVoltage;

    /**
     * 直流母线电压
     */
    @ModelArguments(code = "DCBusVoltage", mean = "直流母线电压", number = 1)
    private float DCBusVoltage;

    /**
     * 直流电流
     */
    @ModelArguments(code = "DCCurrent", mean = "直流电流", number = 2)
    private float DCCurrent;

    /**
     * 直流功率
     */
    @ModelArguments(code = "DCPower", mean = "直流功率", number = 3)
    private float DCPower;

    /**
     * Uab
     */
    @ModelArguments(code = "Uab", mean = "Uab", number = 4)
    private float Uab;

    /**
     * Ubc
     */
    @ModelArguments(code = "Ubc", mean = "Ubc", number = 5)
    private float Ubc;

    /**
     * Uca
     */
    @ModelArguments(code = "Uca", mean = "Uca", number = 6)
    private float Uca;

    /**
     * Ua
     */
    @ModelArguments(code = "Ua", mean = "Ua", number = 7)
    private float Ua;

    /**
     * Ub
     */
    @ModelArguments(code = "Ub", mean = "Ub", number = 8)
    private float Ub;

    /**
     * Uc
     */
    @ModelArguments(code = "Uc", mean = "Uc", number = 9)
    private float Uc;

    /**
     * Ia
     */
    @ModelArguments(code = "Ia", mean = "Ia", number = 10)
    private float Ia;

    /**
     * Ib
     */
    @ModelArguments(code = "Ib", mean = "Ib", number = 11)
    private float Ib;

    /**
     * Ic
     */
    @ModelArguments(code = "Ic", mean = "Ic", number = 12)
    private float Ic;

    /**
     * 系统频率
     */
    @ModelArguments(code = "SystemFrequency", mean = "系统频率", number = 13)
    private float SystemFrequency;

    /**
     * 交流有功
     */
    @ModelArguments(code = "ActiveCommunication", mean = "交流有功", number = 14)
    private float ActiveCommunication;

    /**
     * 交流无功
     */
    @ModelArguments(code = "ACReactivePower", mean = "交流无功", number = 15)
    private float ACReactivePower;

    /**
     * 功率因数
     */
    @ModelArguments(code = "powerFactor", mean = "功率因数", number = 16)
    private float powerFactor;

    /**
     * 转换效率
     */
    @ModelArguments(code = "conversionEfficiency", mean = "转换效率", number = 17)
    private float conversionEfficiency;

    /**
     * 模组最高温度
     */
    @ModelArguments(code = "MaximumModuleTemperature", mean = "模组最高温度", number = 18)
    private float MaximumModuleTemperature;

    /**
     * A相模组温度
     */
    @ModelArguments(code = "AModuleTemperature", mean = "A相模组温度", number = 19)
    private float AModuleTemperature;

    /**
     * B相模组温度
     */
    @ModelArguments(code = "BModuleTemperature", mean = "B相模组温度", number = 20)
    private float BModuleTemperature;

    /**
     * C相模组温度
     */
    @ModelArguments(code = "CModuleTemperature", mean = "C相模组温度", number = 21)
    private float CModuleTemperature;

    /**
     * A2相模组温度
     */
    @ModelArguments(code = "A2ModuleTemperature", mean = "A2相模组温度", number = 22)
    private float A2ModuleTemperature;

    /**
     * B2相模组温度
     */
    @ModelArguments(code = "B2ModuleTemperature", mean = "B2相模组温度", number = 23)
    private float B2ModuleTemperature;

    /**
     * C2相模组温度
     */
    @ModelArguments(code = "C2ModuleTemperature", mean = "C2相模组温度", number = 24)
    private float C2ModuleTemperature;

    /**
     * 总充电量
     */
    @ModelArguments(code = "TotalCharge", mean = "总充电量", number = 25)
    private float TotalCharge;

    /**
     * 总发电量
     */
    @ModelArguments(code = "TotalGeneration", mean = "总发电量", number = 26)
    private float TotalGeneration;

    /**
     * 日充电量
     */
    @ModelArguments(code = "DailyCharge", mean = "日充电量", number = 27)
    private float DailyCharge;

    /**
     * 日放电量
     */
    @ModelArguments(code = "DailyDischarge", mean = "日放电量", number = 28)
    private float DailyDischarge;

    /**
     * 总充电小时数
     */
    @ModelArguments(code = "TotalChargingHours", mean = "总充电小时数", number = 29)
    private float TotalChargingHours;


    /**
     * 总放电小时数
     */
    @ModelArguments(code = "TotalDischargeHours", mean = "总放电小时数", number = 30)
    private float TotalDischargeHours;

    /**
     * 遥调有功值
     */
    @ModelArguments(code = "YTActiveValue", mean = "遥调有功值", number = 31)
    private float YTActiveValue;

    /**
     * 遥调无功值
     */
    @ModelArguments(code = "YTInActiveValue", mean = "遥调无功值", number = 32)
    private float YTInActiveValue;

    /**
     * 遥调功率因数值
     */
    @ModelArguments(code = "YTPowerFactorValue", mean = "遥调功率因数值", number = 33)
    private float YTPowerFactorValue;

    /**
     * 漏电流
     */
    @ModelArguments(code = "LeakageCurrent", mean = "漏电流", number = 34)
    private float LeakageCurrent;

    /**
     * 逆变器负序电压
     */
    @ModelArguments(code = "InverterNegativeVoltage", mean = "逆变器负序电压", number = 35)
    private float InverterNegativeVoltage;

    /**
     * 逆变器负序电流
     */
    @ModelArguments(code = "InverterNegativeCurrent", mean = "逆变器负序电流", number = 36)
    private float InverterNegativeCurrent;

    public EnergyBatteryStack getEnergyBatteryStack() {
        return energyBatteryStack;
    }

    public void setEnergyBatteryStack(EnergyBatteryStack energyBatteryStack) {
        this.energyBatteryStack = energyBatteryStack;
    }

    public float getDCIncomingLineVoltage() {
        return DCIncomingLineVoltage;
    }

    public void setDCIncomingLineVoltage(float DCIncomingLineVoltage) {
        this.DCIncomingLineVoltage = DCIncomingLineVoltage;
    }

    public float getDCBusVoltage() {
        return DCBusVoltage;
    }

    public void setDCBusVoltage(float DCBusVoltage) {
        this.DCBusVoltage = DCBusVoltage;
    }

    public float getDCCurrent() {
        return DCCurrent;
    }

    public void setDCCurrent(float DCCurrent) {
        this.DCCurrent = DCCurrent;
    }

    public float getDCPower() {
        return DCPower;
    }

    public void setDCPower(float DCPower) {
        this.DCPower = DCPower;
    }

    public float getUab() {
        return Uab;
    }

    public void setUab(float uab) {
        Uab = uab;
    }

    public float getUbc() {
        return Ubc;
    }

    public void setUbc(float ubc) {
        Ubc = ubc;
    }

    public float getUca() {
        return Uca;
    }

    public void setUca(float uca) {
        Uca = uca;
    }

    public float getUa() {
        return Ua;
    }

    public void setUa(float ua) {
        Ua = ua;
    }

    public float getUb() {
        return Ub;
    }

    public void setUb(float ub) {
        Ub = ub;
    }

    public float getUc() {
        return Uc;
    }

    public void setUc(float uc) {
        Uc = uc;
    }

    public float getIa() {
        return Ia;
    }

    public void setIa(float ia) {
        Ia = ia;
    }

    public float getIb() {
        return Ib;
    }

    public void setIb(float ib) {
        Ib = ib;
    }

    public float getIc() {
        return Ic;
    }

    public void setIc(float ic) {
        Ic = ic;
    }

    public float getSystemFrequency() {
        return SystemFrequency;
    }

    public void setSystemFrequency(float systemFrequency) {
        SystemFrequency = systemFrequency;
    }

    public float getActiveCommunication() {
        return ActiveCommunication;
    }

    public void setActiveCommunication(float activeCommunication) {
        ActiveCommunication = activeCommunication;
    }

    public float getACReactivePower() {
        return ACReactivePower;
    }

    public void setACReactivePower(float ACReactivePower) {
        this.ACReactivePower = ACReactivePower;
    }

    public float getPowerFactor() {
        return powerFactor;
    }

    public void setPowerFactor(float powerFactor) {
        this.powerFactor = powerFactor;
    }

    public float getConversionEfficiency() {
        return conversionEfficiency;
    }

    public void setConversionEfficiency(float conversionEfficiency) {
        this.conversionEfficiency = conversionEfficiency;
    }

    public float getMaximumModuleTemperature() {
        return MaximumModuleTemperature;
    }

    public void setMaximumModuleTemperature(float maximumModuleTemperature) {
        MaximumModuleTemperature = maximumModuleTemperature;
    }

    public float getAModuleTemperature() {
        return AModuleTemperature;
    }

    public void setAModuleTemperature(float AModuleTemperature) {
        this.AModuleTemperature = AModuleTemperature;
    }

    public float getBModuleTemperature() {
        return BModuleTemperature;
    }

    public void setBModuleTemperature(float BModuleTemperature) {
        this.BModuleTemperature = BModuleTemperature;
    }

    public float getCModuleTemperature() {
        return CModuleTemperature;
    }

    public void setCModuleTemperature(float CModuleTemperature) {
        this.CModuleTemperature = CModuleTemperature;
    }

    public float getA2ModuleTemperature() {
        return A2ModuleTemperature;
    }

    public void setA2ModuleTemperature(float a2ModuleTemperature) {
        A2ModuleTemperature = a2ModuleTemperature;
    }

    public float getB2ModuleTemperature() {
        return B2ModuleTemperature;
    }

    public void setB2ModuleTemperature(float b2ModuleTemperature) {
        B2ModuleTemperature = b2ModuleTemperature;
    }

    public float getC2ModuleTemperature() {
        return C2ModuleTemperature;
    }

    public void setC2ModuleTemperature(float c2ModuleTemperature) {
        C2ModuleTemperature = c2ModuleTemperature;
    }

    public float getTotalCharge() {
        return TotalCharge;
    }

    public void setTotalCharge(float totalCharge) {
        TotalCharge = totalCharge;
    }

    public float getTotalGeneration() {
        return TotalGeneration;
    }

    public void setTotalGeneration(float totalGeneration) {
        TotalGeneration = totalGeneration;
    }

    public float getDailyCharge() {
        return DailyCharge;
    }

    public void setDailyCharge(float dailyCharge) {
        DailyCharge = dailyCharge;
    }

    public float getDailyDischarge() {
        return DailyDischarge;
    }

    public void setDailyDischarge(float dailyDischarge) {
        DailyDischarge = dailyDischarge;
    }

    public float getTotalChargingHours() {
        return TotalChargingHours;
    }

    public void setTotalChargingHours(float totalChargingHours) {
        TotalChargingHours = totalChargingHours;
    }

    public float getTotalDischargeHours() {
        return TotalDischargeHours;
    }

    public void setTotalDischargeHours(float totalDischargeHours) {
        TotalDischargeHours = totalDischargeHours;
    }

    public float getYTActiveValue() {
        return YTActiveValue;
    }

    public void setYTActiveValue(float YTActiveValue) {
        this.YTActiveValue = YTActiveValue;
    }

    public float getYTInActiveValue() {
        return YTInActiveValue;
    }

    public void setYTInActiveValue(float YTInActiveValue) {
        this.YTInActiveValue = YTInActiveValue;
    }

    public float getYTPowerFactorValue() {
        return YTPowerFactorValue;
    }

    public void setYTPowerFactorValue(float YTPowerFactorValue) {
        this.YTPowerFactorValue = YTPowerFactorValue;
    }

    public float getLeakageCurrent() {
        return LeakageCurrent;
    }

    public void setLeakageCurrent(float leakageCurrent) {
        LeakageCurrent = leakageCurrent;
    }

    public float getInverterNegativeVoltage() {
        return InverterNegativeVoltage;
    }

    public void setInverterNegativeVoltage(float inverterNegativeVoltage) {
        InverterNegativeVoltage = inverterNegativeVoltage;
    }

    public float getInverterNegativeCurrent() {
        return InverterNegativeCurrent;
    }

    public void setInverterNegativeCurrent(float inverterNegativeCurrent) {
        InverterNegativeCurrent = inverterNegativeCurrent;
    }

    public static void main(String[] args) {
        EnergyInverter energyInverter = new EnergyInverter();
        Class<? extends EnergyInverter> energyInverterClass = energyInverter.getClass();
        ModelDeviceInfo annotation = energyInverterClass.getAnnotation(ModelDeviceInfo.class);
        if (annotation != null) {
            System.out.println("主类型:" + annotation.mainType());
            System.out.println("主类型:" + annotation.modelType());
        }
    }
}
