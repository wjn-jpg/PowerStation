package com.ntdq.power_station.nettyHandler.parseCore.parse;

import com.alibaba.fastjson.JSON;
import com.ntdq.power_station.authority.client.MqttConsumer;
import com.ntdq.power_station.remoteControl.ControlCommandCallback;
import com.ntdq.power_station.remoteControl.wrapper.PromiseWrapper;
import com.ntdq.power_station.domain.Energy;
import com.ntdq.power_station.domain.engrgy.yc.*;
import com.ntdq.power_station.domain.engrgy.yx.EnergyBatterGroupYx;
import com.ntdq.power_station.domain.engrgy.yx.EnergyBatteryInvertYx;
import com.ntdq.power_station.domain.engrgy.yx.EnergyYX;
import com.ntdq.power_station.domain.engrgy.yx.NariProtectDeviceYX;
import com.ntdq.power_station.nettyHandler.parseCore.handler.ForwardHandler;
import com.ntdq.power_station.nettyHandler.parseCore.entyty.PowerPoint;
import com.ntdq.power_station.redis.RedisClient;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.YcAndYxProcess;
import com.ntdq.power_station.util.CommonByteUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ntdq.power_station.util.FeedConversionUtil.*;
import static com.ntdq.power_station.util.FeedConversionUtil.toFloat;

@Component
@RequestMapping("/kafka/point")
public class Energy104Parser implements IParse {

    @Autowired
    private MqttConsumer mqttConsumer;


    /**
     * 0 yx
     * 1 yc
     */
    private static int Send_YC_YX = 0;

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(Energy104Parser.class);

    private final ForwardHandler<EnergyInverter> energyInverterForwardHandler = new ForwardHandler<EnergyInverter>() {
    };

    private final ForwardHandler<EnergyBatteryGroupExtra> energyBatteryGroupExtraForwardHandler = new ForwardHandler<EnergyBatteryGroupExtra>() {
    };

    private final ForwardHandler<EnergyBatteryStackExtra> energyBatteryStackExtraForwardHandler = new ForwardHandler<EnergyBatteryStackExtra>() {
    };

//    private final ForwardHandler<EnergyBatteryGroupMonomerVoltage> batteryGroupMonomerVoltageForwardHandler = new ForwardHandler<EnergyBatteryGroupMonomerVoltage>() {
//    };

    private final ForwardHandler<EnergyBatterGroupYx> energyBatterGroupYxForwardHandler = new ForwardHandler<EnergyBatterGroupYx>() {
    };

    private final ForwardHandler<EnergyBatteryInvertYx> energyBatteryInvertYxForwardHandler = new ForwardHandler<EnergyBatteryInvertYx>() {
    };

    private final ForwardHandler<NariProtectDeviceYC> nariProtectDeviceYCForwardHandler = new ForwardHandler<NariProtectDeviceYC>() {
    };

    private final ForwardHandler<NariProtectDeviceYX> nariProtectDeviceYXForwardHandler = new ForwardHandler<NariProtectDeviceYX>() {
    };

    private final int FIRST_ADDRESS_YX = 0;

    private final int FIRST_ADDRESS_YC = 16385;

    /**
     * 储能PCS#1
     */
    private EnergyInverter energyInverter1 = new EnergyInverter();

    /**
     * 储能PCS#2
     */
    private EnergyInverter energyInverter2 = new EnergyInverter();

    /**
     * 1 电池堆
     */
    private EnergyBatteryStackExtra energyBatteryStack1 = new EnergyBatteryStackExtra();

    /**
     * 2 电池堆
     */
    private EnergyBatteryStackExtra energyBatteryStack2 = new EnergyBatteryStackExtra();

    /**
     * 1-1 电池组
     */
    private EnergyBatteryGroupExtra energyBatteryGroup1_1 = new EnergyBatteryGroupExtra();

    /**
     * 1-1 电池电压信息
     */
    private EnergyBatteryGroupMonomerVoltage energyBatteryGroupMonomerVoltage1_1 = new EnergyBatteryGroupMonomerVoltage();

    /**
     * 1-2 电池组
     */
    private EnergyBatteryGroupExtra energyBatteryGroup1_2 = new EnergyBatteryGroupExtra();
    /**
     * 1-2 电池电压信息
     */
    private EnergyBatteryGroupMonomerVoltage energyBatteryGroupMonomerVoltage1_2 = new EnergyBatteryGroupMonomerVoltage();

    /**
     * 1-3 电池组
     */
    private EnergyBatteryGroupExtra energyBatteryGroup1_3 = new EnergyBatteryGroupExtra();

    /**
     * 1-3 电池电压信息
     */
    private EnergyBatteryGroupMonomerVoltage energyBatteryGroupMonomerVoltage1_3 = new EnergyBatteryGroupMonomerVoltage();

    /**
     * 2-1 电池组
     */
    private EnergyBatteryGroupExtra energyBatteryGroup2_1 = new EnergyBatteryGroupExtra();

    /**
     * 2-1 电池电压信息
     */
    private EnergyBatteryGroupMonomerVoltage energyBatteryGroupMonomerVoltage2_1 = new EnergyBatteryGroupMonomerVoltage();

    /**
     * 2-2 电池组
     */
    private EnergyBatteryGroupExtra energyBatteryGroup2_2 = new EnergyBatteryGroupExtra();

    /**
     * 2-2 电池电压信息
     */
    private EnergyBatteryGroupMonomerVoltage energyBatteryGroupMonomerVoltage2_2 = new EnergyBatteryGroupMonomerVoltage();

    /**
     * 2-3 电池组
     */
    private EnergyBatteryGroupExtra energyBatteryGroup2_3 = new EnergyBatteryGroupExtra();

    /**
     * 2-3 电池电压信息
     */
    private EnergyBatteryGroupMonomerVoltage energyBatteryGroupMonomerVoltage2_3 = new EnergyBatteryGroupMonomerVoltage();

    private NariProtectDeviceYC nariProtectDeviceYC1 = new NariProtectDeviceYC();

    private NariProtectDeviceYC nariProtectDeviceYC2 = new NariProtectDeviceYC();

    // yX | YC =========


    private EnergyBatteryInvertYx energyBatteryInvertYx1;

    private EnergyBatterGroupYx energyBatterGroupYx11_1;

    private EnergyBatterGroupYx energyBatterGroupYx11_2;

    private EnergyBatterGroupYx energyBatterGroupYx11_3;

    private EnergyBatteryInvertYx energyBatteryInvertYx2;

    private EnergyBatterGroupYx energyBatterGroupYx12_1;

    private EnergyBatterGroupYx energyBatterGroupYx12_2;

    private EnergyBatterGroupYx energyBatterGroupYx12_3;

    /**
     * 南瑞保护装置YX
     */
    private NariProtectDeviceYX nariProtectDeviceYX1;

    /**
     * 南瑞保护装置YX
     */
    private NariProtectDeviceYX nariProtectDeviceYX2;

    /**
     * 解析报文帧数量
     */
    private final AtomicInteger YC_COUNT = new AtomicInteger(0);

    /**
     * 解析报文帧数量
     */
    private final AtomicInteger YX_COUNT = new AtomicInteger(0);

    @Autowired
    private RedisClient redisClient;

    private PromiseWrapper promiseWrapper;

    public void setAddressFieldMap(Map<Integer, String> addressFieldMap) {
        energyBatteryGroupExtraForwardHandler.setAddressFieldNameMap(addressFieldMap);
        energyBatteryStackExtraForwardHandler.setAddressFieldNameMap(addressFieldMap);
        //batteryGroupMonomerVoltageForwardHandler.setAddressFieldNameMap(addressFieldMap);
        energyInverterForwardHandler.setAddressFieldNameMap(addressFieldMap);
        energyBatterGroupYxForwardHandler.setAddressFieldNameMap(addressFieldMap);
        energyBatteryInvertYxForwardHandler.setAddressFieldNameMap(addressFieldMap);
        nariProtectDeviceYCForwardHandler.setAddressFieldNameMap(addressFieldMap);
        nariProtectDeviceYXForwardHandler.setAddressFieldNameMap(addressFieldMap);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "NTDLL2";

    private PowerPoint powerPoint;

    private static int DIVIDED = 4;

    private static final int TOTAL_LENGTH = 2269;


    @Override
    public void parseByteToMessage(byte[] bytes, YcAndYxProcess callBack) {
        try {
            String hexData = CommonByteUtils.BinaryToHexString(bytes);
            String[] hexDataArray = hexData.split(" ");
            logger.info("总共收到的数据:{}", hexDataArray);
            if (hexDataArray[0].equals("68")) {
                //报文长度
                int length = Integer.parseInt(hexDataArray[1], 16);
                //发送序号
                int sendNum = Integer.parseInt(hexDataArray[3] + hexDataArray[2], 16) / 2;
                //接收序号
                int receiverNum = Integer.parseInt(hexDataArray[5] + hexDataArray[4], 16) / 2;
                if (hexDataArray.length > 6) {
                    //获取可变结构限定词，及数据个数
                    String hexStr2BinStr = hexStr2BinStr(hexDataArray[7]);
                    int dateNum = Integer.valueOf(hexStr2BinStr.substring(1, hexStr2BinStr.length()), 2);
                    DIVIDED = dateNum;
                    //传输原因
                    int reason = Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16);
                    switch (reason) {
                        case 3://突发
                            logger.info("突发");
                            break;
                        case 7://激活确认
                            logger.info("激活确认");
                            break;
                        case 10://激活终止
                            logger.info("激活终止");
                            break;
                        case 20://响应站召唤
                            logger.info("响应站召唤");
                            break;
                    }
                    //公共地址
                    int publicAddress = Integer.parseInt(hexDataArray[11] + hexDataArray[10], 16);

                    //类型标识
                    int type = Integer.parseInt(hexDataArray[6], 16);
                    switch (type) {
                        case 1: //单点遥信
                            YX_COUNT.incrementAndGet();
                            Send_YC_YX = 0;
                            logger.info("单点遥信...");
                            // 变电 不带地址 光伏 储能带地址 (遥信)
                            if (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16) != 20) {//只有第一个地址
                                int Addresss = Integer.parseInt(hexDataArray[14] + hexDataArray[13] + hexDataArray[12], 16);
                                logger.info("数据个数：" + dateNum);
                                logger.info("数据首地址：" + Addresss);
                                for (int i = 0; i < dateNum; i++) {
                                    String dateN = "";
                                    for (int j = 1; j > 0; j--) {
                                        dateN += hexDataArray[14 + i * 1 + j];
                                    }
                                    int dateTrue = Integer.parseInt(dateN);
                                    int finalAddress = Addresss + i;
//                                    TestController.addressYXMap.put(finalAddress, dateTrue);
                                    //遥信TODO
                                    //powerPoint = powerPointForwardHandler.parsePointData(finalAddress, String.valueOf(dateTrue), 0);
                                    if (callBack != null) {
                                        callBack.processYXOrYC(finalAddress, String.valueOf(dateTrue));
                                    }

                                    logger.info("地址" + finalAddress + "数据为：" + dateTrue);
                                    parseEnergyPointYXByAddress(finalAddress, dateTrue);
                                }
                            } else {
                                logger.info("数据个数：" + dateNum);
                                for (int i = 0; i < dateNum; i++) {
                                    //地址
                                    int Addresss = 0;
                                    String AddressN = "";
                                    for (int x = 3; x > 0; x--) {
                                        AddressN += hexDataArray[11 + i * 4 + x];
                                    }
                                    Addresss = Integer.parseInt(AddressN, 16);
                                    //数据
                                    String dateN = "";
                                    dateN = hexDataArray[11 + i * 4 + 4];
                                    int dateTrue = Integer.parseInt(dateN);
                                    if (callBack != null) {
                                        callBack.processYXOrYC(Addresss, String.valueOf(dateTrue));
                                    }
//                                    TestController.addressYXMap.put(Addresss, dateTrue);
                                    logger.info("地址" + Addresss + "的数据为：" + dateTrue);
                                    parseEnergyPointYXByAddress(Addresss, dateTrue);
                                }
                            }
                            break;
                        case 2: //遥控
                            break;
                        case 9:
                            if (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16) == 20) {//只有第一个地址
                                int Addresss1 = Integer.parseInt(hexDataArray[14] + hexDataArray[13] + hexDataArray[12], 16);
                                for (int i = 0; i < dateNum; i++) {
                                    String dateN = "";
                                    for (int j = 2; j > 0; j--) {
                                        dateN += hexDataArray[14 + i * 3 + j];
                                    }
                                    logger.info(dateN);
                                    float dateTrue = 0;
                                    if (dateN.substring(0, 1).equals("F")) {
                                        String valueOf = String.valueOf(Long.parseLong(HexB(dateN), 16) + Long.parseLong("01", 16));
                                        dateTrue = 0 - Integer.parseInt(valueOf);
                                    } else {
                                        dateTrue = Integer.parseInt(dateN, 16);
                                    }
                                    int finalAddress = Addresss1 + (i + 1);
                                    if (callBack != null) {
                                        callBack.processYXOrYC(finalAddress, String.valueOf(dateTrue));
                                    }
                                    logger.info("地址" + finalAddress + "个数据为：" + dateTrue);
                                }
                            } else {
                                logger.info("数据个数：" + dateNum);
                                for (int i = 0; i < dateNum; i++) {
                                    //地址
                                    int Addresss = 0;
                                    String AddressN = "";
                                    for (int x = 3; x > 0; x--) {
                                        AddressN += hexDataArray[11 + i * 6 + x];
                                    }
                                    Addresss = Integer.parseInt(AddressN, 16);
                                    logger.info("数据地址：" + Addresss);
                                    //数据
                                    String dateN = "";
                                    for (int j = 2; j > 0; j--) {
                                        dateN += hexDataArray[11 + i * 6 + j + 3];
                                    }
                                    float dateTrue = 0;
                                    if (dateN.substring(0, 1).equals("F")) {
                                        String valueOf = String.valueOf(Long.parseLong(HexB(dateN), 16) + Long.parseLong("01", 16));
                                        dateTrue = 0 - Integer.parseInt(valueOf);
                                    } else {
                                        dateTrue = Integer.parseInt(dateN, 16);
                                    }
                                    int finalAddress = Addresss + (i + 1);
                                    if (callBack != null) {
                                        callBack.processYXOrYC(finalAddress, String.valueOf(dateTrue));
                                    }
                                    logger.info("地址" + finalAddress + "个数据为:" + dateTrue);
                                }
                            }
                            break;
                        case 13: //遥测 短浮点数
                            Send_YC_YX = 1;
                            YC_COUNT.incrementAndGet();
                            if (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16) == 20) {//只有第一个地址
                                logger.info("遥测数据不带地址...");
                                int Addresss1 = Integer.parseInt(hexDataArray[14] + hexDataArray[13] + hexDataArray[12], 16);
                                for (int i = 0; i < dateNum; i++) {
                                    String dateN = "";
                                    for (int j = 4; j > 0; j--) {
                                        dateN += hexDataArray[14 + i * 5 + j];
                                    }
                                    float dateTrue = 0;
                                    try {
                                        dateTrue = Float.parseFloat(toFloat(dateN));
                                    } catch (Exception e) {
                                        dateTrue = Float.intBitsToFloat(new BigInteger(dateN, 16).intValue());
                                    }
                                    int finalAddress = Addresss1 + i;
                                    logger.info("地址" + finalAddress + "数据为：" + dateTrue);
                                    if (callBack != null) {
                                        callBack.processYXOrYC(finalAddress, String.valueOf(dateTrue));
                                    }
                                    parseEnergyPointYCByAddress(finalAddress, dateTrue);
//                                    TestController.addressValueMap.put(finalAddress, dateTrue);
                                }
                            } else {//数据都包含地址位
                                logger.info("遥测数据有地址...");
                                int dateAddressHasP = Integer.parseInt(hexDataArray[14] + hexDataArray[13] + hexDataArray[12], 16);
                                logger.info("数据个数：" + dateNum);
                                logger.info("数据地址：" + dateAddressHasP);
                                for (int i = 0; i < dateNum; i++) {
                                    String dateN = "";
                                    for (int j = 4; j > 0; j--) {
                                        dateN += hexDataArray[11 + i * 8 + j + 3];
                                    }
                                    float dateTrue = 0;
                                    try {
                                        dateTrue = Float.valueOf(toFloat(dateN));
                                    } catch (Exception e) {
                                        dateTrue = Float.intBitsToFloat(new BigInteger(dateN, 16).intValue());
                                    }
                                    int finalAddress = dateAddressHasP + (i + 1);
                                    logger.info("地址" + finalAddress + "数据为：" + dateTrue);
                                    //log.info("品质值：" + DateArray[11 + i * 8 + 8]);
                                    if (callBack != null) {
                                        callBack.processYXOrYC(finalAddress, String.valueOf(dateTrue));
                                    }
//                                    TestController.addressValueMap.put(finalAddress, dateTrue);
                                    parseEnergyPointYCByAddress(finalAddress, dateTrue);
                                }
                            }
                            break;
                        case 45: //单点控制
                            logger.info("遥控单点控制 45");
                            if (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16) == 7) {
                                logger.info("激活确认");
                            }
                            int addressRTU = Integer.parseInt(hexDataArray[11] + hexDataArray[10], 16);
                            logger.info("RTU地址为:{}", addressRTU);
                            int addressMessage = Integer.parseInt(hexDataArray[14] + hexDataArray[13] + hexDataArray[12], 16);
                            logger.info("信息体地址:{}", addressMessage);
                            break;
                        case 50:
                            logger.info("遥调单点控制浮点数 50 ************************************************");
                            boolean isSuccess = false;
                            //TODO
                            if (isSuccess) {
                                promiseWrapper.getControlCommandCallback().onCommandResult(true);
                            } else {
                                promiseWrapper.getControlCommandCallback().onCommandResult(false);
                            }
                            break;
                    }
                }
            }
            switch (Send_YC_YX) {
                case 0://yx
                    if (YX_COUNT.get() >= 20) {
                        EnergyYX energyYX = new EnergyYX();
                        energyYX.setEnergyBatteryInvertYx1(energyBatteryInvertYx1);
                        energyYX.setEnergyBatteryInvertYx2(energyBatteryInvertYx2);
                        energyYX.setEnergyBatterGroupYx11_1(energyBatterGroupYx11_1);
                        energyYX.setEnergyBatterGroupYx11_2(energyBatterGroupYx11_1);
                        energyYX.setEnergyBatterGroupYx11_3(energyBatterGroupYx11_1);
                        energyYX.setEnergyBatterGroupYx12_1(energyBatterGroupYx12_1);
                        energyYX.setEnergyBatterGroupYx12_2(energyBatterGroupYx12_2);
                        energyYX.setEnergyBatterGroupYx12_3(energyBatterGroupYx12_3);
                        mqttConsumer.connect();
                        MqttConsumer.publish("EnergyYXTopic", JSON.toJSONString(energyYX));
                        YC_COUNT.set(0);
                    }
                    break;
                case 1://yc
                    if (YC_COUNT.get() >= 75) {
                        Energy energy = new Energy();
                        EnergyBatteryStack energyBatteryStack1 = new EnergyBatteryStack();
                        EnergyBatteryStack energyBatteryStack2 = new EnergyBatteryStack();

                        EnergyBatteryGroup energyBatteryGroup1_1 = new EnergyBatteryGroup();
                        EnergyBatteryGroup energyBatteryGroup1_2 = new EnergyBatteryGroup();
                        EnergyBatteryGroup energyBatteryGroup1_3 = new EnergyBatteryGroup();

                        energyBatteryGroup1_1.setEnergyBatteryGroupExtra(this.energyBatteryGroup1_1);
                        energyBatteryGroup1_1.setEnergyBatteryGroupMonomerVoltage(this.energyBatteryGroupMonomerVoltage1_1);
                        energyBatteryGroup1_2.setEnergyBatteryGroupExtra(this.energyBatteryGroup1_2);
                        energyBatteryGroup1_2.setEnergyBatteryGroupMonomerVoltage(this.energyBatteryGroupMonomerVoltage1_2);
                        energyBatteryGroup1_3.setEnergyBatteryGroupExtra(this.energyBatteryGroup1_3);
                        energyBatteryGroup1_3.setEnergyBatteryGroupMonomerVoltage(this.energyBatteryGroupMonomerVoltage1_3);

                        EnergyBatteryGroup energyBatteryGroup2_1 = new EnergyBatteryGroup();
                        EnergyBatteryGroup energyBatteryGroup2_2 = new EnergyBatteryGroup();
                        EnergyBatteryGroup energyBatteryGroup2_3 = new EnergyBatteryGroup();

                        energyBatteryGroup2_1.setEnergyBatteryGroupExtra(this.energyBatteryGroup2_1);
                        energyBatteryGroup2_1.setEnergyBatteryGroupMonomerVoltage(this.energyBatteryGroupMonomerVoltage2_1);
                        energyBatteryGroup2_2.setEnergyBatteryGroupExtra(this.energyBatteryGroup2_2);
                        energyBatteryGroup2_2.setEnergyBatteryGroupMonomerVoltage(this.energyBatteryGroupMonomerVoltage2_2);
                        energyBatteryGroup2_3.setEnergyBatteryGroupExtra(this.energyBatteryGroup2_3);
                        energyBatteryGroup2_3.setEnergyBatteryGroupMonomerVoltage(this.energyBatteryGroupMonomerVoltage2_3);

                        energyBatteryStack1.setEnergyBatteryGroup1(energyBatteryGroup1_1);
                        energyBatteryStack1.setEnergyBatteryGroup2(energyBatteryGroup1_2);
                        energyBatteryStack1.setEnergyBatteryGroup3(energyBatteryGroup1_3);
                        energyBatteryStack1.setEnergyBatteryStackExtra(this.energyBatteryStack1);

                        energyBatteryStack2.setEnergyBatteryGroup1(energyBatteryGroup2_1);
                        energyBatteryStack2.setEnergyBatteryGroup2(energyBatteryGroup2_2);
                        energyBatteryStack2.setEnergyBatteryGroup3(energyBatteryGroup2_3);
                        energyBatteryStack2.setEnergyBatteryStackExtra(this.energyBatteryStack2);

                        this.energyInverter1.setEnergyBatteryStack(energyBatteryStack1);
                        this.energyInverter2.setEnergyBatteryStack(energyBatteryStack2);

                        energy.setEnergyInverter1(energyInverter1);
                        energy.setEnergyInverter2(energyInverter2);

                        energy.setNariProtectDeviceYC1(nariProtectDeviceYC1);
                        energy.setNariProtectDeviceYC2(nariProtectDeviceYC2);


                        mqttConsumer.connect();
                        MqttConsumer.publish("EnergyYCTopic", JSON.toJSONString(energy));
                        YC_COUNT.set(0);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/send")
    public void send() {
        while (true) {
            PowerPoint powerPoint = null;
            //TODO
            //powerPoint = powerPointForwardHandler.parsePointData(1, "0", 0);
            //powerPoint = powerPointForwardHandler.parsePointData(2, "9.99", 1);
            //kafka send 指针指控 help gc
            kafkaTemplate.send(TOPIC, JSON.toJSONString(powerPoint));
            // powerPointForwardHandler.setObjNull();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 处理104协议遥测消息
     *
     * @param address
     * @param data
     */
    private void parseEnergyPointYXByAddress(int address, int data) {
        int pointAddress = address - FIRST_ADDRESS_YX;
        if (pointAddress >= 1 && pointAddress <= 137) {
            energyBatteryInvertYx1 = energyBatteryInvertYxForwardHandler.parsePointData(address, String.valueOf(data), 1, energyBatteryInvertYx1);
        } else if (pointAddress >= 138 && pointAddress <= 187) {
            energyBatterGroupYx11_1 = energyBatterGroupYxForwardHandler.parsePointData(address, String.valueOf(data), 1, energyBatterGroupYx11_1);
        } else if (pointAddress >= 188 && pointAddress <= 237) {
            energyBatterGroupYx11_2 = energyBatterGroupYxForwardHandler.parsePointData(address, String.valueOf(data), 1, energyBatterGroupYx11_2);
        } else if (pointAddress >= 238 && pointAddress <= 287) {
            energyBatterGroupYx11_3 = energyBatterGroupYxForwardHandler.parsePointData(address, String.valueOf(data), 1, energyBatterGroupYx11_3);
        } else if (pointAddress >= 288 && pointAddress <= 424) {
            energyBatteryInvertYx2 = energyBatteryInvertYxForwardHandler.parsePointData(address, String.valueOf(data), 1, energyBatteryInvertYx2);
        } else if (pointAddress >= 425 && pointAddress <= 474) {
            energyBatterGroupYx12_1 = energyBatterGroupYxForwardHandler.parsePointData(address, String.valueOf(data), 1, energyBatterGroupYx12_1);
        } else if (pointAddress >= 475 && pointAddress <= 524) {
            energyBatterGroupYx12_2 = energyBatterGroupYxForwardHandler.parsePointData(address, String.valueOf(data), 1, energyBatterGroupYx12_2);
        } else if (pointAddress >= 525 && pointAddress <= 574) {
            energyBatterGroupYx12_3 = energyBatterGroupYxForwardHandler.parsePointData(address, String.valueOf(data), 1, energyBatterGroupYx12_3);
        } else if (pointAddress >= 575 && pointAddress <= 593) { //南瑞YX保护1
            nariProtectDeviceYX1 = nariProtectDeviceYXForwardHandler.parsePointData(address, String.valueOf(data), 1, nariProtectDeviceYX1);
        } else if (pointAddress >= 594 && pointAddress <= 612) { //南瑞YX保护2
            nariProtectDeviceYX2 = nariProtectDeviceYXForwardHandler.parsePointData(address, String.valueOf(data), 1, nariProtectDeviceYX2);
        }

    }

    /**
     * 处理104协议遥测消息
     *
     * @param address
     * @param data
     */
    private void parseEnergyPointYCByAddress(int address, float data) {
        int pointAddress = address - FIRST_ADDRESS_YC;
        if (pointAddress >= 0 && pointAddress <= 36) {
            energyInverter1 = energyInverterForwardHandler.parsePointData(address, String.valueOf(data), 0, energyInverter1);
        } else if (pointAddress >= 37 && pointAddress <= 45) {
            energyBatteryStack1 = energyBatteryStackExtraForwardHandler.parsePointData(address, String.valueOf(data), 0, energyBatteryStack1);
        } else if (pointAddress >= 46 && pointAddress <= 86) {
            energyBatteryGroup1_1 = energyBatteryGroupExtraForwardHandler.parsePointData(address, String.valueOf(data), 0, energyBatteryGroup1_1);
        } else if (pointAddress >= 87 && pointAddress <= 127) {
            energyBatteryGroup1_2 = energyBatteryGroupExtraForwardHandler.parsePointData(address, String.valueOf(data), 0, energyBatteryGroup1_2);
        } else if (pointAddress >= 128 && pointAddress <= 168) {
            energyBatteryGroup1_3 = energyBatteryGroupExtraForwardHandler.parsePointData(address, String.valueOf(data), 0, energyBatteryGroup1_3);
        } else if (pointAddress >= 169 && pointAddress <= 1107) {
            if (pointAddress <= 481) {
                int index = pointAddress - 169;
                energyBatteryGroupMonomerVoltage1_1.setVoltage(index, data);
            } else if (pointAddress <= 794) {
                int index = pointAddress - 482;
                energyBatteryGroupMonomerVoltage1_2.setVoltage(index, data);
            } else {
                int index = pointAddress - 795;
                energyBatteryGroupMonomerVoltage1_3.setVoltage(index, data);
            }
        } else if (pointAddress >= 1108 && pointAddress <= 1144) {
            energyInverter2 = energyInverterForwardHandler.parsePointData(address, String.valueOf(data), 0, energyInverter2);
        } else if (pointAddress >= 1145 && pointAddress <= 1153) {
            energyBatteryStack2 = energyBatteryStackExtraForwardHandler.parsePointData(address, String.valueOf(data), 0, energyBatteryStack2);
        } else if (pointAddress >= 1154 && pointAddress <= 1194) {
            energyBatteryGroup2_1 = energyBatteryGroupExtraForwardHandler.parsePointData(address, String.valueOf(data), 0, energyBatteryGroup2_1);
        } else if (pointAddress >= 1195 && pointAddress <= 1235) {
            energyBatteryGroup2_2 = energyBatteryGroupExtraForwardHandler.parsePointData(address, String.valueOf(data), 0, energyBatteryGroup2_2);
        } else if (pointAddress >= 1236 && pointAddress <= 1276) {
            energyBatteryGroup2_3 = energyBatteryGroupExtraForwardHandler.parsePointData(address, String.valueOf(data), 0, energyBatteryGroup2_3);
        } else if (pointAddress >= 1277 && pointAddress <= 2215) {
            if (pointAddress <= 1589) {
                int index = pointAddress - 1277;
                energyBatteryGroupMonomerVoltage2_1.setVoltage(index, data);
            } else if (pointAddress <= 1902) {
                int index = pointAddress - 1590;
                energyBatteryGroupMonomerVoltage2_2.setVoltage(index, data);
            } else {
                int index = pointAddress - 1903;
                energyBatteryGroupMonomerVoltage2_3.setVoltage(index, data);
            }
        } else if (pointAddress >= 2216 && pointAddress <= 2242) {
            nariProtectDeviceYC1 = nariProtectDeviceYCForwardHandler.parsePointData(address, String.valueOf(data), 0, nariProtectDeviceYC1);
        } else if (pointAddress >= 2243 && pointAddress <= 2269) {
            nariProtectDeviceYC2 = nariProtectDeviceYCForwardHandler.parsePointData(address, String.valueOf(data), 0, nariProtectDeviceYC2);
        }
    }

    @RequestMapping("/getEnergy")
    public Energy getPhotovoltaic() {
        for (int i = 0; i <= 1276; i++) {
            parseEnergyPointYCByAddress(FIRST_ADDRESS_YC + i, i);
        }
        Energy energy = new Energy();
        EnergyBatteryStack energyBatteryStack1 = new EnergyBatteryStack();
        EnergyBatteryStack energyBatteryStack2 = new EnergyBatteryStack();
        EnergyBatteryGroup energyBatteryGroup1_1 = new EnergyBatteryGroup();
        EnergyBatteryGroup energyBatteryGroup1_2 = new EnergyBatteryGroup();
        EnergyBatteryGroup energyBatteryGroup1_3 = new EnergyBatteryGroup();
        energyBatteryGroup1_1.setEnergyBatteryGroupExtra(this.energyBatteryGroup1_1);
        energyBatteryGroup1_2.setEnergyBatteryGroupExtra(this.energyBatteryGroup1_2);
        energyBatteryGroup1_3.setEnergyBatteryGroupExtra(this.energyBatteryGroup1_3);
        EnergyBatteryGroup energyBatteryGroup2_1 = new EnergyBatteryGroup();
        EnergyBatteryGroup energyBatteryGroup2_2 = new EnergyBatteryGroup();
        EnergyBatteryGroup energyBatteryGroup2_3 = new EnergyBatteryGroup();
        energyBatteryGroup2_1.setEnergyBatteryGroupExtra(this.energyBatteryGroup2_1);
        energyBatteryGroup2_2.setEnergyBatteryGroupExtra(this.energyBatteryGroup2_2);
        energyBatteryGroup2_3.setEnergyBatteryGroupExtra(this.energyBatteryGroup2_3);

        energyBatteryStack1.setEnergyBatteryGroup1(energyBatteryGroup1_1);
        energyBatteryStack1.setEnergyBatteryGroup2(energyBatteryGroup1_2);
        energyBatteryStack1.setEnergyBatteryGroup3(energyBatteryGroup1_3);
        energyBatteryStack1.setEnergyBatteryStackExtra(this.energyBatteryStack1);

        energyBatteryStack2.setEnergyBatteryGroup1(energyBatteryGroup2_1);
        energyBatteryStack2.setEnergyBatteryGroup2(energyBatteryGroup2_2);
        energyBatteryStack2.setEnergyBatteryGroup3(energyBatteryGroup2_3);
        energyBatteryStack2.setEnergyBatteryStackExtra(this.energyBatteryStack2);

        this.energyInverter1.setEnergyBatteryStack(energyBatteryStack1);
        this.energyInverter2.setEnergyBatteryStack(energyBatteryStack2);

        energy.setEnergyInverter1(energyInverter1);
        energy.setEnergyInverter2(energyInverter2);
        return energy;
    }

    @Override
    public PromiseWrapper setControlPromiseWrapper(Channel channel, ChannelPromise channelPromise, ControlCommandCallback controlCommandCallback) {
        //TODO
        promiseWrapper = new PromiseWrapper(channel, channelPromise, controlCommandCallback);
        return promiseWrapper;
    }

    public static void main(String[] args) {
        ForwardHandler<PowerPoint> powerPointForwardHandler = new ForwardHandler<PowerPoint>() {
        };
        PowerPoint powerPoint = null;
        powerPoint = powerPointForwardHandler.parsePointData(1, "0", 0);
        powerPoint = powerPointForwardHandler.parsePointData(2, "1.45", 1);
        System.out.println(powerPoint);
        //kafka send 指针指控 help gc
        //kafkaTemplate.send("defaultTopic",JSON.toString(powerPoint));
        powerPointForwardHandler.setObjNull();

        PowerPoint powerPoint1;
        powerPoint1 = powerPointForwardHandler.parsePointData(1, "1", 0);
        powerPoint1 = powerPointForwardHandler.parsePointData(2, "2.78", 1);
        System.out.println(powerPoint1);
    }
}
