package com.ntdq.power_station.domain.engrgy.yc;

public class EnergyBatteryGroup {

    /**
     * 电池组单体电压
     */
    private EnergyBatteryGroupMonomerVoltage energyBatteryGroupMonomerVoltage;

    /**
     * 电池组基本信息
     */
    private EnergyBatteryGroupExtra energyBatteryGroupExtra;

    public EnergyBatteryGroupMonomerVoltage getEnergyBatteryGroupMonomerVoltage() {
        return energyBatteryGroupMonomerVoltage;
    }

    public void setEnergyBatteryGroupMonomerVoltage(EnergyBatteryGroupMonomerVoltage energyBatteryGroupMonomerVoltage) {
        this.energyBatteryGroupMonomerVoltage = energyBatteryGroupMonomerVoltage;
    }

    public EnergyBatteryGroupExtra getEnergyBatteryGroupExtra() {
        return energyBatteryGroupExtra;
    }

    public void setEnergyBatteryGroupExtra(EnergyBatteryGroupExtra energyBatteryGroupExtra) {
        this.energyBatteryGroupExtra = energyBatteryGroupExtra;
    }
}
