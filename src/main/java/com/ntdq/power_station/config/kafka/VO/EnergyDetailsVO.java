package com.ntdq.power_station.config.kafka.VO;

/**
 * 储能具体情况
 */
public class EnergyDetailsVO {

    /**
     * 1储能变流器状态
     */
    private int EnergyCurrentTransformerStatus1;

    /**
     * 1储能变流器当前负荷
     */
    private int EnergyCurrentTransformerLoad1;

    /**
     * 1储能变流器当前出力
     */
    private int EnergyCurrentTransformerOut1;

    /**
     * 1储能变流器当前转化效率
     */
    private double EnergyCurrentTransformerEfficiency1;

    /**
     * 2储能变流器状态
     */
    private int EnergyCurrentTransformerStatus2;

    /**
     * 2储能变流器当前负荷
     */
    private int EnergyCurrentTransformerLoad2;

    /**
     * 2储能变流器当前出力
     */
    private int EnergyCurrentTransformerOut2;

    /**
     * 2储能变流器当前转化效率
     */
    private double EnergyCurrentTransformerEfficiency2;

    /**
     * 储能电池组1 总容量
     */
    private double EnergyPowerGroupCapacity1;

    /**
     * 储能电池组1 SOC
     */
    private double EnergyPowerGroupSOC1;

    /**
     * 储能电池组2 总容量
     */
    private double EnergyPowerGroupCapacity2;

    /**
     * 储能电池组2 SOC
     */
    private double EnergyPowerGroupSOC2;

    public int getEnergyCurrentTransformerStatus1() {
        return EnergyCurrentTransformerStatus1;
    }

    public void setEnergyCurrentTransformerStatus1(int energyCurrentTransformerStatus1) {
        EnergyCurrentTransformerStatus1 = energyCurrentTransformerStatus1;
    }

    public int getEnergyCurrentTransformerLoad1() {
        return EnergyCurrentTransformerLoad1;
    }

    public void setEnergyCurrentTransformerLoad1(int energyCurrentTransformerLoad1) {
        EnergyCurrentTransformerLoad1 = energyCurrentTransformerLoad1;
    }

    public int getEnergyCurrentTransformerOut1() {
        return EnergyCurrentTransformerOut1;
    }

    public void setEnergyCurrentTransformerOut1(int energyCurrentTransformerOut1) {
        EnergyCurrentTransformerOut1 = energyCurrentTransformerOut1;
    }

    public double getEnergyCurrentTransformerEfficiency1() {
        return EnergyCurrentTransformerEfficiency1;
    }

    public void setEnergyCurrentTransformerEfficiency1(double energyCurrentTransformerEfficiency1) {
        EnergyCurrentTransformerEfficiency1 = energyCurrentTransformerEfficiency1;
    }

    public int getEnergyCurrentTransformerStatus2() {
        return EnergyCurrentTransformerStatus2;
    }

    public void setEnergyCurrentTransformerStatus2(int energyCurrentTransformerStatus2) {
        EnergyCurrentTransformerStatus2 = energyCurrentTransformerStatus2;
    }

    public int getEnergyCurrentTransformerLoad2() {
        return EnergyCurrentTransformerLoad2;
    }

    public void setEnergyCurrentTransformerLoad2(int energyCurrentTransformerLoad2) {
        EnergyCurrentTransformerLoad2 = energyCurrentTransformerLoad2;
    }

    public int getEnergyCurrentTransformerOut2() {
        return EnergyCurrentTransformerOut2;
    }

    public void setEnergyCurrentTransformerOut2(int energyCurrentTransformerOut2) {
        EnergyCurrentTransformerOut2 = energyCurrentTransformerOut2;
    }

    public double getEnergyCurrentTransformerEfficiency2() {
        return EnergyCurrentTransformerEfficiency2;
    }

    public void setEnergyCurrentTransformerEfficiency2(double energyCurrentTransformerEfficiency2) {
        EnergyCurrentTransformerEfficiency2 = energyCurrentTransformerEfficiency2;
    }

    public double getEnergyPowerGroupCapacity1() {
        return EnergyPowerGroupCapacity1;
    }

    public void setEnergyPowerGroupCapacity1(double energyPowerGroupCapacity1) {
        EnergyPowerGroupCapacity1 = energyPowerGroupCapacity1;
    }

    public double getEnergyPowerGroupSOC1() {
        return EnergyPowerGroupSOC1;
    }

    public void setEnergyPowerGroupSOC1(double energyPowerGroupSOC1) {
        EnergyPowerGroupSOC1 = energyPowerGroupSOC1;
    }

    public double getEnergyPowerGroupCapacity2() {
        return EnergyPowerGroupCapacity2;
    }

    public void setEnergyPowerGroupCapacity2(double energyPowerGroupCapacity2) {
        EnergyPowerGroupCapacity2 = energyPowerGroupCapacity2;
    }

    public double getEnergyPowerGroupSOC2() {
        return EnergyPowerGroupSOC2;
    }

    public void setEnergyPowerGroupSOC2(double energyPowerGroupSOC2) {
        EnergyPowerGroupSOC2 = energyPowerGroupSOC2;
    }
}
