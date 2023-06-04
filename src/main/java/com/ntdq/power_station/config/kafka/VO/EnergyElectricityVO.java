package com.ntdq.power_station.config.kafka.VO;

public class EnergyElectricityVO {

    /**
     * 近7天储能放电量
     */
    private double EnergyInElectricityByWeekVO;

    /**
     * 近7天储能放电量
     */
    private double EnergyOutElectricityByWeekVO;


    public double getEnergyInElectricityByWeekVO() {
        return EnergyInElectricityByWeekVO;
    }

    public void setEnergyInElectricityByWeekVO(double energyInElectricityByWeekVO) {
        EnergyInElectricityByWeekVO = energyInElectricityByWeekVO;
    }

    public double getEnergyOutElectricityByWeekVO() {
        return EnergyOutElectricityByWeekVO;
    }

    public void setEnergyOutElectricityByWeekVO(double energyOutElectricityByWeekVO) {
        EnergyOutElectricityByWeekVO = energyOutElectricityByWeekVO;
    }
}
