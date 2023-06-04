package com.ntdq.power_station.nettyHandler.tcp.nbt33007.constant;

/**
 * @Description : NBT33007帧类型常量封装
 * @Author : Kang
 * @Date : 2023/4/19 11:37
 * @Version : 1.0
 */
public class Nbt33007FrameTypeConstant {

    /**
     * 主站下发总召唤命令
     */
    public static final byte TOTAL_CALL = 0x03;

    /**
     * 上送全遥测
     */
    public static final byte FULL_YC = 0x0B;

    /**
     * 上送全遥信
     */
    public static final byte FULL_YX = 0x05;

    /**
     * 上送全遥脉
     */
    public static final byte FULL_YM = 0x10;

    /**
     * 上送扩展遥脉
     */
    public static final byte EXPAND_YM = 0x11;

    /**
     * 上送故障代码
     */
    public static final byte FAULT_CODE = 0x12;

    /**
     * 上送全遥测（扩展 4 字节遥测）
     */
    public static final byte FULL_YC_EXPAND = 0x5B;

    /**
     * 主动上送变化遥信
     */
    public static final byte CHANGE_YX = 0x15;

    /**
     * 充电预约
     */
    public static final byte CHARGING_RESERVE = (byte) 0x93;

    /**
     * 充电启停控制
     */
    public static final byte START_STOP_CONTROL = 0x41;

    /**
     * 上送充电记录
     */
    public static final byte CHARGING_RECORD = 0x42;

    /**
     * 刷卡充电请求
     */
    public static final byte CHARGING_REQUEST = (byte) 0x94;

    /**
     * 充电桩返回控制执行结果： - 充电卡扣值 - 帧类型，0x96—卡扣值执行结果
     */
    public static final byte DEDUCTION_VALUE = (byte) 0x96;

    /**
     * 主站下发扣值命令 - 充电卡扣值 - 帧类型，0x96—卡扣值执行结果
     */
    public static final byte MASTER_DEDUCTION_VALUE = (byte) 0x95;

    /**
     * 充电机功率调控
     */
    public static final byte POWER_REGULATION = 0x2E;

    /**
     * 对时
     */
    public static final byte ON_TIME = 0x02;

    /**
     * 变化遥测
     */
    public static final byte CHANGE_YC = 0x1A;

    /**
     * 变化遥测（扩展 4 字节遥测）
     */
    public static final byte CHANGE_YC_EXPAND = 0x5A;

    /**
     * 卡信息查询
     */
    public static final byte CARD_INFO_QUERY = 0x13;

    /**
     *  上送充电车辆 VIN
     */
    public static final byte CHARGING_VEHICLE_VIN = 0x2C;

    /**
     * 上送 BMS 充电需求
     */
    public static final byte BMS_CHARGING_NEED = 0x30;

    /**
     * 上送 BMS 充电总状态
     */
    public static final byte BMS_CHARGING_TOTAL_STATUS = 0x31;

    /**
     * 上送 BMS 单体电池信息
     */
    public static final byte BMS_SINGLE_BATTERY_INFO = 0x32;

    /**
     * 下发有序充电曲线
     */
    public static final byte ORDERLY_CHARGING_CURVE = 0x33;

    /**
     * 超级充电临时增加功能
     */
    public static final byte SUPER_CHARGING = 0x39;

    /**
     * 计费模型下发
     */
    public static final byte BILLING_MODEL = 0x50;

    /**
     * 主动上送故障告警详情
     */
    public static final byte FAULT_ALARM_DETAILS = 0x62;

    /**
     * 遥控
     */
    public static final byte YK = 0x2D;

    /**
     * 车辆自编号启动充电
     */
    public static final byte VEHICLE_SELF_NUMBER_START_CHARGING = 0x2F;

    /**
     * 请求充电桩档案信息
     */
    public static final byte CHARGING_PILE_ARCHIVES_INFO = 0x71;

    /**
     * 主站主动下发固件版本
     */
    public static final byte FIRMWARE_VERSION = 0x72;

}
