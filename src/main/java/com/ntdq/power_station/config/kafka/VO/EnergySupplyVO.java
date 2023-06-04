package com.ntdq.power_station.config.kafka.VO;

/**
 * 能源供给
 */
public class EnergySupplyVO {

    /**
     * 光伏供给比
     */
    private double PhotovoltaicSupplyPer;

    /**
     * v2g供给比
     */
    private double V2gSupplyPer;

    /**
     * 储能供给比
     */
    private double EnergySupplyPer;

    /**
     * 市电供给比
     */
    private double ElectricSupplyPer;

    public double getPhotovoltaicSupplyPer() {
        return PhotovoltaicSupplyPer;
    }

    public void setPhotovoltaicSupplyPer(double photovoltaicSupplyPer) {
        PhotovoltaicSupplyPer = photovoltaicSupplyPer;
    }

    public double getV2gSupplyPer() {
        return V2gSupplyPer;
    }

    public void setV2gSupplyPer(double v2gSupplyPer) {
        V2gSupplyPer = v2gSupplyPer;
    }

    public double getEnergySupplyPer() {
        return EnergySupplyPer;
    }

    public void setEnergySupplyPer(double energySupplyPer) {
        EnergySupplyPer = energySupplyPer;
    }

    public double getElectricSupplyPer() {
        return ElectricSupplyPer;
    }

    public void setElectricSupplyPer(double electricSupplyPer) {
        ElectricSupplyPer = electricSupplyPer;
    }
}
