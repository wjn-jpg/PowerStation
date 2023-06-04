package com.ntdq.power_station.config.kafka.VO;

/**
 * 实时负荷
 */
public class RealTimeLoadVO {

    /**
     * 实际用电量
     */
    private int ActualPowerConsumption;

    /**
     * 预测用电量
     */
    private int ForecastPowerConsumption;

    /**
     * 充电负荷
     */
    private int ChargingLoad;

    public int getActualPowerConsumption() {
        return ActualPowerConsumption;
    }

    public void setActualPowerConsumption(int actualPowerConsumption) {
        ActualPowerConsumption = actualPowerConsumption;
    }

    public int getForecastPowerConsumption() {
        return ForecastPowerConsumption;
    }

    public void setForecastPowerConsumption(int forecastPowerConsumption) {
        ForecastPowerConsumption = forecastPowerConsumption;
    }

    public int getChargingLoad() {
        return ChargingLoad;
    }

    public void setChargingLoad(int chargingLoad) {
        ChargingLoad = chargingLoad;
    }
}
