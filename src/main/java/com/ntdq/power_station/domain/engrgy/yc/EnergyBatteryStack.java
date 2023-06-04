package com.ntdq.power_station.domain.engrgy.yc;

import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

@ModelDeviceInfo(mainType = 7, modelType = 3, subType = 10)
public class EnergyBatteryStack {


    /**
     * 1-1# 电池组
     */
    private EnergyBatteryGroup energyBatteryGroup1;

    /**
     * 1-2# 电池组
     */
    private EnergyBatteryGroup energyBatteryGroup2;

    /**
     * 1-3# 电池组
     */
    private EnergyBatteryGroup energyBatteryGroup3;


    /**
     * 电池堆基本信息
     */
    private EnergyBatteryStackExtra energyBatteryStackExtra;

    public EnergyBatteryGroup getEnergyBatteryGroup1() {
        return energyBatteryGroup1;
    }

    public void setEnergyBatteryGroup1(EnergyBatteryGroup energyBatteryGroup1) {
        this.energyBatteryGroup1 = energyBatteryGroup1;
    }

    public EnergyBatteryGroup getEnergyBatteryGroup2() {
        return energyBatteryGroup2;
    }

    public void setEnergyBatteryGroup2(EnergyBatteryGroup energyBatteryGroup2) {
        this.energyBatteryGroup2 = energyBatteryGroup2;
    }

    public EnergyBatteryGroup getEnergyBatteryGroup3() {
        return energyBatteryGroup3;
    }

    public void setEnergyBatteryGroup3(EnergyBatteryGroup energyBatteryGroup3) {
        this.energyBatteryGroup3 = energyBatteryGroup3;
    }

    public EnergyBatteryStackExtra getEnergyBatteryStackExtra() {
        return energyBatteryStackExtra;
    }

    public void setEnergyBatteryStackExtra(EnergyBatteryStackExtra energyBatteryStackExtra) {
        this.energyBatteryStackExtra = energyBatteryStackExtra;
    }
}
