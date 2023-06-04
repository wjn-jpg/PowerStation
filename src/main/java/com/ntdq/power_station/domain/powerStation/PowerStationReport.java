package com.ntdq.power_station.domain.powerStation;

import cn.hutool.core.util.ByteUtil;
import com.ntdq.power_station.IController.PowerController;
import com.ntdq.power_station.util.CommonByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PowerStationReport extends ByteMessage {

    private static final Logger logger = LoggerFactory.getLogger(PowerStationReport.class);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    //修改
    private static final Map<String, Double> IP_POWER_MAP = new ConcurrentHashMap<>(256);

    /**
     * 报文头
     */
    private byte header;

    /**
     * 报文长度 不含报文头和长度本身3个字节
     */
    private short length;

    /**
     * 接入方
     */
    private String deviceClient;

    /**
     * 源地址
     */
    private int sourceAddr;

    /**
     * 目标地址
     */
    private int targetAddr;

    /**
     * 遥信0
     *
     * @param byteData
     */
    private byte[] YX0;

    /**
     * 遥信1
     */
    private byte[] YX1;

    /**
     * 遥信2
     */
    private byte[] YX2;

    /**
     * 遥信3
     */
    private byte[] YX3;

    /**
     * 遥信4
     */
    private byte[] YX4;

    /**
     * 遥信5
     */
    private byte[] YX5;

    /**
     * 充电输出电压
     */
    private double ChargingOutputVoltage;

    /**
     * 充电是正的
     * 放电是负的 -250 - +250
     * 充电输出电流 -4000 * 0.1
     */
    private double ChargingOutputCurrent;

    /**
     * SOC
     */
    private short SOC;

    /**
     * 电池组最低温度 -50
     */
    private short MinimumBatteryTemperature;

    /**
     * 电池组最高温度 -50
     */
    private short MaximumBatteryTemperature;

    /**
     * 单体电池最高电压
     */
    private double MaximumVoltageSingleBattery;

    /**
     * 单体电池最低电压
     */
    private double MinimumVoltageSingleBattery;

    /**
     * 充电导引电压
     */
    private double ChargingPilotVoltage;

    /**
     * BMS需求电压
     */
    private double BMSDemandVoltage;

    /**
     * BMS需求电流 -4000 350*0.1  -35
     */
    private double BMSDemandCurrent;

    /**
     * 预留
     */
    private int reserve;

    /**
     * 功率调节指令类型
     */
    private int PowerRegulationInstructionType;

    /**
     * 功率调节参数
     */
    private short PowerRegulationParameters;

    /**
     * 校验和
     */
    private byte check;

    /**
     * 结束标志
     */
    private byte finishFlag;

    private PowerStationReport(byte[] byteData) {
        super(byteData);
    }

    public static PowerStationReport build(byte[] byteData) {
        return new PowerStationReport(byteData);
    }

    @Override
    public void parseByteData() {
        logger.info("进入到parseByteData方法 此时的字节数据size为:{}", getByteData().length);
        byte[] byteData = getByteData();
        //68 23 00 DC 01 08 00 00 00 24 13 00 00 A0 0F 00 00 32 00 32 00 00 00 00 00 8E 01 00 00 00 00 00 00 00 00 00 00 00
        try {
            int index = 0;
            header = byteData[index++];
            byte[] temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            length = ByteUtil.bytesToShort(temp);

            temp = new byte[1];
            temp[0] = byteData[index++];
            targetAddr = CommonByteUtils.ByteArrToInteger(temp);

            temp = new byte[1];
            temp[0] = byteData[index++];
            sourceAddr = CommonByteUtils.ByteArrToInteger(temp);

            YX0 = CommonByteUtils.getBooleanArray(byteData[index++]);
            YX1 = CommonByteUtils.getBooleanArray(byteData[index++]);
            YX2 = CommonByteUtils.getBooleanArray(byteData[index++]);
            YX3 = CommonByteUtils.getBooleanArray(byteData[index++]);
            YX4 = CommonByteUtils.getBooleanArray(byteData[index++]);
            YX5 = CommonByteUtils.getBooleanArray(byteData[index++]);

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            ChargingOutputVoltage = ByteUtil.bytesToShort(temp) * 0.1;
            //A0 0F
            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            ChargingOutputCurrent = (ByteUtil.bytesToShort(temp) - 4000) * 0.1;

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            SOC = ByteUtil.bytesToShort(temp);

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            MinimumBatteryTemperature = ByteUtil.bytesToShort(temp);

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            MaximumBatteryTemperature = ByteUtil.bytesToShort(temp);

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            MaximumVoltageSingleBattery = ByteUtil.bytesToShort(temp) * 0.01;

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            MinimumVoltageSingleBattery = ByteUtil.bytesToShort(temp) * 0.01;

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            ChargingPilotVoltage = ByteUtil.bytesToShort(temp) * 0.01;

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            BMSDemandVoltage = ByteUtil.bytesToShort(temp) * 0.1;

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            BMSDemandCurrent = (ByteUtil.bytesToShort(temp) - 4000) * 0.1;

            temp = new byte[2];
            for (int i = 1; i >= 0; i--) {
                temp[i] = byteData[index++];
            }
            reserve = ByteUtil.bytesToShort(temp);

            temp = new byte[1];
            temp[0] = byteData[index++];
            PowerRegulationInstructionType = CommonByteUtils.ByteArrToInteger(temp);

            temp = new byte[2];
            for (int i = 0; i < 2; i++) {
                temp[i] = byteData[index++];
            }
            PowerRegulationParameters = ByteUtil.bytesToShort(temp);

            //修改!
            if (isTheSamePower() && PowerController.resetPowerModifyState()) {
                logger.info("解析数据为修改功率成功确认返回! 退出解析方法!");
                return;
            }

            check = byteData[index++];
            finishFlag = byteData[index++];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.info("收到的数据长度不完整...");
        }
    }

    private boolean isTheSamePower(){
        Double theSourcePower = IP_POWER_MAP.get(String.valueOf(sourceAddr));
        if (theSourcePower==null){
            return false;
        }
        //将theSourcePower 用户传进来的功率做转化 对比 PowerRegulationParameters是否相同 相同为true
        //TODO
        return true;
    }

    //修改!
    public static void storeAddrPower(String ip, double power) {
        IP_POWER_MAP.put(ip, power);
    }


    public static void main(String[] args) {
        String a = "YX0";
        String b = "YX1";
        System.out.println(a.compareTo(b));
    }
}
