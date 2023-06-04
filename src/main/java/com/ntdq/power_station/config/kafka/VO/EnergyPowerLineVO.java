package com.ntdq.power_station.config.kafka.VO;

/**
 * 储能功率曲线
 */
public class EnergyPowerLineVO {

    /**
     * 实时充电功率
     */
    private double realInPower;

    /**
     * 实时放电功率
     */
    private double realOutPower;

    public double getRealInPower() {
        return realInPower;
    }

    public void setRealInPower(double realInPower) {
        this.realInPower = realInPower;
    }

    public double getRealOutPower() {
        return realOutPower;
    }

    public void setRealOutPower(double realOutPower) {
        this.realOutPower = realOutPower;
    }
}
