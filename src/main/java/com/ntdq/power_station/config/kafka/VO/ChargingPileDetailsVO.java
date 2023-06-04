package com.ntdq.power_station.config.kafka.VO;

/**
 * 充电桩具体情况
 */
public class ChargingPileDetailsVO {

    /**
     * 直流桩个数
     */
    private int DCPileNums;

    /**
     * 交流桩个数
     */
    private int ACPileNums;

    /**
     * 充电桩总额定功率
     */
    private double PileAllRatedPower;

    /**
     * 充电桩总充电次数
     */
    private int PileAllChargeNums;

    /**
     * 当日充电收益
     */
    private double InComeChargeDay;

    /**
     * 当日充电量
     */
    private double ChargeCapacityDay;

    /**
     * 充电桩日均时间利用率
     */
    private double PileAverageDailyTimeUtilization;

    /**
     * 充电桩日均功率利用率
     */
    private double PileDailyAveragePowerUtilization;

    /**
     * 充电桩日均离线率
     */
    private double PileAverageDailyOfflineRate;

    /**
     * 充电桩日均故障率
     */
    private double PileAverageDailyFailureRate;

    public int getDCPileNums() {
        return DCPileNums;
    }

    public void setDCPileNums(int DCPileNums) {
        this.DCPileNums = DCPileNums;
    }

    public int getACPileNums() {
        return ACPileNums;
    }

    public void setACPileNums(int ACPileNums) {
        this.ACPileNums = ACPileNums;
    }

    public double getPileAllRatedPower() {
        return PileAllRatedPower;
    }

    public void setPileAllRatedPower(double pileAllRatedPower) {
        PileAllRatedPower = pileAllRatedPower;
    }

    public int getPileAllChargeNums() {
        return PileAllChargeNums;
    }

    public void setPileAllChargeNums(int pileAllChargeNums) {
        PileAllChargeNums = pileAllChargeNums;
    }

    public double getInComeChargeDay() {
        return InComeChargeDay;
    }

    public void setInComeChargeDay(double inComeChargeDay) {
        InComeChargeDay = inComeChargeDay;
    }

    public double getChargeCapacityDay() {
        return ChargeCapacityDay;
    }

    public void setChargeCapacityDay(double chargeCapacityDay) {
        ChargeCapacityDay = chargeCapacityDay;
    }

    public double getPileAverageDailyTimeUtilization() {
        return PileAverageDailyTimeUtilization;
    }

    public void setPileAverageDailyTimeUtilization(double pileAverageDailyTimeUtilization) {
        PileAverageDailyTimeUtilization = pileAverageDailyTimeUtilization;
    }

    public double getPileDailyAveragePowerUtilization() {
        return PileDailyAveragePowerUtilization;
    }

    public void setPileDailyAveragePowerUtilization(double pileDailyAveragePowerUtilization) {
        PileDailyAveragePowerUtilization = pileDailyAveragePowerUtilization;
    }

    public double getPileAverageDailyOfflineRate() {
        return PileAverageDailyOfflineRate;
    }

    public void setPileAverageDailyOfflineRate(double pileAverageDailyOfflineRate) {
        PileAverageDailyOfflineRate = pileAverageDailyOfflineRate;
    }

    public double getPileAverageDailyFailureRate() {
        return PileAverageDailyFailureRate;
    }

    public void setPileAverageDailyFailureRate(double pileAverageDailyFailureRate) {
        PileAverageDailyFailureRate = pileAverageDailyFailureRate;
    }
}
