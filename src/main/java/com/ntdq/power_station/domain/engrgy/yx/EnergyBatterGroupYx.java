package com.ntdq.power_station.domain.engrgy.yx;

import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

/**
 * 电池组遥信
 */
@ModelDeviceInfo(mainType = 7, modelType = 3, subType = 8)
public class EnergyBatterGroupYx {

    /**
     * 单体电压过高告警
     */
    @ModelArguments(code = "HighMonomerVoltageAlarm", mean = "单体电压过高告警", number = 1)
    private int HighMonomerVoltageAlarm;

    /**
     * 单体电压过低告警
     */
    @ModelArguments(code = "LowMonomerVoltageAlarm", mean = "单体电压过低告警", number = 2)
    private int LowMonomerVoltageAlarm;

    /**
     * 单体温度过高告警
     */
    @ModelArguments(code = "SingleHighTemperatureAlarm", mean = "单体温度过高告警", number = 3)
    private int SingleHighTemperatureAlarm;

    /**
     * 单体温度过低告警
     */
    @ModelArguments(code = "SingleLowTemperatureAlarm", mean = "单体温度过低告警", number = 4)
    private int SingleLowTemperatureAlarm;

    /**
     * 单体电压过高保护
     */
    @ModelArguments(code = "MonomerHighVoltageProtection", mean = "单体电压过高保护", number = 5)
    private int MonomerHighVoltageProtection;

    /**
     * 单体电压过低保护
     */
    @ModelArguments(code = "MonomerLowVoltageProtection", mean = "单体电压过低保护", number = 6)
    private int MonomerLowVoltageProtection;

    /**
     * 单体温度过高保护
     */
    @ModelArguments(code = "SingleHighTemPro", mean = "单体温度过高保护", number = 7)
    private int SingleHighTemPro;


    /**
     * 单体温度过低保护
     */
    @ModelArguments(code = "SingleLowTemPro", mean = "单体温度过低保护", number = 8)
    private int SingleLowTemPro;

    /**
     * 组电压过高告警
     */
    @ModelArguments(code = "GroupVoltageHighAlarm", mean = "组电压过高告警", number = 9)
    private int GroupVoltageHighAlarm;

    /**
     * 组电压过低告警
     */
    @ModelArguments(code = "GroupVoltageLowAlarm", mean = "组电压过低告警", number = 10)
    private int GroupVoltageLowAlarm;

    /**
     * 组电流过高告警
     */
    @ModelArguments(code = "GroupCurrentLowAlarm", mean = "组电流过高告警", number = 11)
    private int GroupCurrentLowAlarm;

    /**
     * 环境温度过高告警
     */
    @ModelArguments(code = "HighTemAlarm", mean = "环境温度过高告警", number = 12)
    private int HighTemAlarm;

    /**
     * 环境温度过低告警
     */
    @ModelArguments(code = "LowTemAlarm", mean = "环境温度过低告警", number = 13)
    private int LowTemAlarm;



    /**
     * 组端SOC超下限告警
     */
    @ModelArguments(code = "SOCOverDownLimitAlarm", mean = "组端SOC超下限告警", number = 14)
    private int SOCOverDownLimitAlarm;

    /**
     * 组电压过高保护
     */
    @ModelArguments(code = "GroupVoltageHighPro", mean = "组电压过高保护", number = 15)
    private int GroupVoltageHighPro;

    /**
     * 组电压过低保护
     */
    @ModelArguments(code = "GroupVoltageLowPro", mean = "组电压过低保护", number = 16)
    private int GroupVoltageLowPro;



    /**
     * 组电流过高保护
     */
    @ModelArguments(code = "GroupCurrentHighPro", mean = "组电流过高保护", number = 17)
    private int GroupCurrentHighPro;

    /**
     * 环境温度过高保护
     */
    @ModelArguments(code = "EnvironTemHighPro", mean = "环境温度过高保护", number = 18)
    private int EnvironTemHighPro;

    /**
     * 环境温度过低保护
     */
    @ModelArguments(code = "EnvironTemLowPro", mean = "环境温度过低保护", number = 19)
    private int EnvironTemLowPro;



    /**
     * 组端SOC超下限保护
     */
    @ModelArguments(code = "GroupSOCOverDownLimitPro", mean = "组端SOC超下限保护", number = 20)
    private int GroupSOCOverDownLimitPro;

    /**
     * BMU设备连接故障告警
     */
    @ModelArguments(code = "BMUDeviceConnectAlarm", mean = "BMU设备连接故障告警", number = 21)
    private int BMUDeviceConnectAlarm;

    /**
     * BCMU设备连接故障告警
     */
    @ModelArguments(code = "BCMUDeviceConnectAlarm", mean = "BCMU设备连接故障告警", number = 22)
    private int BCMUDeviceConnectAlarm;



    /**
     * 单体电压过高预警
     */
    @ModelArguments(code = "HighMonomerVoltageWarning", mean = "单体电压过高预警", number = 23)
    private int HighMonomerVoltageWarning;

    /**
     * 单体电压过低预警
     */
    @ModelArguments(code = "LowMonomerVoltageWarning", mean = "单体电压过低预警", number = 24)
    private int LowMonomerVoltageWarning;

    /**
     * 单体温度过高预警
     */
    @ModelArguments(code = "SignalTemHighWarning", mean = "单体温度过高预警", number = 25)
    private int SignalTemHighWarning;



    /**
     * 单体温度过低预警
     */
    @ModelArguments(code = "SignalTemLowWarning", mean = "单体温度过低预警", number = 26)
    private int SignalTemLowWarning;

    /**
     * 组端SOC超上限告警
     */
    @ModelArguments(code = "GroupSOCOverTopLimitWarning", mean = "组端SOC超上限告警", number = 27)
    private int GroupSOCOverTopLimitWarning;

    /**
     * 组端SOC超上限保护
     */
    @ModelArguments(code = "GroupSOCOverTopPro", mean = "组端SOC超上限保护", number = 28)
    private int GroupSOCOverTopPro;



    /**
     * 正极绝缘内阻下限保护
     */
    @ModelArguments(code = "PositiveInsulationDownPro", mean = "正极绝缘内阻下限保护", number = 29)
    private int PositiveInsulationDownPro;

    /**
     * 负极绝缘内阻下限保护
     */
    @ModelArguments(code = "BurdenInsulationDownPro", mean = "负极绝缘内阻下限保护", number = 30)
    private int BurdenInsulationDownPro;

    /**
     * 箱压差上限告警
     */
    @ModelArguments(code = "BoxVoltageTopAlarm", mean = "箱压差上限告警", number = 31)
    private int BoxVoltageTopAlarm;



    /**
     * 箱压差上限保护
     */
    @ModelArguments(code = "BoxVoltageTopPro", mean = "箱压差上限保护", number = 32)
    private int BoxVoltageTopPro;

    /**
     * 箱温差上限告警
     */
    @ModelArguments(code = "BoxTemTopPro", mean = "箱温差上限告警", number = 33)
    private int BoxTemTopPro;

    /**
     * 箱温差上限保护
     */
    @ModelArguments(code = "BoxTemDownPro", mean = "箱温差上限保护", number = 34)
    private int BoxTemDownPro;


    /**
     * 组电压过高预警
     */
    @ModelArguments(code = "GroupVoltageHighWarning", mean = "组电压过高预警", number = 35)
    private int GroupVoltageHighWarning;

    /**
     * 组电压过低预警
     */
    @ModelArguments(code = "GroupVoltageLowWarning", mean = "组电压过低预警", number = 36)
    private int GroupVoltageLowWarning;

    /**
     * 组电流过高预警
     */
    @ModelArguments(code = "GroupCurrentLowWarning", mean = "组电流过高预警", number = 37)
    private int GroupCurrentLowWarning;



    /**
     * 环境温度过高预警
     */
    @ModelArguments(code = "EnvironTemHighWarning", mean = "环境温度过高预警", number = 38)
    private int EnvironTemHighWarning;

    /**
     * 环境温度过低预警
     */
    @ModelArguments(code = "EnvironTemHLowWarning", mean = "环境温度过低预警", number = 39)
    private int EnvironTemHLowWarning;

    /**
     * SOC超上限预警
     */
    @ModelArguments(code = "SOCOverTopWarning", mean = "SOC超上限预警", number = 40)
    private int SOCOverTopWarning;



    /**
     * SOC超下限预警
     */
    @ModelArguments(code = "SOCOverDownWarning", mean = "SOC超下限预警", number = 41)
    private int SOCOverDownWarning;

    /**
     * 箱压差上限预警
     */
    @ModelArguments(code = "BoxVoltageTopWarning", mean = "箱压差上限预警", number = 42)
    private int BoxVoltageTopWarning;

    /**
     * 箱温差上限预警
     */
    @ModelArguments(code = "BoxTemTopWarning", mean = "箱温差上限预警", number = 43)
    private int BoxTemTopWarning;


    /**
     * 落后电池电压多次保护
     */
    @ModelArguments(code = "BatteryVoltagePros", mean = "落后电池电压多次保护", number = 44)
    private int BatteryVoltagePros;

    /**
     * 单体电压采集线故障
     */
    @ModelArguments(code = "SignalVoltageAcLineFail", mean = "单体电压采集线故障", number = 45)
    private int SignalVoltageAcLineFail;

    /**
     * 总压采集线故障
     */
    @ModelArguments(code = "TotalVoltageAcLineFail", mean = "总压采集线故障", number = 46)
    private int TotalVoltageAcLineFail;


    /**
     * 电流采集线故障
     */
    @ModelArguments(code = "CurrentAcLineFail", mean = "电流采集线故障", number = 47)
    private int CurrentAcLineFail;


    /**
     * 温度采集断线
     */
    @ModelArguments(code = "TemAcLineFail", mean = "温度采集断线", number = 48)
    private int TemAcLineFail;

    /**
     * 温度采集短路
     */
    @ModelArguments(code = "TemAcShortCut", mean = "温度采集短路", number = 49)
    private int TemAcShortCut;

    /**
     * BMS设备故障
     */
    @ModelArguments(code = "BMSDeviceFail", mean = "BMS设备故障", number = 50)
    private int BMSDeviceFail;

    public int getHighMonomerVoltageAlarm() {
        return HighMonomerVoltageAlarm;
    }

    public void setHighMonomerVoltageAlarm(int highMonomerVoltageAlarm) {
        HighMonomerVoltageAlarm = highMonomerVoltageAlarm;
    }

    public int getLowMonomerVoltageAlarm() {
        return LowMonomerVoltageAlarm;
    }

    public void setLowMonomerVoltageAlarm(int lowMonomerVoltageAlarm) {
        LowMonomerVoltageAlarm = lowMonomerVoltageAlarm;
    }

    public int getSingleHighTemperatureAlarm() {
        return SingleHighTemperatureAlarm;
    }

    public void setSingleHighTemperatureAlarm(int singleHighTemperatureAlarm) {
        SingleHighTemperatureAlarm = singleHighTemperatureAlarm;
    }

    public int getSingleLowTemperatureAlarm() {
        return SingleLowTemperatureAlarm;
    }

    public void setSingleLowTemperatureAlarm(int singleLowTemperatureAlarm) {
        SingleLowTemperatureAlarm = singleLowTemperatureAlarm;
    }

    public int getMonomerHighVoltageProtection() {
        return MonomerHighVoltageProtection;
    }

    public void setMonomerHighVoltageProtection(int monomerHighVoltageProtection) {
        MonomerHighVoltageProtection = monomerHighVoltageProtection;
    }

    public int getMonomerLowVoltageProtection() {
        return MonomerLowVoltageProtection;
    }

    public void setMonomerLowVoltageProtection(int monomerLowVoltageProtection) {
        MonomerLowVoltageProtection = monomerLowVoltageProtection;
    }

    public int getSingleHighTemPro() {
        return SingleHighTemPro;
    }

    public void setSingleHighTemPro(int singleHighTemPro) {
        SingleHighTemPro = singleHighTemPro;
    }

    public int getSingleLowTemPro() {
        return SingleLowTemPro;
    }

    public void setSingleLowTemPro(int singleLowTemPro) {
        SingleLowTemPro = singleLowTemPro;
    }

    public int getGroupVoltageHighAlarm() {
        return GroupVoltageHighAlarm;
    }

    public void setGroupVoltageHighAlarm(int groupVoltageHighAlarm) {
        GroupVoltageHighAlarm = groupVoltageHighAlarm;
    }

    public int getGroupVoltageLowAlarm() {
        return GroupVoltageLowAlarm;
    }

    public void setGroupVoltageLowAlarm(int groupVoltageLowAlarm) {
        GroupVoltageLowAlarm = groupVoltageLowAlarm;
    }

    public int getGroupCurrentLowAlarm() {
        return GroupCurrentLowAlarm;
    }

    public void setGroupCurrentLowAlarm(int groupCurrentLowAlarm) {
        GroupCurrentLowAlarm = groupCurrentLowAlarm;
    }

    public int getHighTemAlarm() {
        return HighTemAlarm;
    }

    public void setHighTemAlarm(int highTemAlarm) {
        HighTemAlarm = highTemAlarm;
    }

    public int getLowTemAlarm() {
        return LowTemAlarm;
    }

    public void setLowTemAlarm(int lowTemAlarm) {
        LowTemAlarm = lowTemAlarm;
    }

    public int getSOCOverDownLimitAlarm() {
        return SOCOverDownLimitAlarm;
    }

    public void setSOCOverDownLimitAlarm(int SOCOverDownLimitAlarm) {
        this.SOCOverDownLimitAlarm = SOCOverDownLimitAlarm;
    }

    public int getGroupVoltageHighPro() {
        return GroupVoltageHighPro;
    }

    public void setGroupVoltageHighPro(int groupVoltageHighPro) {
        GroupVoltageHighPro = groupVoltageHighPro;
    }

    public int getGroupVoltageLowPro() {
        return GroupVoltageLowPro;
    }

    public void setGroupVoltageLowPro(int groupVoltageLowPro) {
        GroupVoltageLowPro = groupVoltageLowPro;
    }

    public int getGroupCurrentHighPro() {
        return GroupCurrentHighPro;
    }

    public void setGroupCurrentHighPro(int groupCurrentHighPro) {
        GroupCurrentHighPro = groupCurrentHighPro;
    }

    public int getEnvironTemHighPro() {
        return EnvironTemHighPro;
    }

    public void setEnvironTemHighPro(int environTemHighPro) {
        EnvironTemHighPro = environTemHighPro;
    }

    public int getEnvironTemLowPro() {
        return EnvironTemLowPro;
    }

    public void setEnvironTemLowPro(int environTemLowPro) {
        EnvironTemLowPro = environTemLowPro;
    }

    public int getGroupSOCOverDownLimitPro() {
        return GroupSOCOverDownLimitPro;
    }

    public void setGroupSOCOverDownLimitPro(int groupSOCOverDownLimitPro) {
        GroupSOCOverDownLimitPro = groupSOCOverDownLimitPro;
    }

    public int getBMUDeviceConnectAlarm() {
        return BMUDeviceConnectAlarm;
    }

    public void setBMUDeviceConnectAlarm(int BMUDeviceConnectAlarm) {
        this.BMUDeviceConnectAlarm = BMUDeviceConnectAlarm;
    }

    public int getBCMUDeviceConnectAlarm() {
        return BCMUDeviceConnectAlarm;
    }

    public void setBCMUDeviceConnectAlarm(int BCMUDeviceConnectAlarm) {
        this.BCMUDeviceConnectAlarm = BCMUDeviceConnectAlarm;
    }

    public int getHighMonomerVoltageWarning() {
        return HighMonomerVoltageWarning;
    }

    public void setHighMonomerVoltageWarning(int highMonomerVoltageWarning) {
        HighMonomerVoltageWarning = highMonomerVoltageWarning;
    }

    public int getLowMonomerVoltageWarning() {
        return LowMonomerVoltageWarning;
    }

    public void setLowMonomerVoltageWarning(int lowMonomerVoltageWarning) {
        LowMonomerVoltageWarning = lowMonomerVoltageWarning;
    }

    public int getSignalTemHighWarning() {
        return SignalTemHighWarning;
    }

    public void setSignalTemHighWarning(int signalTemHighWarning) {
        SignalTemHighWarning = signalTemHighWarning;
    }

    public int getSignalTemLowWarning() {
        return SignalTemLowWarning;
    }

    public void setSignalTemLowWarning(int signalTemLowWarning) {
        SignalTemLowWarning = signalTemLowWarning;
    }

    public int getGroupSOCOverTopLimitWarning() {
        return GroupSOCOverTopLimitWarning;
    }

    public void setGroupSOCOverTopLimitWarning(int groupSOCOverTopLimitWarning) {
        GroupSOCOverTopLimitWarning = groupSOCOverTopLimitWarning;
    }

    public int getGroupSOCOverTopPro() {
        return GroupSOCOverTopPro;
    }

    public void setGroupSOCOverTopPro(int groupSOCOverTopPro) {
        GroupSOCOverTopPro = groupSOCOverTopPro;
    }

    public int getPositiveInsulationDownPro() {
        return PositiveInsulationDownPro;
    }

    public void setPositiveInsulationDownPro(int positiveInsulationDownPro) {
        PositiveInsulationDownPro = positiveInsulationDownPro;
    }

    public int getBurdenInsulationDownPro() {
        return BurdenInsulationDownPro;
    }

    public void setBurdenInsulationDownPro(int burdenInsulationDownPro) {
        BurdenInsulationDownPro = burdenInsulationDownPro;
    }

    public int getBoxVoltageTopAlarm() {
        return BoxVoltageTopAlarm;
    }

    public void setBoxVoltageTopAlarm(int boxVoltageTopAlarm) {
        BoxVoltageTopAlarm = boxVoltageTopAlarm;
    }

    public int getBoxVoltageTopPro() {
        return BoxVoltageTopPro;
    }

    public void setBoxVoltageTopPro(int boxVoltageTopPro) {
        BoxVoltageTopPro = boxVoltageTopPro;
    }

    public int getBoxTemTopPro() {
        return BoxTemTopPro;
    }

    public void setBoxTemTopPro(int boxTemTopPro) {
        BoxTemTopPro = boxTemTopPro;
    }

    public int getBoxTemDownPro() {
        return BoxTemDownPro;
    }

    public void setBoxTemDownPro(int boxTemDownPro) {
        BoxTemDownPro = boxTemDownPro;
    }

    public int getGroupVoltageHighWarning() {
        return GroupVoltageHighWarning;
    }

    public void setGroupVoltageHighWarning(int groupVoltageHighWarning) {
        GroupVoltageHighWarning = groupVoltageHighWarning;
    }

    public int getGroupVoltageLowWarning() {
        return GroupVoltageLowWarning;
    }

    public void setGroupVoltageLowWarning(int groupVoltageLowWarning) {
        GroupVoltageLowWarning = groupVoltageLowWarning;
    }

    public int getGroupCurrentLowWarning() {
        return GroupCurrentLowWarning;
    }

    public void setGroupCurrentLowWarning(int groupCurrentLowWarning) {
        GroupCurrentLowWarning = groupCurrentLowWarning;
    }

    public int getEnvironTemHighWarning() {
        return EnvironTemHighWarning;
    }

    public void setEnvironTemHighWarning(int environTemHighWarning) {
        EnvironTemHighWarning = environTemHighWarning;
    }

    public int getEnvironTemHLowWarning() {
        return EnvironTemHLowWarning;
    }

    public void setEnvironTemHLowWarning(int environTemHLowWarning) {
        EnvironTemHLowWarning = environTemHLowWarning;
    }

    public int getSOCOverTopWarning() {
        return SOCOverTopWarning;
    }

    public void setSOCOverTopWarning(int SOCOverTopWarning) {
        this.SOCOverTopWarning = SOCOverTopWarning;
    }

    public int getSOCOverDownWarning() {
        return SOCOverDownWarning;
    }

    public void setSOCOverDownWarning(int SOCOverDownWarning) {
        this.SOCOverDownWarning = SOCOverDownWarning;
    }

    public int getBoxVoltageTopWarning() {
        return BoxVoltageTopWarning;
    }

    public void setBoxVoltageTopWarning(int boxVoltageTopWarning) {
        BoxVoltageTopWarning = boxVoltageTopWarning;
    }

    public int getBoxTemTopWarning() {
        return BoxTemTopWarning;
    }

    public void setBoxTemTopWarning(int boxTemTopWarning) {
        BoxTemTopWarning = boxTemTopWarning;
    }

    public int getBatteryVoltagePros() {
        return BatteryVoltagePros;
    }

    public void setBatteryVoltagePros(int batteryVoltagePros) {
        BatteryVoltagePros = batteryVoltagePros;
    }

    public int getSignalVoltageAcLineFail() {
        return SignalVoltageAcLineFail;
    }

    public void setSignalVoltageAcLineFail(int signalVoltageAcLineFail) {
        SignalVoltageAcLineFail = signalVoltageAcLineFail;
    }

    public int getTotalVoltageAcLineFail() {
        return TotalVoltageAcLineFail;
    }

    public void setTotalVoltageAcLineFail(int totalVoltageAcLineFail) {
        TotalVoltageAcLineFail = totalVoltageAcLineFail;
    }

    public int getCurrentAcLineFail() {
        return CurrentAcLineFail;
    }

    public void setCurrentAcLineFail(int currentAcLineFail) {
        CurrentAcLineFail = currentAcLineFail;
    }

    public int getTemAcLineFail() {
        return TemAcLineFail;
    }

    public void setTemAcLineFail(int temAcLineFail) {
        TemAcLineFail = temAcLineFail;
    }

    public int getTemAcShortCut() {
        return TemAcShortCut;
    }

    public void setTemAcShortCut(int temAcShortCut) {
        TemAcShortCut = temAcShortCut;
    }

    public int getBMSDeviceFail() {
        return BMSDeviceFail;
    }

    public void setBMSDeviceFail(int BMSDeviceFail) {
        this.BMSDeviceFail = BMSDeviceFail;
    }
}
