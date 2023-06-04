package com.ntdq.power_station.domain.engrgy.yx;

import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

/**
 * 南瑞保护装置YX信息
 */
@ModelDeviceInfo(mainType = 7, modelType = 3, subType = 12)
public class NariProtectDeviceYX {
    /**
     * 1#开关柜保护装置闭锁
     */
    @ModelArguments(code = "APhaseVOfSwitch", mean = "开关柜保护装置闭锁", number = 1)
    private float APhaseVOfSwitch;

    /**
     * 1#开关柜保护装置报警
     */
    @ModelArguments(code = "SwitchProtectDevice", mean = "开关柜保护装置报警", number = 2)
    private float SwitchProtectDevice;

    /**
     * 1#开关柜断路器合位
     */
    @ModelArguments(code = "SwitchCabinetClosed", mean = "开关柜断路器合位", number = 3)
    private float SwitchCabinetClosed;

    /**
     * 1#开关柜断路器分位
     */
    @ModelArguments(code = "SwitchCabinetQuantile", mean = "开关柜断路器分位", number = 4)
    private float SwitchCabinetQuantile;

    /**
     * 1#开关柜手车试验位
     */
    @ModelArguments(code = "SwitchHandCarPosition", mean = "开关柜手车试验位", number = 5)
    private float SwitchHandCarTryPosition;

    /**
     * 1#开关柜手车工作位
     */
    @ModelArguments(code = "SwitchHandCarWorkPosition", mean = "开关柜手车工作位", number = 6)
    private float SwitchHandCarWorkPosition;

    /**
     * 1#开关柜地刀合位
     */
    @ModelArguments(code = "SwitchCabinetGroundClosed", mean = "开关柜地刀合位", number = 7)
    private float SwitchCabinetGroundClosed;

    /**
     * 1#开关柜跳闸保护
     */
    @ModelArguments(code = "SwitchCabinetTripPro", mean = "开关柜跳闸保护", number = 8)
    private float SwitchCabinetTripPro;

    /**
     * 1#逆功率保护装置闭锁
     */
    @ModelArguments(code = "ReversePowerProDeviceBlock", mean = "逆功率保护装置闭锁", number = 9)
    private float ReversePowerProDeviceBlock;

    /**
     * 1#逆功率保护装置报警
     */
    @ModelArguments(code = "ReversePowerProDeviceAlarm", mean = "逆功率保护装置报警", number = 10)
    private float ReversePowerProDeviceAlarm;

    /**
     * 1#逆功率跳闸保护
     */
    @ModelArguments(code = "ReversePowerTripPro", mean = "逆功率跳闸保护", number = 11)
    private float ReversePowerTripPro;

    /**
     * 1#电能质量检测频率越限报警
     */
    @ModelArguments(code = "PowerQuanOverFreAlarm", mean = "电能质量检测频率越限报警", number = 12)
    private float PowerQuanOverFreAlarm;

    /**
     * 1#电能质量检测电压总谐波畸变率越限报警
     */
    @ModelArguments(code = "PowerQuanOverVoltageAlarm", mean = "电能质量检测电压总谐波畸变率越限报警", number = 13)
    private float PowerQuanOverVoltageAlarm;

    /**
     * 1#电能质量检测电流总谐波畸变率越限报警
     */
    @ModelArguments(code = "PowerQuanOverCurrentAlarm", mean = "电能质量检测电流总谐波畸变率越限报警", number = 14)
    private float PowerQuanOverCurrentAlarm;

    /**
     * 1#电能质量检测装置闭锁
     */
    @ModelArguments(code = "PowerQuanBlock", mean = "电能质量检测装置闭锁", number = 15)
    private float PowerQuanBlock;

    /**
     * 1#电能质量检测报警输出
     */
    @ModelArguments(code = "PowerQuanAlarmOut", mean = "电能质量检测报警输出", number = 16)
    private float PowerQuanAlarmOut;

    /**
     * 1#9726装置报警
     */
    @ModelArguments(code = "WarningDevice9726", mean = "9726装置报警", number = 17)
    private float WarningDevice9726;

    /**
     * 1#9726装置闭锁
     */
    @ModelArguments(code = "BlockDevice9726", mean = "9726装置闭锁", number = 18)
    private float BlockDevice9726;

    /**
     * 1#主变油温高跳闸
     */
    @ModelArguments(code = "MainTranOilTempHighTrip", mean = "主变油温高跳闸", number = 19)
    private float  MainTranOilTempHighTrip;

    public float getAPhaseVOfSwitch() {
        return APhaseVOfSwitch;
    }

    public void setAPhaseVOfSwitch(float APhaseVOfSwitch) {
        this.APhaseVOfSwitch = APhaseVOfSwitch;
    }

    public float getSwitchProtectDevice() {
        return SwitchProtectDevice;
    }

    public void setSwitchProtectDevice(float switchProtectDevice) {
        SwitchProtectDevice = switchProtectDevice;
    }

    public float getSwitchCabinetClosed() {
        return SwitchCabinetClosed;
    }

    public void setSwitchCabinetClosed(float switchCabinetClosed) {
        SwitchCabinetClosed = switchCabinetClosed;
    }

    public float getSwitchCabinetQuantile() {
        return SwitchCabinetQuantile;
    }

    public void setSwitchCabinetQuantile(float switchCabinetQuantile) {
        SwitchCabinetQuantile = switchCabinetQuantile;
    }

    public float getSwitchHandCarTryPosition() {
        return SwitchHandCarTryPosition;
    }

    public void setSwitchHandCarTryPosition(float switchHandCarTryPosition) {
        SwitchHandCarTryPosition = switchHandCarTryPosition;
    }

    public float getSwitchHandCarWorkPosition() {
        return SwitchHandCarWorkPosition;
    }

    public void setSwitchHandCarWorkPosition(float switchHandCarWorkPosition) {
        SwitchHandCarWorkPosition = switchHandCarWorkPosition;
    }

    public float getSwitchCabinetGroundClosed() {
        return SwitchCabinetGroundClosed;
    }

    public void setSwitchCabinetGroundClosed(float switchCabinetGroundClosed) {
        SwitchCabinetGroundClosed = switchCabinetGroundClosed;
    }

    public float getSwitchCabinetTripPro() {
        return SwitchCabinetTripPro;
    }

    public void setSwitchCabinetTripPro(float switchCabinetTripPro) {
        SwitchCabinetTripPro = switchCabinetTripPro;
    }

    public float getReversePowerProDeviceBlock() {
        return ReversePowerProDeviceBlock;
    }

    public void setReversePowerProDeviceBlock(float reversePowerProDeviceBlock) {
        ReversePowerProDeviceBlock = reversePowerProDeviceBlock;
    }

    public float getReversePowerProDeviceAlarm() {
        return ReversePowerProDeviceAlarm;
    }

    public void setReversePowerProDeviceAlarm(float reversePowerProDeviceAlarm) {
        ReversePowerProDeviceAlarm = reversePowerProDeviceAlarm;
    }

    public float getReversePowerTripPro() {
        return ReversePowerTripPro;
    }

    public void setReversePowerTripPro(float reversePowerTripPro) {
        ReversePowerTripPro = reversePowerTripPro;
    }

    public float getPowerQuanOverFreAlarm() {
        return PowerQuanOverFreAlarm;
    }

    public void setPowerQuanOverFreAlarm(float powerQuanOverFreAlarm) {
        PowerQuanOverFreAlarm = powerQuanOverFreAlarm;
    }

    public float getPowerQuanOverVoltageAlarm() {
        return PowerQuanOverVoltageAlarm;
    }

    public void setPowerQuanOverVoltageAlarm(float powerQuanOverVoltageAlarm) {
        PowerQuanOverVoltageAlarm = powerQuanOverVoltageAlarm;
    }

    public float getPowerQuanOverCurrentAlarm() {
        return PowerQuanOverCurrentAlarm;
    }

    public void setPowerQuanOverCurrentAlarm(float powerQuanOverCurrentAlarm) {
        PowerQuanOverCurrentAlarm = powerQuanOverCurrentAlarm;
    }

    public float getPowerQuanBlock() {
        return PowerQuanBlock;
    }

    public void setPowerQuanBlock(float powerQuanBlock) {
        PowerQuanBlock = powerQuanBlock;
    }

    public float getPowerQuanAlarmOut() {
        return PowerQuanAlarmOut;
    }

    public void setPowerQuanAlarmOut(float powerQuanAlarmOut) {
        PowerQuanAlarmOut = powerQuanAlarmOut;
    }

    public float getWarningDevice9726() {
        return WarningDevice9726;
    }

    public void setWarningDevice9726(float warningDevice9726) {
        WarningDevice9726 = warningDevice9726;
    }

    public float getBlockDevice9726() {
        return BlockDevice9726;
    }

    public void setBlockDevice9726(float blockDevice9726) {
        BlockDevice9726 = blockDevice9726;
    }

    public float getMainTranOilTempHighTrip() {
        return MainTranOilTempHighTrip;
    }

    public void setMainTranOilTempHighTrip(float mainTranOilTempHighTrip) {
        MainTranOilTempHighTrip = mainTranOilTempHighTrip;
    }
}
