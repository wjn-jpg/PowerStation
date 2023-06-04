package com.ntdq.power_station.domain.engrgy.yx;

import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

/**
 * 储能电池对yx信息
 */
@ModelDeviceInfo(mainType = 7, modelType = 3, subType = 8)
public class EnergyBatteryInvertYx {

    /**
     * 整组启动
     */
    @ModelArguments(code = "startForWhole", mean = "整组启动", number = 1)
    private int startForWhole;

    /**
     * 保护动作
     */
    @ModelArguments(code = "protectAction", mean = "保护动作", number = 2)
    private int protectAction;

    /**
     * 直流过流保护
     */
    @ModelArguments(code = "DCOverCurrentProtection", mean = "直流过流保护", number = 3)
    private int overCurrentProtection;

    /**
     * 直流过压保护
     */
    @ModelArguments(code = "DCOverVoltageProtection", mean = "直流过压保护", number = 4)
    private int overVoltageProtection;

    /**
     * 直流欠压保护
     */
    @ModelArguments(code = "DCUnderVoltageProtection", mean = "直流欠压保护", number = 5)
    private int underVoltageProtection;

    /**
     * 直流反接保护
     */
    @ModelArguments(code = "DCReverseVoltageProtection", mean = "直流反接保护", number = 6)
    private int DCReverseVoltageProtection;

    /**
     * 直流防反放电保护
     */
    @ModelArguments(code = "DefenceDcDischargePro", mean = "直流防反放电保护", number = 7)
    private int DefenceDcDischargePro;

    /**
     * 交流过流保护
     */
    @ModelArguments(code = "OverCurrentProtection", mean = "交流过流保护", number = 8)
    private int OverCurrentProtection;

    /**
     * 交流电流不平衡保护
     */
    @ModelArguments(code = "BCUnPro", mean = "交流电流不平衡保护", number = 9)
    private int BCUnPro;

    /**
     * 交流过压保护
     */
    @ModelArguments(code = "BCOverVPro", mean = "交流过压保护", number = 10)
    private int BCOverVPro;

    /**
     * 交流欠压保护
     */
    @ModelArguments(code = "BCUnderVPro", mean = "交流欠压保护", number = 11)
    private int BCUnderVPro;

    /**
     * 交流电压不平衡保护
     */
    @ModelArguments(code = "VImbalancePro", mean = "交流电压不平衡保护", number = 12)
    private int VImbalancePro;

    /**
     * 过频保护
     */
    @ModelArguments(code = "OverFrePro", mean = "过频保护", number = 13)
    private int OverFrePro;

    /**
     * 欠频保护
     */
    @ModelArguments(code = "NotFrePro", mean = "欠频保护", number = 14)
    private int NotFrePro;

    /**
     * 过温保护
     */
    @ModelArguments(code = "OverTemPro", mean = "过温保护", number = 15)
    private int OverTemPro;

    /**
     * 低温闭锁
     */
    @ModelArguments(code = "LowTemAtr", mean = "低温闭锁", number = 16)
    private int LowTemAtr;

    /**
     * 过载保护
     */
    @ModelArguments(code = "OverLoadPro", mean = "过载保护", number = 17)
    private int OverLoadPro;

    /**
     * LVRT保护
     */
    @ModelArguments(code = "LVRTPro", mean = "LVRT保护", number = 18)
    private int LVRTPro;

    /**
     * 驱动保护
     */
    @ModelArguments(code = "DriverPro", mean = "驱动保护", number = 19)
    private int DriverPro;

    /**
     * A相驱动保护
     */
    @ModelArguments(code = "ADriverPro", mean = "A相驱动保护", number = 20)
    private int ADriverPro;


    /**
     * B相驱动保护
     */
    @ModelArguments(code = "BDriverPro", mean = "B相驱动保护 ", number = 21)
    private int BDriverPro;

    /**
     * C相驱动保护
     */
    @ModelArguments(code = "CDriverPro", mean = "C相驱动保护", number = 22)
    private int CDriverPro;

    /**
     * 逆变器内部故障
     */
    @ModelArguments(code = "InverterInternalFault", mean = "逆变器内部故障", number = 23)
    private int InverterInternalFault;

    /**
     * 脉冲闭锁
     */
    @ModelArguments(code = "PulseBlock", mean = "脉冲闭锁", number = 24)
    private int PulseBlock;

    /**
     * PT异常保护
     */
    @ModelArguments(code = "PTExPro", mean = "PT异常保护", number = 25)
    private int PTExPro;

    /**
     * 漏电流保护
     */
    @ModelArguments(code = "LeakAgeCurrentPro", mean = "漏电流保护", number = 26)
    private int LeakAgeCurrentPro;

    /**
     * 辅助电源异常保护
     */
    @ModelArguments(code = "AuxiliaryPowerPro", mean = "辅助电源异常保护", number = 27)
    private int AuxiliaryPowerPro;

    /**
     * 遥调指令故障
     */
    @ModelArguments(code = "RemoteSettingFail", mean = "遥调指令故障", number = 28)
    private int RemoteSettingFail;

    /**
     * 模式校验错误
     */
    @ModelArguments(code = "ModeCheckFail", mean = "模式校验错误", number = 29)
    private int ModeCheckFail;

    /**
     * Failure to start inverter comman
     */
    @ModelArguments(code = "FailStartInvertCommand", mean = "故障启动逆变器命令", number = 30)
    private int FailStartInvertCommand;

    /**
     * 自动启动逆变器命令
     */
    @ModelArguments(code = "AutoStartInvertCommand", mean = "自动启动逆变器命令", number = 31)
    private int AutoStartInvertCommand;

    /**
     * 手动启动逆变器命令
     */
    @ModelArguments(code = "ManualStartInvertCommand", mean = "手动启动逆变器命令", number = 32)
    private int ManualStartInvertCommand;

    /**
     * 逆变器并网条件具备
     */
    @ModelArguments(code = "InvertNet", mean = "逆变器并网条件具备", number = 33)
    private int InvertNetCon;

    /**
     * 黑启动条件具备
     */
    @ModelArguments(code = "BlackStartCon", mean = "黑启动条件具备", number = 34)
    private int BlackStartCon;

    /**
     * LVRT功能切换
     */
    @ModelArguments(code = "LVRTFunSwitch", mean = "LVRT功能切换", number = 35)
    private int LVRTFunSwitch;

    /**
     * 开入触发录波
     */
    @ModelArguments(code = "IntoTriggerWave", mean = "开入触发录波", number = 36)
    private int IntoTriggerWave;

    /**
     * 远方触发录波
     */
    @ModelArguments(code = "RemoteTriggerWave", mean = "远方触发录波", number = 37)
    private int RemoteTriggerWave;

    /**
     * 手动触发录波
     */
    @ModelArguments(code = "ManualTriggerWave", mean = "手动触发录波", number = 38)
    private int ManualTriggerWave;

    /**
     * 合并网接触器录波
     */
    @ModelArguments(code = "MergeNetContactWaveRecord", mean = "合并网接触器录波", number = 39)
    private int MergeNetContactWaveRecord;

    /**
     * 保护录波启动
     */
    @ModelArguments(code = "WaveRecordProStart", mean = "保护录波启动", number = 40)
    private int WaveRecordProStart;


    /**
     * 装置报警
     */
    @ModelArguments(code = "DeviceAlarm", mean = "装置报警", number = 41)
    private int DeviceAlarm;

    /**
     * 装置闭锁
     */
    @ModelArguments(code = "DeviceClosure", mean = "装置闭锁", number = 42)
    private int DeviceClosure;

    /**
     * 光耦电源异常
     */
    @ModelArguments(code = "ALightCoupingPower", mean = "光耦电源异常", number = 43)
    private int ALightCoupingPower;

    /**
     * 定值校验出错
     */
    @ModelArguments(code = "ConstantValueValidError", mean = "定值校验出错", number = 44)
    private int ConstantValueValidError;

    /**
     * 定值待确认
     */
    @ModelArguments(code = "ConstantValueToBeConfirmed", mean = "定值待确认", number = 45)
    private int ConstantValueToBeConfirmed;

    /**
     * 板卡配置错误
     */
    @ModelArguments(code = "BoardConfigurationErrors", mean = "板卡配置错误", number = 46)
    private int BoardConfigurationErrors;

    /**
     * 版本错误报警
     */
    @ModelArguments(code = "VersionFailAlarm", mean = "版本错误报警", number = 47)
    private int VersionFailAlarm;

    /**
     * 定值超范围
     */
    @ModelArguments(code = "ConstantBeyValue", mean = "定值超范围", number = 48)
    private int ConstantBeyValue;

    /**
     * 定值修改
     */
    @ModelArguments(code = "ConstantChange", mean = "定值修改", number = 49)
    private int ConstantChange;

    /**
     * 通信传动报警
     */
    @ModelArguments(code = "CommunicationTranAlarm", mean = "通信传动报警", number = 50)
    private int CommunicationTranAlarm;

    /**
     * 定值区不一致
     */
    @ModelArguments(code = "ConstantValueArea", mean = "定值区不一致", number = 51)
    private int ConstantValueArea;

    /**
     * 远方控制使能
     */
    @ModelArguments(code = "RemoteControlMake", mean = "远方控制使能", number = 52)
    private int RemoteControlMake;

    /**
     * BMS控制使能
     */
    @ModelArguments(code = "BMSControlMake", mean = "BCMU组端电压", number = 53)
    private int BMSControlMake;

    /**
     * 启动命令
     */
    @ModelArguments(code = "StartCommand", mean = "启动命令", number = 54)
    private int StartCommand;

    /**
     * 停机命令
     */
    @ModelArguments(code = "StopCommand", mean = "停机命令", number = 55)
    private int StopCommand;

    /**
     * 自动开关机
     */
    @ModelArguments(code = "AutoSwitchMac", mean = "BCMU组端电压", number = 56)
    private int AutoSwitchMac;

    /**
     * 黑启动命令
     */
    @ModelArguments(code = "BlackStartCommand", mean = "黑启动命令", number = 57)
    private int BlackStartCommand;

    /**
     * 投直流电压控制模式
     */
    @ModelArguments(code = "DcVoltageControlMode", mean = "投直流电压控制模式", number = 58)
    private int DcVoltageControlMode;

    /**
     * 投直流电流控制模式
     */
    @ModelArguments(code = "DcCurrentControlMode", mean = "投直流电流控制模式", number = 59)
    private int DcCurrentControlMode;

    /**
     * 投直流功率控制模式
     */
    @ModelArguments(code = "DcPowerControlMode", mean = "投直流功率控制模式", number = 60)
    private int DcPowerControlMode;


    /**
     * 投交流功率控制模式
     */
    @ModelArguments(code = "AcPowerControlMode", mean = "投交流功率控制模式", number = 61)
    private int AcPowerControlMode;

    /**
     * 投功率因数控制模式
     */
    @ModelArguments(code = "PowerFactorControlMode", mean = "投功率因数控制模式", number = 62)
    private int PowerFactorControlMode;

    /**
     * 投VSG控制模式
     */
    @ModelArguments(code = "VSGControlMode", mean = "投VSG控制模式", number = 63)
    private int VSGControlMode;

    /**
     * 投VF控制模式
     */
    @ModelArguments(code = "VFControlMode", mean = "投VF控制模式", number = 64)
    private int VFControlMode;

    /**
     * 投充放末期转恒压模式
     */
    @ModelArguments(code = "CastFillingPutEndVoltageMod", mean = "投充放末期转恒压模式", number = 65)
    private int CastFillingPutEndVoltageMod;

    /**
     * 投虚拟阻抗
     */
    @ModelArguments(code = "ForVirtualImpedance", mean = "投虚拟阻抗", number = 66)
    private int ForVirtualImpedance;

    /**
     * 投负序电压控制
     */
    @ModelArguments(code = "NeSeVoltageControl", mean = "投负序电压控制", number = 67)
    private int NeSeVoltageControl;

    /**
     * 正常运行
     */
    @ModelArguments(code = "RunNormal", mean = "正常运行", number = 68)
    private int RunNormal;

    /**
     * 告警运行
     */
    @ModelArguments(code = "RunAlarm", mean = "告警运行", number = 69)
    private int RunAlarm;

    /**
     * 待机状态
     */
    @ModelArguments(code = "StandByStatus", mean = "待机状态", number = 70)
    private int StandByStatus;

    /**
     * 正常停机
     */
    @ModelArguments(code = "NormalDown", mean = "正常停机", number = 71)
    private int NormalDown;

    /**
     * 故障停机
     */
    @ModelArguments(code = "BreakDown", mean = "故障停机", number = 72)
    private int BreakDown;

    /**
     * 充满自动停机
     */
    @ModelArguments(code = "AutoStopUntilFill", mean = "充满自动停机", number = 73)
    private int AutoStopUntilFill;

    /**
     * 放空自动停机
     */
    @ModelArguments(code = "AutoStopEmpty", mean = "放空自动停机", number = 74)
    private int AutoStopEmpty;

    /**
     * 逆变器告警
     */
    @ModelArguments(code = "InvertAlarm", mean = "逆变器告警", number = 75)
    private int InvertAlarm;

    /**
     * 高温告警
     */
    @ModelArguments(code = "HighTemAlarm", mean = "高温告警", number = 76)
    private int HighTemAlarm;

    /**
     * 高温降额
     */
    @ModelArguments(code = "HighTemDec", mean = "高温降额", number = 77)
    private int HighTemDec;

    /**
     * 直流绝缘异常
     */
    @ModelArguments(code = "DcInsulationANorma", mean = "直流绝缘异常", number = 78)
    private int DcInsulationANorma;

    /**
     * 绝缘监测失效
     */
    @ModelArguments(code = "InsulationMonitoringFailure", mean = "绝缘监测失效", number = 79)
    private int InsulationMonitoringFailure;

    /**
     * 漏电流超标告警
     */
    @ModelArguments(code = "ExcessiveLeakageCurrentAlarms", mean = "漏电流超标告警", number = 80)
    private int ExcessiveLeakageCurrentAlarms;


    /**
     * 模组测温元件异常
     */
    @ModelArguments(code = "ModuleTempMeaElementAnomaly", mean = "模组测温元件异常", number = 81)
    private int ModuleTempMeaElementAnomaly;

    /**
     * 风机回路异常
     */
    @ModelArguments(code = "FanAbnormalLoop", mean = "风机回路异常", number = 82)
    private int FanAbnormalLoop;

    /**
     * 逆变器分合闸失败
     */
    @ModelArguments(code = "InverterSwitchFailure", mean = "逆变器分合闸失败", number = 83)
    private int InverterSwitchFailure;

    /**
     * 采样异常
     */
    @ModelArguments(code = "SamplingAbNormal", mean = "采样异常", number = 84)
    private int SamplingAbNormal;

    /**
     * 直流电压设定异常
     */
    @ModelArguments(code = "DcVoltageSetting", mean = "直流电压设定异常", number = 85)
    private int DcVoltageSetting;

    /**
     * 充电末期状态
     */
    @ModelArguments(code = "LateChargeState", mean = "充电末期状态", number = 86)
    private int LateChargeState;

    /**
     * 放电末期状态
     */
    @ModelArguments(code = "LateDisChargeState", mean = "放电末期状态", number = 87)
    private int LateDisChargeState;

    /**
     * 开机旋钮位置
     */
    @ModelArguments(code = "BootKnobPosition", mean = "开机旋钮位置", number = 88)
    private int BootKnobPosition;

    /**
     * 关机旋钮位置
     */
    @ModelArguments(code = "PowerOffKnobPosition", mean = "关机旋钮位置", number = 89)
    private int PowerOffKnobPosition;

    /**
     * 紧急停机
     */
    @ModelArguments(code = "EmergencyStop", mean = "紧急停机", number = 90)
    private int EmergencyStop;

    /**
     * 熔断器状态
     */
    @ModelArguments(code = "FuseStatus", mean = "熔断器状态", number = 91)
    private int FuseStatus;

    /**
     * 手动合闸
     */
    @ModelArguments(code = "TheManualSwitch", mean = "手动合闸", number = 92)
    private int TheManualSwitch;

    /**
     * 手动分闸
     */
    @ModelArguments(code = "ManualBreakBrake", mean = "手动分闸", number = 93)
    private int ManualBreakBrake;

    /**
     * 交流并网接触器位置
     */
    @ModelArguments(code = "InterConContactPosition", mean = "交流并网接触器位置", number = 94)
    private int  InterConContactPosition;

    /**
     * 交流断路器位置
     */
    @ModelArguments(code = "AcBreakerPosition", mean = "交流断路器位置", number = 95)
    private int AcBreakerPosition;

    /**
     * 直流断路器位置
     */
    @ModelArguments(code = "PositionOfDcCircuitBreaker", mean = "直流断路器位置", number = 96)
    private int  PositionOfDcCircuitBreaker;

    /**
     * 直流绝缘异常开入
     */
    @ModelArguments(code = "DcInsulationFailIntoOpen", mean = "直流绝缘异常开入", number = 97)
    private int DcInsulationFailIntoOpen;

    /**
     * 直流放电接触器位置
     */
    @ModelArguments(code = "DcContactorDischargePosition", mean = "直流放电接触器位置", number = 98)
    private int DcContactorDischargePosition;

    /**
     * 散热风机启动
     */
    @ModelArguments(code = "CoolingFanStart", mean = "散热风机启动", number = 99)
    private int CoolingFanStart;

    /**
     * 辅助电源异常
     */
    @ModelArguments(code = "AuxiliaryPowerAbnormal", mean = "辅助电源异常", number = 100)
    private int AuxiliaryPowerAbnormal;


    /**
     * 预充电接触器位置
     */
    @ModelArguments(code = "ChargingContactPosition", mean = "预充电接触器位置", number = 101)
    private int ChargingContactPosition;

    /**
     * 交流防雷器空开位置
     */
    @ModelArguments(code = "CommPreLightingSwitchPosition", mean = "交流防雷器空开位置", number = 102)
    private int CommPreLightingSwitchPosition;

    /**
     * 交流防雷器异常
     */
    @ModelArguments(code = "ACCommPreLightingSwitchFail", mean = "交流防雷器异常", number = 103)
    private int ACCommPreLightingSwitchFail;

    /**
     * 直流防雷器异常
     */
    @ModelArguments(code = "DCCommPreLightingSwitchFail", mean = "交流防雷器异常", number = 104)
    private int DCCommPreLightingSwitchFail;

    /**
     * 电抗器温度开入
     */
    @ModelArguments(code = "ReactorTemperatureIntoOpen", mean = "电抗器温度开入", number = 105)
    private int ReactorTemperatureIntoOpen;

    /**
     * 充电接触器位置信号
     */
    @ModelArguments(code = "ChargingContactorPositionSignal", mean = "充电接触器位置信号", number = 106)
    private int ChargingContactorPositionSignal;

    /**
     * 自动手动模式信号
     */
    @ModelArguments(code = "ManualModeSignal", mean = "自动手动模式信号", number = 107)
    private int ManualModeSignal;

    /**
     * 变压器温度信号
     */
    @ModelArguments(code = "TransformerTemperatureSignal", mean = "变压器温度信号", number = 108)
    private int TransformerTemperatureSignal;

    /**
     * 门锁打开
     */
    @ModelArguments(code = "DoorOpen", mean = "门锁打开", number = 109)
    private int DoorOpen;

    /**
     * 紧急停运（来自外部）
     */
    @ModelArguments(code = "EmergencyShutdownFromOut", mean = "紧急停运（来自外部）", number = 110)
    private int EmergencyShutdownFromOut;

    /**
     * 逆变器PWM使能
     */
    @ModelArguments(code = "PWMInverterMake", mean = "逆变器PWM使能", number = 111)
    private int PWMInverterMake;

    /**
     * 投过流保护
     */
    @ModelArguments(code = "ForOverCurrentProtection", mean = "投过流保护", number = 112)
    private int ForOverCurrentProtection;

    /**
     * 投过欠频保护
     */
    @ModelArguments(code = "ForLowFrequencyProtection", mean = "投过欠频保护", number = 113)
    private int ForLowFrequencyProtection;

    /**
     * 投交流过欠压保护
     */
    @ModelArguments(code = "AcUnderVoltageProtection", mean = "投交流过欠压保护", number = 114)
    private int AcUnderVoltageProtection;

    /**
     * 投逆变器LVRT保护
     */
    @ModelArguments(code = "InverterLVRTProtection", mean = "投逆变器LVRT保护", number = 115)
    private int InverterLVRTProtection;

    /**
     * 投逆变器过载保护
     */
    @ModelArguments(code = "InverterOverloadProtection", mean = "投逆变器过载保护", number = 116)
    private int InverterOverloadProtection;

    /**
     * 投直流过压保护
     */
    @ModelArguments(code = "DcOverVoltageProtection", mean = "投直流过压保护", number = 117)
    private int DcOverVoltageProtection;

    /**
     * 投逆变器负序保护
     */
    @ModelArguments(code = "InverterSequencePro", mean = "投逆变器负序保护", number = 118)
    private int InverterSequencePro;

    /**
     * 投模组过温保护
     */
    @ModelArguments(code = "ModuleOverTemperaturePro", mean = "投模组过温保护", number = 119)
    private int ModuleOverTemperaturePro;

    /**
     * 投负序电流内环控制
     */
    @ModelArguments(code = "SequenceCurInnerLoopControl", mean = "投负序电流内环控制", number = 120)
    private int SequenceCurInnerLoopControl;

    /**
     * 投交流功率控制模式
     */
    @ModelArguments(code = "AcTPowerControlMode2", mean = "投交流功率控制模式", number = 121)
    private int AcTPowerControlMode2;


    /**
     * LVRT控制使能
     */
    @ModelArguments(code = "LVRTControlCanMake", mean = "LVRT控制使能", number = 122)
    private int LVRTControlCanMake;

    /**
     * 自动开关机
     */
    @ModelArguments(code = "AutoSwitch", mean = "自动开关机", number = 123)
    private int AutoSwitch;

    /**
     * SVG模式使能
     */
    @ModelArguments(code = "SVGModeCanMake", mean = "SVG模式使能", number = 124)
    private int SVGModeCanMake;

    /**
     * 投直流反接保护
     */
    @ModelArguments(code = "DcReverseConnectProtection", mean = "投直流反接保护", number = 125)
    private int DcReverseConnectProtection;

    /**
     * 投直流防反放电保护
     */
    @ModelArguments(code = "DefenceOfTheDcDischargePro2", mean = "投直流防反放电保护", number = 126)
    private int DefenceOfTheDcDischargePro2;

    /**
     * 投交流接地保护
     */
    @ModelArguments(code = "AcGroundFaultProtection", mean = "投交流接地保护", number = 127)
    private int AcGroundFaultProtection;

    /**
     * 投功率因数控制模式
     */
    @ModelArguments(code = "ThePowerFactorControlMode", mean = "投功率因数控制模式", number = 128)
    private int ThePowerFactorControlMode;

    /**
     * 投辅助电源异常保护
     */
    @ModelArguments(code = "AbnormalPowerSupplyProtection", mean = "投辅助电源异常保护", number = 129)
    private int AbnormalPowerSupplyProtection;

    /**
     * 直流电压控制模式
     */
    @ModelArguments(code = "DcVoltageControlMode2", mean = "直流电压控制模式", number = 130)
    private int DcVoltageControlMode2;

    /**
     * 交流功率控制模式
     */
    @ModelArguments(code = "AcPowerControlMode2", mean = "交流功率控制模式", number = 131)
    private int AcPowerControlMode2;

    /**
     * 电池充满
     */
    @ModelArguments(code = "BatteryFill", mean = "电池充满", number = 132)
    private int BatteryFill;

    /**
     * 电池放空
     */
    @ModelArguments(code = "DcVoltageControlMode2", mean = "电池放空", number = 133)
    private int BatteryEmpty;

    /**
     * 电池告警
     */
    @ModelArguments(code = "BatteryAlarm", mean = "电池告警", number = 134)
    private int BatteryAlarm;

    /**
     * 电池故障
     */
    @ModelArguments(code = "BatteryFail", mean = "电池故障", number = 135)
    private int BatteryFail;

    /**
     * 电池正常
     */
    @ModelArguments(code = "BatteryNormal", mean = "电池正常", number = 136)
    private int BatteryNormal;

    /**
     * BMS通讯故障
     */
    @ModelArguments(code = "BMSCommFail", mean = "BMS通讯故障", number = 137)
    private int BMSCommFail;

    public int getStartForWhole() {
        return startForWhole;
    }

    public void setStartForWhole(int startForWhole) {
        this.startForWhole = startForWhole;
    }

    public int getProtectAction() {
        return protectAction;
    }

    public void setProtectAction(int protectAction) {
        this.protectAction = protectAction;
    }

    public int getOverCurrentProtection() {
        return overCurrentProtection;
    }

    public void setOverCurrentProtection(int overCurrentProtection) {
        this.overCurrentProtection = overCurrentProtection;
    }

    public int getBCUnPro() {
        return BCUnPro;
    }

    public void setBCUnPro(int BCUnPro) {
        this.BCUnPro = BCUnPro;
    }

    public int getBCOverVPro() {
        return BCOverVPro;
    }

    public void setBCOverVPro(int BCOverVPro) {
        this.BCOverVPro = BCOverVPro;
    }

    public int getBCUnderVPro() {
        return BCUnderVPro;
    }

    public void setBCUnderVPro(int BCUnderVPro) {
        this.BCUnderVPro = BCUnderVPro;
    }

    public int getVImbalancePro() {
        return VImbalancePro;
    }

    public void setVImbalancePro(int VImbalancePro) {
        this.VImbalancePro = VImbalancePro;
    }

    public int getOverFrePro() {
        return OverFrePro;
    }

    public void setOverFrePro(int overFrePro) {
        OverFrePro = overFrePro;
    }

    public int getNotFrePro() {
        return NotFrePro;
    }

    public void setNotFrePro(int notFrePro) {
        NotFrePro = notFrePro;
    }

    public int getOverTemPro() {
        return OverTemPro;
    }

    public void setOverTemPro(int overTemPro) {
        OverTemPro = overTemPro;
    }

    public int getLowTemAtr() {
        return LowTemAtr;
    }

    public void setLowTemAtr(int lowTemAtr) {
        LowTemAtr = lowTemAtr;
    }

    public int getOverLoadPro() {
        return OverLoadPro;
    }

    public void setOverLoadPro(int overLoadPro) {
        OverLoadPro = overLoadPro;
    }

    public int getLVRTPro() {
        return LVRTPro;
    }

    public void setLVRTPro(int LVRTPro) {
        this.LVRTPro = LVRTPro;
    }

    public int getDriverPro() {
        return DriverPro;
    }

    public void setDriverPro(int driverPro) {
        DriverPro = driverPro;
    }

    public int getADriverPro() {
        return ADriverPro;
    }

    public void setADriverPro(int ADriverPro) {
        this.ADriverPro = ADriverPro;
    }

    public int getBDriverPro() {
        return BDriverPro;
    }

    public void setBDriverPro(int BDriverPro) {
        this.BDriverPro = BDriverPro;
    }

    public int getCDriverPro() {
        return CDriverPro;
    }

    public void setCDriverPro(int CDriverPro) {
        this.CDriverPro = CDriverPro;
    }

    public int getInverterInternalFault() {
        return InverterInternalFault;
    }

    public void setInverterInternalFault(int inverterInternalFault) {
        InverterInternalFault = inverterInternalFault;
    }

    public int getPulseBlock() {
        return PulseBlock;
    }

    public void setPulseBlock(int pulseBlock) {
        PulseBlock = pulseBlock;
    }

    public int getPTExPro() {
        return PTExPro;
    }

    public void setPTExPro(int PTExPro) {
        this.PTExPro = PTExPro;
    }

    public int getLeakAgeCurrentPro() {
        return LeakAgeCurrentPro;
    }

    public void setLeakAgeCurrentPro(int leakAgeCurrentPro) {
        LeakAgeCurrentPro = leakAgeCurrentPro;
    }

    public int getAuxiliaryPowerPro() {
        return AuxiliaryPowerPro;
    }

    public void setAuxiliaryPowerPro(int auxiliaryPowerPro) {
        AuxiliaryPowerPro = auxiliaryPowerPro;
    }

    public int getRemoteSettingFail() {
        return RemoteSettingFail;
    }

    public void setRemoteSettingFail(int remoteSettingFail) {
        RemoteSettingFail = remoteSettingFail;
    }

    public int getModeCheckFail() {
        return ModeCheckFail;
    }

    public void setModeCheckFail(int modeCheckFail) {
        ModeCheckFail = modeCheckFail;
    }

    public int getFailStartInvertCommand() {
        return FailStartInvertCommand;
    }

    public void setFailStartInvertCommand(int failStartInvertCommand) {
        FailStartInvertCommand = failStartInvertCommand;
    }

    public int getAutoStartInvertCommand() {
        return AutoStartInvertCommand;
    }

    public void setAutoStartInvertCommand(int autoStartInvertCommand) {
        AutoStartInvertCommand = autoStartInvertCommand;
    }

    public int getManualStartInvertCommand() {
        return ManualStartInvertCommand;
    }

    public void setManualStartInvertCommand(int manualStartInvertCommand) {
        ManualStartInvertCommand = manualStartInvertCommand;
    }

    public int getInvertNetCon() {
        return InvertNetCon;
    }

    public void setInvertNetCon(int invertNetCon) {
        InvertNetCon = invertNetCon;
    }

    public int getBlackStartCon() {
        return BlackStartCon;
    }

    public void setBlackStartCon(int blackStartCon) {
        BlackStartCon = blackStartCon;
    }

    public int getLVRTFunSwitch() {
        return LVRTFunSwitch;
    }

    public void setLVRTFunSwitch(int LVRTFunSwitch) {
        this.LVRTFunSwitch = LVRTFunSwitch;
    }

    public int getIntoTriggerWave() {
        return IntoTriggerWave;
    }

    public void setIntoTriggerWave(int intoTriggerWave) {
        IntoTriggerWave = intoTriggerWave;
    }

    public int getRemoteTriggerWave() {
        return RemoteTriggerWave;
    }

    public void setRemoteTriggerWave(int remoteTriggerWave) {
        RemoteTriggerWave = remoteTriggerWave;
    }

    public int getManualTriggerWave() {
        return ManualTriggerWave;
    }

    public void setManualTriggerWave(int manualTriggerWave) {
        ManualTriggerWave = manualTriggerWave;
    }

    public int getWaveRecordProStart() {
        return WaveRecordProStart;
    }

    public void setWaveRecordProStart(int waveRecordProStart) {
        WaveRecordProStart = waveRecordProStart;
    }

    public int getDeviceAlarm() {
        return DeviceAlarm;
    }

    public void setDeviceAlarm(int deviceAlarm) {
        DeviceAlarm = deviceAlarm;
    }

    public int getOverVoltageProtection() {
        return overVoltageProtection;
    }

    public void setOverVoltageProtection(int overVoltageProtection) {
        this.overVoltageProtection = overVoltageProtection;
    }

    public int getUnderVoltageProtection() {
        return underVoltageProtection;
    }

    public void setUnderVoltageProtection(int underVoltageProtection) {
        this.underVoltageProtection = underVoltageProtection;
    }

    public int getDCReverseVoltageProtection() {
        return DCReverseVoltageProtection;
    }

    public void setDCReverseVoltageProtection(int DCReverseVoltageProtection) {
        this.DCReverseVoltageProtection = DCReverseVoltageProtection;
    }

    public int getDefenceDcDischargePro() {
        return DefenceDcDischargePro;
    }

    public void setDefenceDcDischargePro(int defenceDcDischargePro) {
        DefenceDcDischargePro = defenceDcDischargePro;
    }

    public int getMergeNetContactWaveRecord() {
        return MergeNetContactWaveRecord;
    }

    public void setMergeNetContactWaveRecord(int mergeNetContactWaveRecord) {
        MergeNetContactWaveRecord = mergeNetContactWaveRecord;
    }

    public int getDeviceClosure() {
        return DeviceClosure;
    }

    public void setDeviceClosure(int deviceClosure) {
        DeviceClosure = deviceClosure;
    }

    public int getALightCoupingPower() {
        return ALightCoupingPower;
    }

    public void setALightCoupingPower(int ALightCoupingPower) {
        this.ALightCoupingPower = ALightCoupingPower;
    }

    public int getConstantValueValidError() {
        return ConstantValueValidError;
    }

    public void setConstantValueValidError(int constantValueValidError) {
        ConstantValueValidError = constantValueValidError;
    }

    public int getConstantValueToBeConfirmed() {
        return ConstantValueToBeConfirmed;
    }

    public void setConstantValueToBeConfirmed(int constantValueToBeConfirmed) {
        ConstantValueToBeConfirmed = constantValueToBeConfirmed;
    }

    public int getBoardConfigurationErrors() {
        return BoardConfigurationErrors;
    }

    public void setBoardConfigurationErrors(int boardConfigurationErrors) {
        BoardConfigurationErrors = boardConfigurationErrors;
    }

    public int getVersionFailAlarm() {
        return VersionFailAlarm;
    }

    public void setVersionFailAlarm(int versionFailAlarm) {
        VersionFailAlarm = versionFailAlarm;
    }

    public int getConstantBeyValue() {
        return ConstantBeyValue;
    }

    public void setConstantBeyValue(int constantBeyValue) {
        ConstantBeyValue = constantBeyValue;
    }

    public int getConstantChange() {
        return ConstantChange;
    }

    public void setConstantChange(int constantChange) {
        ConstantChange = constantChange;
    }

    public int getCommunicationTranAlarm() {
        return CommunicationTranAlarm;
    }

    public void setCommunicationTranAlarm(int communicationTranAlarm) {
        CommunicationTranAlarm = communicationTranAlarm;
    }

    public int getConstantValueArea() {
        return ConstantValueArea;
    }

    public void setConstantValueArea(int constantValueArea) {
        ConstantValueArea = constantValueArea;
    }

    public int getRemoteControlMake() {
        return RemoteControlMake;
    }

    public void setRemoteControlMake(int remoteControlMake) {
        RemoteControlMake = remoteControlMake;
    }

    public int getBMSControlMake() {
        return BMSControlMake;
    }

    public void setBMSControlMake(int BMSControlMake) {
        this.BMSControlMake = BMSControlMake;
    }

    public int getStartCommand() {
        return StartCommand;
    }

    public void setStartCommand(int startCommand) {
        StartCommand = startCommand;
    }

    public int getStopCommand() {
        return StopCommand;
    }

    public void setStopCommand(int stopCommand) {
        StopCommand = stopCommand;
    }

    public int getAutoSwitchMac() {
        return AutoSwitchMac;
    }

    public void setAutoSwitchMac(int autoSwitchMac) {
        AutoSwitchMac = autoSwitchMac;
    }

    public int getBlackStartCommand() {
        return BlackStartCommand;
    }

    public void setBlackStartCommand(int blackStartCommand) {
        BlackStartCommand = blackStartCommand;
    }

    public int getDcVoltageControlMode() {
        return DcVoltageControlMode;
    }

    public void setDcVoltageControlMode(int dcVoltageControlMode) {
        DcVoltageControlMode = dcVoltageControlMode;
    }

    public int getDcCurrentControlMode() {
        return DcCurrentControlMode;
    }

    public void setDcCurrentControlMode(int dcCurrentControlMode) {
        DcCurrentControlMode = dcCurrentControlMode;
    }

    public int getDcPowerControlMode() {
        return DcPowerControlMode;
    }

    public void setDcPowerControlMode(int dcPowerControlMode) {
        DcPowerControlMode = dcPowerControlMode;
    }

    public int getAcPowerControlMode() {
        return AcPowerControlMode;
    }

    public void setAcPowerControlMode(int acPowerControlMode) {
        AcPowerControlMode = acPowerControlMode;
    }

    public int getPowerFactorControlMode() {
        return PowerFactorControlMode;
    }

    public void setPowerFactorControlMode(int powerFactorControlMode) {
        PowerFactorControlMode = powerFactorControlMode;
    }

    public int getVSGControlMode() {
        return VSGControlMode;
    }

    public void setVSGControlMode(int VSGControlMode) {
        this.VSGControlMode = VSGControlMode;
    }

    public int getVFControlMode() {
        return VFControlMode;
    }

    public void setVFControlMode(int VFControlMode) {
        this.VFControlMode = VFControlMode;
    }

    public int getCastFillingPutEndVoltageMod() {
        return CastFillingPutEndVoltageMod;
    }

    public void setCastFillingPutEndVoltageMod(int castFillingPutEndVoltageMod) {
        CastFillingPutEndVoltageMod = castFillingPutEndVoltageMod;
    }

    public int getForVirtualImpedance() {
        return ForVirtualImpedance;
    }

    public void setForVirtualImpedance(int forVirtualImpedance) {
        ForVirtualImpedance = forVirtualImpedance;
    }

    public int getNeSeVoltageControl() {
        return NeSeVoltageControl;
    }

    public void setNeSeVoltageControl(int neSeVoltageControl) {
        NeSeVoltageControl = neSeVoltageControl;
    }

    public int getRunNormal() {
        return RunNormal;
    }

    public void setRunNormal(int runNormal) {
        RunNormal = runNormal;
    }

    public int getRunAlarm() {
        return RunAlarm;
    }

    public void setRunAlarm(int runAlarm) {
        RunAlarm = runAlarm;
    }

    public int getStandByStatus() {
        return StandByStatus;
    }

    public void setStandByStatus(int standByStatus) {
        StandByStatus = standByStatus;
    }

    public int getNormalDown() {
        return NormalDown;
    }

    public void setNormalDown(int normalDown) {
        NormalDown = normalDown;
    }

    public int getBreakDown() {
        return BreakDown;
    }

    public void setBreakDown(int breakDown) {
        BreakDown = breakDown;
    }

    public int getAutoStopUntilFill() {
        return AutoStopUntilFill;
    }

    public void setAutoStopUntilFill(int autoStopUntilFill) {
        AutoStopUntilFill = autoStopUntilFill;
    }

    public int getAutoStopEmpty() {
        return AutoStopEmpty;
    }

    public void setAutoStopEmpty(int autoStopEmpty) {
        AutoStopEmpty = autoStopEmpty;
    }

    public int getInvertAlarm() {
        return InvertAlarm;
    }

    public void setInvertAlarm(int invertAlarm) {
        InvertAlarm = invertAlarm;
    }

    public int getHighTemAlarm() {
        return HighTemAlarm;
    }

    public void setHighTemAlarm(int highTemAlarm) {
        HighTemAlarm = highTemAlarm;
    }

    public int getHighTemDec() {
        return HighTemDec;
    }

    public void setHighTemDec(int highTemDec) {
        HighTemDec = highTemDec;
    }

    public int getDcInsulationANorma() {
        return DcInsulationANorma;
    }

    public void setDcInsulationANorma(int dcInsulationANorma) {
        DcInsulationANorma = dcInsulationANorma;
    }

    public int getInsulationMonitoringFailure() {
        return InsulationMonitoringFailure;
    }

    public void setInsulationMonitoringFailure(int insulationMonitoringFailure) {
        InsulationMonitoringFailure = insulationMonitoringFailure;
    }

    public int getExcessiveLeakageCurrentAlarms() {
        return ExcessiveLeakageCurrentAlarms;
    }

    public void setExcessiveLeakageCurrentAlarms(int excessiveLeakageCurrentAlarms) {
        ExcessiveLeakageCurrentAlarms = excessiveLeakageCurrentAlarms;
    }

    public int getModuleTempMeaElementAnomaly() {
        return ModuleTempMeaElementAnomaly;
    }

    public void setModuleTempMeaElementAnomaly(int moduleTempMeaElementAnomaly) {
        ModuleTempMeaElementAnomaly = moduleTempMeaElementAnomaly;
    }

    public int getFanAbnormalLoop() {
        return FanAbnormalLoop;
    }

    public void setFanAbnormalLoop(int fanAbnormalLoop) {
        FanAbnormalLoop = fanAbnormalLoop;
    }

    public int getInverterSwitchFailure() {
        return InverterSwitchFailure;
    }

    public void setInverterSwitchFailure(int inverterSwitchFailure) {
        InverterSwitchFailure = inverterSwitchFailure;
    }

    public int getSamplingAbNormal() {
        return SamplingAbNormal;
    }

    public void setSamplingAbNormal(int samplingAbNormal) {
        SamplingAbNormal = samplingAbNormal;
    }

    public int getDcVoltageSetting() {
        return DcVoltageSetting;
    }

    public void setDcVoltageSetting(int dcVoltageSetting) {
        DcVoltageSetting = dcVoltageSetting;
    }

    public int getLateChargeState() {
        return LateChargeState;
    }

    public void setLateChargeState(int lateChargeState) {
        LateChargeState = lateChargeState;
    }

    public int getLateDisChargeState() {
        return LateDisChargeState;
    }

    public void setLateDisChargeState(int lateDisChargeState) {
        LateDisChargeState = lateDisChargeState;
    }

    public int getBootKnobPosition() {
        return BootKnobPosition;
    }

    public void setBootKnobPosition(int bootKnobPosition) {
        BootKnobPosition = bootKnobPosition;
    }

    public int getPowerOffKnobPosition() {
        return PowerOffKnobPosition;
    }

    public void setPowerOffKnobPosition(int powerOffKnobPosition) {
        PowerOffKnobPosition = powerOffKnobPosition;
    }

    public int getEmergencyStop() {
        return EmergencyStop;
    }

    public void setEmergencyStop(int emergencyStop) {
        EmergencyStop = emergencyStop;
    }

    public int getFuseStatus() {
        return FuseStatus;
    }

    public void setFuseStatus(int fuseStatus) {
        FuseStatus = fuseStatus;
    }

    public int getTheManualSwitch() {
        return TheManualSwitch;
    }

    public void setTheManualSwitch(int theManualSwitch) {
        TheManualSwitch = theManualSwitch;
    }

    public int getManualBreakBrake() {
        return ManualBreakBrake;
    }

    public void setManualBreakBrake(int manualBreakBrake) {
        ManualBreakBrake = manualBreakBrake;
    }

    public int getInterConContactPosition() {
        return InterConContactPosition;
    }

    public void setInterConContactPosition(int interConContactPosition) {
        InterConContactPosition = interConContactPosition;
    }

    public int getAcBreakerPosition() {
        return AcBreakerPosition;
    }

    public void setAcBreakerPosition(int acBreakerPosition) {
        AcBreakerPosition = acBreakerPosition;
    }

    public int getPositionOfDcCircuitBreaker() {
        return PositionOfDcCircuitBreaker;
    }

    public void setPositionOfDcCircuitBreaker(int positionOfDcCircuitBreaker) {
        PositionOfDcCircuitBreaker = positionOfDcCircuitBreaker;
    }

    public int getDcInsulationFailIntoOpen() {
        return DcInsulationFailIntoOpen;
    }

    public void setDcInsulationFailIntoOpen(int dcInsulationFailIntoOpen) {
        DcInsulationFailIntoOpen = dcInsulationFailIntoOpen;
    }

    public int getDcContactorDischargePosition() {
        return DcContactorDischargePosition;
    }

    public void setDcContactorDischargePosition(int dcContactorDischargePosition) {
        DcContactorDischargePosition = dcContactorDischargePosition;
    }

    public int getCoolingFanStart() {
        return CoolingFanStart;
    }

    public void setCoolingFanStart(int coolingFanStart) {
        CoolingFanStart = coolingFanStart;
    }

    public int getAuxiliaryPowerAbnormal() {
        return AuxiliaryPowerAbnormal;
    }

    public void setAuxiliaryPowerAbnormal(int auxiliaryPowerAbnormal) {
        AuxiliaryPowerAbnormal = auxiliaryPowerAbnormal;
    }

    public int getChargingContactPosition() {
        return ChargingContactPosition;
    }

    public void setChargingContactPosition(int chargingContactPosition) {
        ChargingContactPosition = chargingContactPosition;
    }

    public int getCommPreLightingSwitchPosition() {
        return CommPreLightingSwitchPosition;
    }

    public void setCommPreLightingSwitchPosition(int commPreLightingSwitchPosition) {
        CommPreLightingSwitchPosition = commPreLightingSwitchPosition;
    }

    public int getACCommPreLightingSwitchFail() {
        return ACCommPreLightingSwitchFail;
    }

    public void setACCommPreLightingSwitchFail(int ACCommPreLightingSwitchFail) {
        this.ACCommPreLightingSwitchFail = ACCommPreLightingSwitchFail;
    }

    public int getDCCommPreLightingSwitchFail() {
        return DCCommPreLightingSwitchFail;
    }

    public void setDCCommPreLightingSwitchFail(int DCCommPreLightingSwitchFail) {
        this.DCCommPreLightingSwitchFail = DCCommPreLightingSwitchFail;
    }

    public int getReactorTemperatureIntoOpen() {
        return ReactorTemperatureIntoOpen;
    }

    public void setReactorTemperatureIntoOpen(int reactorTemperatureIntoOpen) {
        ReactorTemperatureIntoOpen = reactorTemperatureIntoOpen;
    }

    public int getChargingContactorPositionSignal() {
        return ChargingContactorPositionSignal;
    }

    public void setChargingContactorPositionSignal(int chargingContactorPositionSignal) {
        ChargingContactorPositionSignal = chargingContactorPositionSignal;
    }

    public int getManualModeSignal() {
        return ManualModeSignal;
    }

    public void setManualModeSignal(int manualModeSignal) {
        ManualModeSignal = manualModeSignal;
    }

    public int getTransformerTemperatureSignal() {
        return TransformerTemperatureSignal;
    }

    public void setTransformerTemperatureSignal(int transformerTemperatureSignal) {
        TransformerTemperatureSignal = transformerTemperatureSignal;
    }

    public int getDoorOpen() {
        return DoorOpen;
    }

    public void setDoorOpen(int doorOpen) {
        DoorOpen = doorOpen;
    }

    public int getEmergencyShutdownFromOut() {
        return EmergencyShutdownFromOut;
    }

    public void setEmergencyShutdownFromOut(int emergencyShutdownFromOut) {
        EmergencyShutdownFromOut = emergencyShutdownFromOut;
    }

    public int getPWMInverterMake() {
        return PWMInverterMake;
    }

    public void setPWMInverterMake(int PWMInverterMake) {
        this.PWMInverterMake = PWMInverterMake;
    }

    public int getForOverCurrentProtection() {
        return ForOverCurrentProtection;
    }

    public void setForOverCurrentProtection(int forOverCurrentProtection) {
        ForOverCurrentProtection = forOverCurrentProtection;
    }

    public int getForLowFrequencyProtection() {
        return ForLowFrequencyProtection;
    }

    public void setForLowFrequencyProtection(int forLowFrequencyProtection) {
        ForLowFrequencyProtection = forLowFrequencyProtection;
    }

    public int getAcUnderVoltageProtection() {
        return AcUnderVoltageProtection;
    }

    public void setAcUnderVoltageProtection(int acUnderVoltageProtection) {
        AcUnderVoltageProtection = acUnderVoltageProtection;
    }

    public int getInverterLVRTProtection() {
        return InverterLVRTProtection;
    }

    public void setInverterLVRTProtection(int inverterLVRTProtection) {
        InverterLVRTProtection = inverterLVRTProtection;
    }

    public int getInverterOverloadProtection() {
        return InverterOverloadProtection;
    }

    public void setInverterOverloadProtection(int inverterOverloadProtection) {
        InverterOverloadProtection = inverterOverloadProtection;
    }

    public int getDcOverVoltageProtection() {
        return DcOverVoltageProtection;
    }

    public void setDcOverVoltageProtection(int dcOverVoltageProtection) {
        DcOverVoltageProtection = dcOverVoltageProtection;
    }

    public int getInverterSequencePro() {
        return InverterSequencePro;
    }

    public void setInverterSequencePro(int inverterSequencePro) {
        InverterSequencePro = inverterSequencePro;
    }

    public int getModuleOverTemperaturePro() {
        return ModuleOverTemperaturePro;
    }

    public void setModuleOverTemperaturePro(int moduleOverTemperaturePro) {
        ModuleOverTemperaturePro = moduleOverTemperaturePro;
    }

    public int getSequenceCurInnerLoopControl() {
        return SequenceCurInnerLoopControl;
    }

    public void setSequenceCurInnerLoopControl(int sequenceCurInnerLoopControl) {
        SequenceCurInnerLoopControl = sequenceCurInnerLoopControl;
    }

    public int getAcTPowerControlMode2() {
        return AcTPowerControlMode2;
    }

    public void setAcTPowerControlMode2(int acTPowerControlMode2) {
        AcTPowerControlMode2 = acTPowerControlMode2;
    }

    public int getLVRTControlCanMake() {
        return LVRTControlCanMake;
    }

    public void setLVRTControlCanMake(int LVRTControlCanMake) {
        this.LVRTControlCanMake = LVRTControlCanMake;
    }

    public int getAutoSwitch() {
        return AutoSwitch;
    }

    public void setAutoSwitch(int autoSwitch) {
        AutoSwitch = autoSwitch;
    }

    public int getSVGModeCanMake() {
        return SVGModeCanMake;
    }

    public void setSVGModeCanMake(int SVGModeCanMake) {
        this.SVGModeCanMake = SVGModeCanMake;
    }

    public int getDcReverseConnectProtection() {
        return DcReverseConnectProtection;
    }

    public void setDcReverseConnectProtection(int dcReverseConnectProtection) {
        DcReverseConnectProtection = dcReverseConnectProtection;
    }

    public int getDefenceOfTheDcDischargePro2() {
        return DefenceOfTheDcDischargePro2;
    }

    public void setDefenceOfTheDcDischargePro2(int defenceOfTheDcDischargePro2) {
        DefenceOfTheDcDischargePro2 = defenceOfTheDcDischargePro2;
    }

    public int getAcGroundFaultProtection() {
        return AcGroundFaultProtection;
    }

    public void setAcGroundFaultProtection(int acGroundFaultProtection) {
        AcGroundFaultProtection = acGroundFaultProtection;
    }

    public int getThePowerFactorControlMode() {
        return ThePowerFactorControlMode;
    }

    public void setThePowerFactorControlMode(int thePowerFactorControlMode) {
        ThePowerFactorControlMode = thePowerFactorControlMode;
    }

    public int getAbnormalPowerSupplyProtection() {
        return AbnormalPowerSupplyProtection;
    }

    public void setAbnormalPowerSupplyProtection(int abnormalPowerSupplyProtection) {
        AbnormalPowerSupplyProtection = abnormalPowerSupplyProtection;
    }

    public int getDcVoltageControlMode2() {
        return DcVoltageControlMode2;
    }

    public void setDcVoltageControlMode2(int dcVoltageControlMode2) {
        DcVoltageControlMode2 = dcVoltageControlMode2;
    }

    public int getAcPowerControlMode2() {
        return AcPowerControlMode2;
    }

    public void setAcPowerControlMode2(int acPowerControlMode2) {
        AcPowerControlMode2 = acPowerControlMode2;
    }

    public int getBatteryFill() {
        return BatteryFill;
    }

    public void setBatteryFill(int batteryFill) {
        BatteryFill = batteryFill;
    }

    public int getBatteryEmpty() {
        return BatteryEmpty;
    }

    public void setBatteryEmpty(int batteryEmpty) {
        BatteryEmpty = batteryEmpty;
    }

    public int getBatteryAlarm() {
        return BatteryAlarm;
    }

    public void setBatteryAlarm(int batteryAlarm) {
        BatteryAlarm = batteryAlarm;
    }

    public int getBatteryFail() {
        return BatteryFail;
    }

    public void setBatteryFail(int batteryFail) {
        BatteryFail = batteryFail;
    }

    public int getBatteryNormal() {
        return BatteryNormal;
    }

    public void setBatteryNormal(int batteryNormal) {
        BatteryNormal = batteryNormal;
    }

    public int getBMSCommFail() {
        return BMSCommFail;
    }

    public void setBMSCommFail(int BMSCommFail) {
        this.BMSCommFail = BMSCommFail;
    }
}
