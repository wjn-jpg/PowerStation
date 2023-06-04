package com.ntdq.power_station.domain.engrgy.yc;

import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

/**
 * 电池堆基本信息
 */
@ModelDeviceInfo(mainType = 7, modelType = 3, subType = 10)
public class EnergyBatteryStackExtra {

    //电池堆基本信息
    /**
     * 电池堆状态
     */
    @ModelArguments(code = "BatteryStackStatus", mean = "电池堆状态", number = 0)
    private float BatteryStackStatus;

    /**
     * 电池堆SOC
     */
    @ModelArguments(code = "BatteryStackSOC", mean = "电池堆SOC", number = 1)
    private float BatteryStackSOC;

    /**
     * 电池堆SOH
     */
    @ModelArguments(code = "BatteryStackSOH", mean = "电池堆SOH", number = 2)
    private float BatteryStackSOH;

    /**
     * 电池堆可充电量
     */
    @ModelArguments(code = "RechargeableCapacityOfBatteryStack", mean = "电池堆可充电量", number = 3)
    private float RechargeableCapacityOfBatteryStack;

    /**
     * 电池堆可放电量
     */
    @ModelArguments(code = "DischargeCapacityOfBatteryStack", mean = "电池堆可放电量", number = 4)
    private float DischargeCapacityOfBatteryStack;

    /**
     * 电池堆充电量累加值
     */
    @ModelArguments(code = "AccumulatedValueOfBatteryStackIn", mean = "电池堆充电量累加值", number = 5)
    private float AccumulatedValueOfBatteryStackIn;

    /**
     * 电池堆放电量累加值
     */
    @ModelArguments(code = "AccumulatedValueOfBatteryStackOut", mean = "电池堆放电量累加值", number = 6)
    private float AccumulatedValueOfBatteryStackOut;

    /**
     * 电池堆总电压
     */
    @ModelArguments(code = "TotalStackVoltage", mean = "电池堆总电压", number = 7)
    private float TotalStackVoltage;

    /**
     * 电池堆总电流
     */
    @ModelArguments(code = "TotalCurrentOfBatteryStack", mean = "电池堆总电流", number = 8)
    private float TotalCurrentOfBatteryStack;

    public float getBatteryStackStatus() {
        return BatteryStackStatus;
    }

    public void setBatteryStackStatus(float batteryStackStatus) {
        BatteryStackStatus = batteryStackStatus;
    }

    public float getBatteryStackSOC() {
        return BatteryStackSOC;
    }

    public void setBatteryStackSOC(float batteryStackSOC) {
        BatteryStackSOC = batteryStackSOC;
    }

    public float getBatteryStackSOH() {
        return BatteryStackSOH;
    }

    public void setBatteryStackSOH(float batteryStackSOH) {
        BatteryStackSOH = batteryStackSOH;
    }

    public float getRechargeableCapacityOfBatteryStack() {
        return RechargeableCapacityOfBatteryStack;
    }

    public void setRechargeableCapacityOfBatteryStack(float rechargeableCapacityOfBatteryStack) {
        RechargeableCapacityOfBatteryStack = rechargeableCapacityOfBatteryStack;
    }

    public float getDischargeCapacityOfBatteryStack() {
        return DischargeCapacityOfBatteryStack;
    }

    public void setDischargeCapacityOfBatteryStack(float dischargeCapacityOfBatteryStack) {
        DischargeCapacityOfBatteryStack = dischargeCapacityOfBatteryStack;
    }

    public float getAccumulatedValueOfBatteryStackIn() {
        return AccumulatedValueOfBatteryStackIn;
    }

    public void setAccumulatedValueOfBatteryStackIn(float accumulatedValueOfBatteryStackIn) {
        AccumulatedValueOfBatteryStackIn = accumulatedValueOfBatteryStackIn;
    }

    public float getAccumulatedValueOfBatteryStackOut() {
        return AccumulatedValueOfBatteryStackOut;
    }

    public void setAccumulatedValueOfBatteryStackOut(float accumulatedValueOfBatteryStackOut) {
        AccumulatedValueOfBatteryStackOut = accumulatedValueOfBatteryStackOut;
    }

    public float getTotalStackVoltage() {
        return TotalStackVoltage;
    }

    public void setTotalStackVoltage(float totalStackVoltage) {
        TotalStackVoltage = totalStackVoltage;
    }

    public float getTotalCurrentOfBatteryStack() {
        return TotalCurrentOfBatteryStack;
    }

    public void setTotalCurrentOfBatteryStack(float totalCurrentOfBatteryStack) {
        TotalCurrentOfBatteryStack = totalCurrentOfBatteryStack;
    }
}
