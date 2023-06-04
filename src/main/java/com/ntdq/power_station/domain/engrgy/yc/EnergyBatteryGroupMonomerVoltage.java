package com.ntdq.power_station.domain.engrgy.yc;

/**
 * 电池组单体电压 一个电池组对应一个电压实体类
 */
public class EnergyBatteryGroupMonomerVoltage {


    /**
     * 313个电池电压
     */
    private final Float[] voltageGroupArr = new Float[313];

    /**
     * 设置单个电池电压
     *
     * @param address
     * @param value
     */
    public void setVoltage(int address, float value) {
        voltageGroupArr[address] = value;
    }

    public Float[] getVoltageGroupArr() {
        return voltageGroupArr;
    }
}
