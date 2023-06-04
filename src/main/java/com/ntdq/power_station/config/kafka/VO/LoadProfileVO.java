package com.ntdq.power_station.config.kafka.VO;

/**
 * 负荷曲线
 */
public class LoadProfileVO {

    /**
     * 当日用能情况
     */
    private int EnergyConsumptionDay;


    /**
     * 当日功能情况
     */
    private int EnergySupplyDay;

    public int getEnergyConsumptionDay() {
        return EnergyConsumptionDay;
    }

    public void setEnergyConsumptionDay(int energyConsumptionDay) {
        EnergyConsumptionDay = energyConsumptionDay;
    }

    public int getEnergySupplyDay() {
        return EnergySupplyDay;
    }

    public void setEnergySupplyDay(int energySupplyDay) {
        EnergySupplyDay = energySupplyDay;
    }
}
