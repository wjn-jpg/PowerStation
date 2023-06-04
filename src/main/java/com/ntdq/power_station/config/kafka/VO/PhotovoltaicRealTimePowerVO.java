package com.ntdq.power_station.config.kafka.VO;

/**
 * 光伏实时功率
 */
public class PhotovoltaicRealTimePowerVO {


    /**
     * 光伏实际功率
     */
    private int PRealPower;


    /**
     * 光伏预测功率
     */
    private int PForecastPower;

    public int getPRealPower() {
        return PRealPower;
    }

    public void setPRealPower(int PRealPower) {
        this.PRealPower = PRealPower;
    }

    public int getPForecastPower() {
        return PForecastPower;
    }

    public void setPForecastPower(int PForecastPower) {
        this.PForecastPower = PForecastPower;
    }
}
