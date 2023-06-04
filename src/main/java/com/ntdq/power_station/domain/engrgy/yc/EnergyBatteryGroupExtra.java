package com.ntdq.power_station.domain.engrgy.yc;

import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

/**
 * 电池组基本信息
 */
@ModelDeviceInfo(mainType = 7, modelType = 3, subType = 8)
public class EnergyBatteryGroupExtra {
    /**
     * BCMU组端电压
     */
    @ModelArguments(code = "BCMUGroupTerminalVoltage", mean = "BCMU组端电压", number = 0)
    private float BCMUGroupTerminalVoltage;

    /**
     * BCMU组端电流
     */
    @ModelArguments(code = "BCMUGroupTerminalCurrent", mean = "BCMU组端电流", number = 1)
    private float BCMUGroupTerminalCurrent;

    /**
     * BCMU环境温度
     */
    @ModelArguments(code = "BCMUAmbientTemperature", mean = "BCMU环境温度", number = 2)
    private float BCMUAmbientTemperature;

    /**
     * BCMU正极绝缘电阻
     */
    @ModelArguments(code = "BCMUPositiveInsulationResistance", mean = "BCMU正极绝缘电阻", number = 3)
    private float BCMUPositiveInsulationResistance;

    /**
     * BCMU负极绝缘电阻
     */
    @ModelArguments(code = "BCMUNegativeInsulationResistance", mean = "BCMU负极绝缘电阻", number = 4)
    private float BCMUNegativeInsulationResistance;

    /**
     * 单体电压平均值
     */
    @ModelArguments(code = "AverageValueOfUnitVoltage", mean = "单体电压平均值", number = 5)
    private float AverageValueOfUnitVoltage;

    /**
     * 单体电压最大值
     */
    @ModelArguments(code = "MaxValueOfUnitVoltage", mean = "单体电压最大值", number = 6)
    private float MaxValueOfUnitVoltage;

    /**
     * 单体电压最小值
     */
    @ModelArguments(code = "MinValueOfUnitVoltage", mean = "单体电压最小值", number = 7)
    private float MinValueOfUnitVoltage;

    /**
     * 单体温度平均值
     */
    @ModelArguments(code = "AverageValueOfMonomerTemperature", mean = "单体温度平均值", number = 8)
    private float AverageValueOfMonomerTemperature;

    /**
     * 单体温度最大值
     */
    @ModelArguments(code = "MaxValueOfMonomerTemperature", mean = "单体温度最大值", number = 9)
    private float MaxValueOfMonomerTemperature;

    /**
     * 单体温度最小值
     */
    @ModelArguments(code = "MinValueOfMonomerTemperature", mean = "单体温度最小值", number = 10)
    private float MinValueOfMonomerTemperature;

    /**
     * 单位平均内阻
     */
    @ModelArguments(code = "AverageInternalResistanceOfMonomer", mean = "单位平均内阻", number = 11)
    private float AverageInternalResistanceOfMonomer;

    /**
     * 单体内阻最大值
     */
    @ModelArguments(code = "MaximumInternalResistanceOfMonomer", mean = "单体内阻最大值", number = 12)
    private float MaximumInternalResistanceOfMonomer;

    /**
     * 单体内阻最小值
     */
    @ModelArguments(code = "MinimumInternalResistanceOfMonomer", mean = "单体内阻最小值", number = 13)
    private float MinimumInternalResistanceOfMonomer;

    /**
     * 单体SOC平均值
     */
    @ModelArguments(code = "AverageValueOfMonomerSOC", mean = "单体SOC平均值", number = 14)
    private float AverageValueOfMonomerSOC;

    /**
     * 单体SOC最大值
     */
    @ModelArguments(code = "MaximumValueOfMonomerSOC", mean = "单体SOC最大值", number = 15)
    private float MaximumValueOfMonomerSOC;

    /**
     * 单体SOC最小值
     */
    @ModelArguments(code = "MinimumValueOfMonomerSOC", mean = "单体SOC最小值", number = 16)
    private float MinimumValueOfMonomerSOC;

    /**
     * 单体SOH平均值
     */
    @ModelArguments(code = "AverageValueOfMonomerSOH", mean = "单体SOH平均值", number = 17)
    private float AverageValueOfMonomerSOH;

    /**
     * 单体SOH最大值
     */
    @ModelArguments(code = "MaximumValueOfMonomerSOH", mean = "单体SOH最大值", number = 18)
    private float MaximumValueOfMonomerSOH;

    /**
     * 单体SOH最小值
     */
    @ModelArguments(code = "MinimumValueOfMonomerSOH", mean = "单体SOH最小值", number = 19)
    private float MinimumValueOfMonomerSOH;

    /**
     * 组SOC
     */
    @ModelArguments(code = "GroupSOC", mean = "组SOC", number = 20)
    private float GroupSOC;

    /**
     * 组SOH
     */
    @ModelArguments(code = "GroupSOH", mean = "组SOH", number = 21)
    private float GroupSOH;

    /**
     * 电池组可充电量
     */
    @ModelArguments(code = "RechargeableCapacityOfBatteryPack", mean = "电池组可充电量", number = 22)
    private float RechargeableCapacityOfBatteryPack;

    /**
     * 电池组可放电量
     */
    @ModelArguments(code = "DischargeCapacityOfBatteryPack", mean = "电池组可放电量", number = 23)
    private float DischargeCapacityOfBatteryPack;

    /**
     * 电池组累计充电电量
     */
    @ModelArguments(code = "AccumulatedChargingCapacityOfBatteryPack", mean = "电池组累计充电电量", number = 24)
    private float AccumulatedChargingCapacityOfBatteryPack;

    /**
     * 电池组累计放电电量
     */
    @ModelArguments(code = "AccumulatedDischargeCapacityOfBatteryPack", mean = "电池组累计放电电量", number = 25)
    private float AccumulatedDischargeCapacityOfBatteryPack;

    /**
     * 电池组单次累计充电电量
     */
    @ModelArguments(code = "AccumulatedChargeOfBatteryPackAtOneTime", mean = "电池组单次累计充电电量", number = 26)
    private float AccumulatedChargeOfBatteryPackAtOneTime;

    /**
     * 电池组单次累计充电电量
     */
    @ModelArguments(code = "AccumulatedSingleDischargeCapacityOfBatteryPack", mean = "电池组单次累计充电电量", number = 27)
    private float AccumulatedSingleDischargeCapacityOfBatteryPack;

    /**
     * 单电最大电压对应ID号
     */
    @ModelArguments(code = "VMaxVoltageId", mean = "单电最大电压对应ID号", number = 28)
    private float VMaxVoltageId;

    /**
     * 单电最小电压对应ID号
     */
    @ModelArguments(code = "VMinVoltageId", mean = "单电最小电压对应ID号", number = 29)
    private float VMinVoltageId;

    /**
     * 单电最大温度对应ID号
     */
    @ModelArguments(code = "VMaxTemperatureId", mean = "单电最大温度对应ID号", number = 30)
    private float VMaxTemperatureId;

    /**
     * 单电最小温度对应ID号
     */
    @ModelArguments(code = "VMinTemperatureId", mean = "单电最小温度对应ID号", number = 31)
    private float VMinTemperatureId;

    /**
     * 单电最大内阻对应ID号
     */
    @ModelArguments(code = "VMaxInternalResistanceId", mean = "单电最大内阻对应ID号", number = 32)
    private float VMaxInternalResistanceId;

    /**
     * 单电最小内阻对应ID号
     */
    @ModelArguments(code = "VMinInternalResistanceId", mean = "单电最小内阻对应ID号", number = 33)
    private float VMinInternalResistanceId;

    /**
     * 单电最大SOC对应ID号
     */
    @ModelArguments(code = "VMaxSOCId", mean = "单电最大SOC对应ID号", number = 34)
    private float VMaxSOCId;

    /**
     * 单电最小SOC对应ID号
     */
    @ModelArguments(code = "VMinSOCId", mean = "单电最小SOC对应ID号", number = 35)
    private float VMinSOCId;

    /**
     * 单电最大SOH对应ID号
     */
    @ModelArguments(code = "VMaxSOHId", mean = "单电最大SOH对应ID号", number = 36)
    private float VMaxSOHId;

    /**
     * 单电最小SOH对应ID号
     */
    @ModelArguments(code = "VMinSOHId", mean = "单电最小SOH对应ID号", number = 37)
    private float VMinSOHId;

    /**
     * 蓄电池组充放电状态
     */
    @ModelArguments(code = "BatteryChargeAndDischargeStatus", mean = "蓄电池组充放电状态", number = 38)
    private float BatteryChargeAndDischargeStatus;

    /**
     * 电池组状态
     */
    @ModelArguments(code = "BatteryPackStatus", mean = "电池组状态", number = 39)
    private float BatteryPackStatus;

    /**
     * BCMU直流接触器状态
     */
    @ModelArguments(code = "BCMUDCContactorStatus", mean = "BCMU直流接触器状态", number = 40)
    private float BCMUDCContactorStatus;

    public float getBCMUGroupTerminalVoltage() {
        return BCMUGroupTerminalVoltage;
    }

    public void setBCMUGroupTerminalVoltage(float BCMUGroupTerminalVoltage) {
        this.BCMUGroupTerminalVoltage = BCMUGroupTerminalVoltage;
    }

    public float getBCMUGroupTerminalCurrent() {
        return BCMUGroupTerminalCurrent;
    }

    public void setBCMUGroupTerminalCurrent(float BCMUGroupTerminalCurrent) {
        this.BCMUGroupTerminalCurrent = BCMUGroupTerminalCurrent;
    }

    public float getBCMUAmbientTemperature() {
        return BCMUAmbientTemperature;
    }

    public void setBCMUAmbientTemperature(float BCMUAmbientTemperature) {
        this.BCMUAmbientTemperature = BCMUAmbientTemperature;
    }

    public float getBCMUPositiveInsulationResistance() {
        return BCMUPositiveInsulationResistance;
    }

    public void setBCMUPositiveInsulationResistance(float BCMUPositiveInsulationResistance) {
        this.BCMUPositiveInsulationResistance = BCMUPositiveInsulationResistance;
    }

    public float getBCMUNegativeInsulationResistance() {
        return BCMUNegativeInsulationResistance;
    }

    public void setBCMUNegativeInsulationResistance(float BCMUNegativeInsulationResistance) {
        this.BCMUNegativeInsulationResistance = BCMUNegativeInsulationResistance;
    }

    public float getAverageValueOfUnitVoltage() {
        return AverageValueOfUnitVoltage;
    }

    public void setAverageValueOfUnitVoltage(float averageValueOfUnitVoltage) {
        AverageValueOfUnitVoltage = averageValueOfUnitVoltage;
    }

    public float getMaxValueOfUnitVoltage() {
        return MaxValueOfUnitVoltage;
    }

    public void setMaxValueOfUnitVoltage(float maxValueOfUnitVoltage) {
        MaxValueOfUnitVoltage = maxValueOfUnitVoltage;
    }

    public float getMinValueOfUnitVoltage() {
        return MinValueOfUnitVoltage;
    }

    public void setMinValueOfUnitVoltage(float minValueOfUnitVoltage) {
        MinValueOfUnitVoltage = minValueOfUnitVoltage;
    }

    public float getAverageValueOfMonomerTemperature() {
        return AverageValueOfMonomerTemperature;
    }

    public void setAverageValueOfMonomerTemperature(float averageValueOfMonomerTemperature) {
        AverageValueOfMonomerTemperature = averageValueOfMonomerTemperature;
    }

    public float getMaxValueOfMonomerTemperature() {
        return MaxValueOfMonomerTemperature;
    }

    public void setMaxValueOfMonomerTemperature(float maxValueOfMonomerTemperature) {
        MaxValueOfMonomerTemperature = maxValueOfMonomerTemperature;
    }

    public float getMinValueOfMonomerTemperature() {
        return MinValueOfMonomerTemperature;
    }

    public void setMinValueOfMonomerTemperature(float minValueOfMonomerTemperature) {
        MinValueOfMonomerTemperature = minValueOfMonomerTemperature;
    }

    public float getAverageInternalResistanceOfMonomer() {
        return AverageInternalResistanceOfMonomer;
    }

    public void setAverageInternalResistanceOfMonomer(float averageInternalResistanceOfMonomer) {
        AverageInternalResistanceOfMonomer = averageInternalResistanceOfMonomer;
    }

    public float getMaximumInternalResistanceOfMonomer() {
        return MaximumInternalResistanceOfMonomer;
    }

    public void setMaximumInternalResistanceOfMonomer(float maximumInternalResistanceOfMonomer) {
        MaximumInternalResistanceOfMonomer = maximumInternalResistanceOfMonomer;
    }

    public float getMinimumInternalResistanceOfMonomer() {
        return MinimumInternalResistanceOfMonomer;
    }

    public void setMinimumInternalResistanceOfMonomer(float minimumInternalResistanceOfMonomer) {
        MinimumInternalResistanceOfMonomer = minimumInternalResistanceOfMonomer;
    }

    public float getAverageValueOfMonomerSOC() {
        return AverageValueOfMonomerSOC;
    }

    public void setAverageValueOfMonomerSOC(float averageValueOfMonomerSOC) {
        AverageValueOfMonomerSOC = averageValueOfMonomerSOC;
    }

    public float getMaximumValueOfMonomerSOC() {
        return MaximumValueOfMonomerSOC;
    }

    public void setMaximumValueOfMonomerSOC(float maximumValueOfMonomerSOC) {
        MaximumValueOfMonomerSOC = maximumValueOfMonomerSOC;
    }

    public float getMinimumValueOfMonomerSOC() {
        return MinimumValueOfMonomerSOC;
    }

    public void setMinimumValueOfMonomerSOC(float minimumValueOfMonomerSOC) {
        MinimumValueOfMonomerSOC = minimumValueOfMonomerSOC;
    }

    public float getAverageValueOfMonomerSOH() {
        return AverageValueOfMonomerSOH;
    }

    public void setAverageValueOfMonomerSOH(float averageValueOfMonomerSOH) {
        AverageValueOfMonomerSOH = averageValueOfMonomerSOH;
    }

    public float getMaximumValueOfMonomerSOH() {
        return MaximumValueOfMonomerSOH;
    }

    public void setMaximumValueOfMonomerSOH(float maximumValueOfMonomerSOH) {
        MaximumValueOfMonomerSOH = maximumValueOfMonomerSOH;
    }

    public float getMinimumValueOfMonomerSOH() {
        return MinimumValueOfMonomerSOH;
    }

    public void setMinimumValueOfMonomerSOH(float minimumValueOfMonomerSOH) {
        MinimumValueOfMonomerSOH = minimumValueOfMonomerSOH;
    }

    public float getGroupSOC() {
        return GroupSOC;
    }

    public void setGroupSOC(float groupSOC) {
        GroupSOC = groupSOC;
    }

    public float getGroupSOH() {
        return GroupSOH;
    }

    public void setGroupSOH(float groupSOH) {
        GroupSOH = groupSOH;
    }

    public float getRechargeableCapacityOfBatteryPack() {
        return RechargeableCapacityOfBatteryPack;
    }

    public void setRechargeableCapacityOfBatteryPack(float rechargeableCapacityOfBatteryPack) {
        RechargeableCapacityOfBatteryPack = rechargeableCapacityOfBatteryPack;
    }

    public float getDischargeCapacityOfBatteryPack() {
        return DischargeCapacityOfBatteryPack;
    }

    public void setDischargeCapacityOfBatteryPack(float dischargeCapacityOfBatteryPack) {
        DischargeCapacityOfBatteryPack = dischargeCapacityOfBatteryPack;
    }

    public float getAccumulatedChargingCapacityOfBatteryPack() {
        return AccumulatedChargingCapacityOfBatteryPack;
    }

    public void setAccumulatedChargingCapacityOfBatteryPack(float accumulatedChargingCapacityOfBatteryPack) {
        AccumulatedChargingCapacityOfBatteryPack = accumulatedChargingCapacityOfBatteryPack;
    }

    public float getAccumulatedDischargeCapacityOfBatteryPack() {
        return AccumulatedDischargeCapacityOfBatteryPack;
    }

    public void setAccumulatedDischargeCapacityOfBatteryPack(float accumulatedDischargeCapacityOfBatteryPack) {
        AccumulatedDischargeCapacityOfBatteryPack = accumulatedDischargeCapacityOfBatteryPack;
    }

    public float getAccumulatedChargeOfBatteryPackAtOneTime() {
        return AccumulatedChargeOfBatteryPackAtOneTime;
    }

    public void setAccumulatedChargeOfBatteryPackAtOneTime(float accumulatedChargeOfBatteryPackAtOneTime) {
        AccumulatedChargeOfBatteryPackAtOneTime = accumulatedChargeOfBatteryPackAtOneTime;
    }

    public float getAccumulatedSingleDischargeCapacityOfBatteryPack() {
        return AccumulatedSingleDischargeCapacityOfBatteryPack;
    }

    public void setAccumulatedSingleDischargeCapacityOfBatteryPack(float accumulatedSingleDischargeCapacityOfBatteryPack) {
        AccumulatedSingleDischargeCapacityOfBatteryPack = accumulatedSingleDischargeCapacityOfBatteryPack;
    }

    public float getVMaxVoltageId() {
        return VMaxVoltageId;
    }

    public void setVMaxVoltageId(float VMaxVoltageId) {
        this.VMaxVoltageId = VMaxVoltageId;
    }

    public float getVMinVoltageId() {
        return VMinVoltageId;
    }

    public void setVMinVoltageId(float VMinVoltageId) {
        this.VMinVoltageId = VMinVoltageId;
    }

    public float getVMaxTemperatureId() {
        return VMaxTemperatureId;
    }

    public void setVMaxTemperatureId(float VMaxTemperatureId) {
        this.VMaxTemperatureId = VMaxTemperatureId;
    }

    public float getVMinTemperatureId() {
        return VMinTemperatureId;
    }

    public void setVMinTemperatureId(float VMinTemperatureId) {
        this.VMinTemperatureId = VMinTemperatureId;
    }

    public float getVMaxInternalResistanceId() {
        return VMaxInternalResistanceId;
    }

    public void setVMaxInternalResistanceId(float VMaxInternalResistanceId) {
        this.VMaxInternalResistanceId = VMaxInternalResistanceId;
    }

    public float getVMinInternalResistanceId() {
        return VMinInternalResistanceId;
    }

    public void setVMinInternalResistanceId(float VMinInternalResistanceId) {
        this.VMinInternalResistanceId = VMinInternalResistanceId;
    }

    public float getVMaxSOCId() {
        return VMaxSOCId;
    }

    public void setVMaxSOCId(float VMaxSOCId) {
        this.VMaxSOCId = VMaxSOCId;
    }

    public float getVMinSOCId() {
        return VMinSOCId;
    }

    public void setVMinSOCId(float VMinSOCId) {
        this.VMinSOCId = VMinSOCId;
    }

    public float getVMaxSOHId() {
        return VMaxSOHId;
    }

    public void setVMaxSOHId(float VMaxSOHId) {
        this.VMaxSOHId = VMaxSOHId;
    }

    public float getVMinSOHId() {
        return VMinSOHId;
    }

    public void setVMinSOHId(float VMinSOHId) {
        this.VMinSOHId = VMinSOHId;
    }

    public float getBatteryChargeAndDischargeStatus() {
        return BatteryChargeAndDischargeStatus;
    }

    public void setBatteryChargeAndDischargeStatus(float batteryChargeAndDischargeStatus) {
        BatteryChargeAndDischargeStatus = batteryChargeAndDischargeStatus;
    }

    public float getBatteryPackStatus() {
        return BatteryPackStatus;
    }

    public void setBatteryPackStatus(float batteryPackStatus) {
        BatteryPackStatus = batteryPackStatus;
    }

    public float getBCMUDCContactorStatus() {
        return BCMUDCContactorStatus;
    }

    public void setBCMUDCContactorStatus(float BCMUDCContactorStatus) {
        this.BCMUDCContactorStatus = BCMUDCContactorStatus;
    }
}
