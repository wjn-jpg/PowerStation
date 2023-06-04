package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor;


import com.ntdq.power_station.nettyHandler.tcp.nbt33007.constant.Nbt33007FrameTypeConstant;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.response.*;

/**
 * @Description : NBT33007接收消息策略管理
 * @Author : Kang
 * @Date : 2023/4/21 9:34
 * @Version : 1.0
 */
public class ReceiveMessageStrategyManager {

    public static ReceiveMessageStrategy getMessageStrategy(byte frameType){
        switch (frameType){
            case Nbt33007FrameTypeConstant.TOTAL_CALL:
                return new TotalCallMessageStrategy();
            case Nbt33007FrameTypeConstant.FULL_YC:
                return new FullYCMessageStrategy();
            case Nbt33007FrameTypeConstant.FULL_YX:
                return new FullYXMessageStrategy();
            case Nbt33007FrameTypeConstant.FULL_YM:
                return new FullYMMessageStrategy();
            case Nbt33007FrameTypeConstant.EXPAND_YM:
                return new ExpandYMMessageStrategy();
            case Nbt33007FrameTypeConstant.FAULT_CODE:
                return new FaultCodeMessageStrategy();
            case Nbt33007FrameTypeConstant.FULL_YC_EXPAND:
                return new FullYCExpandMessageStrategy();
            case Nbt33007FrameTypeConstant.CHANGE_YX:
                return new ChangeYXMessageStrategy();
            case Nbt33007FrameTypeConstant.START_STOP_CONTROL:
                return new StartStopControlMessageStrategy();
            case Nbt33007FrameTypeConstant.CHARGING_RECORD:
                return new ChargingRecordMessageStrategy();
            case Nbt33007FrameTypeConstant.CHARGING_REQUEST:
                return new ChargingRequestMessageStrategy();
            case Nbt33007FrameTypeConstant.DEDUCTION_VALUE:
                return new DeductionValueMessageStrategy();
            case Nbt33007FrameTypeConstant.POWER_REGULATION:
                return new PowerRegulationMessageStrategy();
            case Nbt33007FrameTypeConstant.ON_TIME:
                return new OnTimeMessageStrategy();
            case Nbt33007FrameTypeConstant.CHANGE_YC:
                return new ChangeYCMessageStrategy();
            case Nbt33007FrameTypeConstant.CHANGE_YC_EXPAND:
                return new ChangeYCExpandMessageStrategy();
            case Nbt33007FrameTypeConstant.CARD_INFO_QUERY:
                return new CardInfoQueryMessageStrategy();
            case Nbt33007FrameTypeConstant.CHARGING_VEHICLE_VIN:
                return new ChargingVehicleVinMessageStrategy();
            case Nbt33007FrameTypeConstant.BMS_CHARGING_NEED:
                return new BmsChargingNeedMessageStrategy();
            case Nbt33007FrameTypeConstant.BMS_CHARGING_TOTAL_STATUS:
                return new BmsChargingTotalStatusMessageStrategy();
            case Nbt33007FrameTypeConstant.BMS_SINGLE_BATTERY_INFO:
                return new BmsSingleBatteryInfoMessageStrategy();
            case Nbt33007FrameTypeConstant.ORDERLY_CHARGING_CURVE:
                return new OrderlyChargingCurveMessageStrategy();
            case Nbt33007FrameTypeConstant.SUPER_CHARGING:
                return new SuperChargingMessageStrategy();
            case Nbt33007FrameTypeConstant.BILLING_MODEL:
                return new BillingModelMessageStrategy();
            case Nbt33007FrameTypeConstant.FAULT_ALARM_DETAILS:
                return new FaultAlarmDetailsMessageStrategy();
            case Nbt33007FrameTypeConstant.YK:
                return new YKMessageStrategy();
            case Nbt33007FrameTypeConstant.VEHICLE_SELF_NUMBER_START_CHARGING:
                return new VehicleSelfNumberStartChargingMessageStrategy();
            case Nbt33007FrameTypeConstant.FIRMWARE_VERSION:
                return new FirmwareVersionMessageStrategy();
            case Nbt33007FrameTypeConstant.CHARGING_PILE_ARCHIVES_INFO:
                return new ChargingPileArchivesMessageStrategy();
            default:
                return null;
        }
    }
}
