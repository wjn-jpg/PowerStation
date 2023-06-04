package com.ntdq.power_station.config.kafka.VO;

/**
 * 实时运行
 */
public class RealTimeOperationVO {

    /**
     * 储能出力
     */
    private int EnergyPowerOut;

    /**
     * 储能充电
     */
    private int EnergyPowerIn;

    /**
     * 光伏出力
     */
    private int PhotovoltaicOut;

    /**
     * 充电桩负荷
     */
    private int ChargePileLoad;

    /**
     * 照明负荷
     */
    private int LightingLoad;

    /**
     * 空调负荷
     */
    private int airConditionLoad;

    public int getEnergyPowerOut() {
        return EnergyPowerOut;
    }

    public void setEnergyPowerOut(int energyPowerOut) {
        EnergyPowerOut = energyPowerOut;
    }

    public int getEnergyPowerIn() {
        return EnergyPowerIn;
    }

    public void setEnergyPowerIn(int energyPowerIn) {
        EnergyPowerIn = energyPowerIn;
    }

    public int getPhotovoltaicOut() {
        return PhotovoltaicOut;
    }

    public void setPhotovoltaicOut(int photovoltaicOut) {
        PhotovoltaicOut = photovoltaicOut;
    }

    public int getChargePileLoad() {
        return ChargePileLoad;
    }

    public void setChargePileLoad(int chargePileLoad) {
        ChargePileLoad = chargePileLoad;
    }

    public int getLightingLoad() {
        return LightingLoad;
    }

    public void setLightingLoad(int lightingLoad) {
        LightingLoad = lightingLoad;
    }

    public int getAirConditionLoad() {
        return airConditionLoad;
    }

    public void setAirConditionLoad(int airConditionLoad) {
        this.airConditionLoad = airConditionLoad;
    }
}
