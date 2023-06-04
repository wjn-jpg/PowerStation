package com.ntdq.power_station.nettyHandler.parseCore.parse;

import com.alibaba.fastjson.JSON;
import com.ntdq.power_station.authority.client.MqttConsumer;
import com.ntdq.power_station.domain.photovoltaic.yc.Photovoltaic;
import com.ntdq.power_station.domain.photovoltaic.yc.PhotovoltaicYXExtra;
import com.ntdq.power_station.domain.photovoltaic.yc.PhotovoltaicInverter;
import com.ntdq.power_station.domain.photovoltaic.yx.NariMeasureControlDeviceYX;
import com.ntdq.power_station.domain.photovoltaic.yx.PhotovoltaicInvertYx;
import com.ntdq.power_station.domain.photovoltaic.yx.PhotovoltaicYx;
import com.ntdq.power_station.nettyHandler.parseCore.handler.ForwardHandler;
import com.ntdq.power_station.remoteControl.ControlCommandCallback;
import com.ntdq.power_station.remoteControl.wrapper.PromiseWrapper;
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
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ntdq.power_station.util.FeedConversionUtil.*;
import static com.ntdq.power_station.util.FeedConversionUtil.toFloat;

@Component
@RestController
@RequestMapping("/Photovoltaic")
public class Photovoltaic104Parser implements IParse {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(Photovoltaic104Parser.class);

    /**
     * 解析报文帧数量
     */
    private final AtomicInteger yc_count = new AtomicInteger(0);

    private final AtomicInteger yx_count = new AtomicInteger(0);

    @Autowired
    private MqttConsumer mqttConsumer;

    private final int DIVIDED = 4;

    private static final int TOTAL_LENGTH = 1000;

    private final ForwardHandler<PhotovoltaicInverter> photovoltaicForwardHandler = new ForwardHandler<PhotovoltaicInverter>() {
    };

    private final ForwardHandler<PhotovoltaicYXExtra> photovoltaicExtraForwardHandler = new ForwardHandler<PhotovoltaicYXExtra>() {
    };

    private final ForwardHandler<PhotovoltaicInvertYx> photovoltaicInvertYxForwardHandler = new ForwardHandler<PhotovoltaicInvertYx>() {
    };

    private final ForwardHandler<NariMeasureControlDeviceYX> nariMeasureControlDeviceYXForwardHandler = new ForwardHandler<NariMeasureControlDeviceYX>() {
    };
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "NTDLL2";

    /**
     * 光伏逆变器1
     */
    private PhotovoltaicInverter photovoltaicInverterInverter1 = new PhotovoltaicInverter();

    /**
     * 光伏逆变器2
     */
    private PhotovoltaicInverter photovoltaicInverterInverter2 = new PhotovoltaicInverter();

    /**
     * 光伏逆变器3
     */
    private PhotovoltaicInverter photovoltaicInverterInverter3 = new PhotovoltaicInverter();

    /**
     * 光伏逆变器4
     */
    private PhotovoltaicInverter photovoltaicInverterInverter4 = new PhotovoltaicInverter();

    /**
     * 光伏遥信额外信息
     */
    private PhotovoltaicYXExtra photovoltaicExtra;


    // YC | YX ===============

    private PhotovoltaicInvertYx photovoltaicInvertYx1 = new PhotovoltaicInvertYx();

    private PhotovoltaicInvertYx photovoltaicInvertYx2 = new PhotovoltaicInvertYx();

    private PhotovoltaicInvertYx photovoltaicInvertYx3 = new PhotovoltaicInvertYx();

    private PhotovoltaicInvertYx photovoltaicInvertYx4 = new PhotovoltaicInvertYx();

    private NariMeasureControlDeviceYX measureControlDeviceYX = new NariMeasureControlDeviceYX();

    private final int FIRST_ADDRESS_YX = 1;

    private final int FIRST_ADDRESS_YC = 18000;


    /**
     * 0 yx
     * 1 yc
     */
    private static int Send_YC_YX = 0;


    private PromiseWrapper promiseWrapper;

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
                    //传输原因
                    switch (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16)) {
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
                    switch (Integer.parseInt(hexDataArray[6], 16)) {
                        case 1: //单点遥信
                            Send_YC_YX = 0;
                            logger.info("单点遥信...");
                            // 变电 不带地址 光伏 储能带地址 (遥信)
                            if (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16) != 20) {//只有第一个地址
                                int Addresss = Integer.parseInt(hexDataArray[14] + hexDataArray[13] + hexDataArray[12], 16);
                                logger.info("数据个数：" + dateNum);
                                logger.info("数据地址：" + Addresss);
                                for (int i = 0; i < dateNum; i++) {
                                    String dateN = "";
                                    for (int j = 1; j > 0; j--) {
                                        dateN += hexDataArray[14 + i * 1 + j];
                                    }
                                    int dateTrue = Integer.parseInt(dateN);
                                    int finalAddress = Addresss + i;
                                    //PhotovoltaicInverter1 = photovoltaicForwardHandler.parsePointData(finalAddress, String.valueOf(dateTrue), 0);
                                    if (callBack != null) {
                                        callBack.processYXOrYC(finalAddress, String.valueOf(dateTrue));
                                    }
                                    logger.info("地址" + finalAddress + "数据为：" + dateTrue);
                                    yx_count.incrementAndGet();
                                    parsePhotovoltaicPointYXByAddress(finalAddress, dateTrue);
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
                                    logger.info("地址" + Addresss + "的数据为：" + dateTrue);
                                    yx_count.incrementAndGet();
                                    parsePhotovoltaicPointYXByAddress(Addresss, dateTrue);
                                }
                            }
                            break;
                        case 2: //遥控
                            break;
                        case 9:
                            if (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16) != 20) {//只有第一个地址
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
                                    logger.info("地址" + finalAddress + "个数据为:" + dateTrue);
                                }
                            }
                            break;
                        case 13: //遥测 短浮点数
                            Send_YC_YX = 1;
                            if (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16) == 20) {//只有第一个地址
                                logger.info("遥测数据不带地址...");
                                int Addresss1 = Integer.parseInt(hexDataArray[14] + hexDataArray[13] + hexDataArray[12], 16);
                                logger.info("数据个数：" + dateNum);
                                logger.info("数据地址：" + Addresss1);
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
                                    yc_count.incrementAndGet();
                                    parsePhotovoltaicPointYCByAddress(finalAddress, dateTrue);
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
                                    yc_count.incrementAndGet();
                                    parsePhotovoltaicPointYCByAddress(finalAddress, dateTrue);
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
                            System.out.println("a");
                            if (promiseWrapper.getControlCommandCallback() != null) {
                                promiseWrapper.getControlCommandCallback().onCommandResult(true);
                            }
                            break;
                    }
                }
            }
            switch (Send_YC_YX) {
                case 0:
                    if (yx_count.get() >= 3) {
                        PhotovoltaicYx photovoltaicYx = new PhotovoltaicYx();
                        photovoltaicYx.setPhotovoltaicInvertYx1(photovoltaicInvertYx1);
                        photovoltaicYx.setPhotovoltaicInvertYx2(photovoltaicInvertYx2);
                        photovoltaicYx.setPhotovoltaicInvertYx3(photovoltaicInvertYx3);
                        photovoltaicYx.setPhotovoltaicInvertYx4(photovoltaicInvertYx4);
                        photovoltaicYx.setNariMeasureControlDeviceYX(measureControlDeviceYX);
                        mqttConsumer.connect();
                        MqttConsumer.publish("PhotovoltaicYXTopic", JSON.toJSONString(photovoltaicYx));
                        yx_count.set(0);
                    }
                    break;
                case 1:
                    if (yc_count.get() >= 4) {
                        Photovoltaic photovoltaic = new Photovoltaic();
                        photovoltaic.setPhotovoltaicInverter1(photovoltaicInverterInverter1);
                        photovoltaic.setPhotovoltaicInverter2(photovoltaicInverterInverter2);
                        photovoltaic.setPhotovoltaicInverter3(photovoltaicInverterInverter3);
                        photovoltaic.setPhotovoltaicInverter4(photovoltaicInverterInverter4);
                        photovoltaic.setPhotovoltaicExtra(photovoltaicExtra);
                        mqttConsumer.connect();
                        MqttConsumer.publish("PhotovoltaicYCTopic", JSON.toJSONString(photovoltaic));
                        yc_count.set(0);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isNeedSendKafka(int count, int divided, int totalLength) {
        return count >= 4;
    }

    private void resetNull() {
        photovoltaicForwardHandler.setObjNull();
        photovoltaicExtraForwardHandler.setObjNull();
        photovoltaicInverterInverter1 = new PhotovoltaicInverter();
        photovoltaicInverterInverter2 = new PhotovoltaicInverter();
        photovoltaicInverterInverter3 = new PhotovoltaicInverter();
        photovoltaicInverterInverter4 = new PhotovoltaicInverter();
    }

    public void setAddressFieldMap(Map<Integer, String> addressFieldMap) {
        photovoltaicForwardHandler.setAddressFieldNameMap(addressFieldMap);
        photovoltaicExtraForwardHandler.setAddressFieldNameMap(addressFieldMap);
        nariMeasureControlDeviceYXForwardHandler.setAddressFieldNameMap(addressFieldMap);
        photovoltaicInvertYxForwardHandler.setAddressFieldNameMap(addressFieldMap);
    }

    /**
     * 处理104协议遥测消息
     *
     * @param address
     * @param data
     */
    private void parsePhotovoltaicPointYCByAddress(int address, float data) {
        int pointAddress = address - FIRST_ADDRESS_YC;
        if (pointAddress >= 0 && pointAddress <= 39) {
            photovoltaicInverterInverter1 = photovoltaicForwardHandler.parsePointData(address, String.valueOf(data), 0, photovoltaicInverterInverter1);
        } else if (pointAddress >= 40 && pointAddress <= 79) {
            photovoltaicInverterInverter2 = photovoltaicForwardHandler.parsePointData(address, String.valueOf(data), 0, photovoltaicInverterInverter2);
        } else if (pointAddress >= 80 && pointAddress <= 119) {
            photovoltaicInverterInverter3 = photovoltaicForwardHandler.parsePointData(address, String.valueOf(data), 0, photovoltaicInverterInverter3);
        } else if (pointAddress >= 120 && pointAddress <= 159) {
            photovoltaicInverterInverter4 = photovoltaicForwardHandler.parsePointData(address, String.valueOf(data), 0, photovoltaicInverterInverter4);
        } else if (pointAddress >= 160 && pointAddress <= 181) {
            photovoltaicExtra = photovoltaicExtraForwardHandler.parsePointData(address, String.valueOf(data), 0);
        }
    }

    /**
     * 处理104协议遥信信息
     *
     * @param address
     * @param data
     */
    private void parsePhotovoltaicPointYXByAddress(int address, int data) {
        //拿到遥测的序号
        int pointAddress = address - FIRST_ADDRESS_YX;
        if (pointAddress >= 0 && pointAddress <= 27) {
            photovoltaicInvertYx1 = photovoltaicInvertYxForwardHandler.parsePointData(address, String.valueOf(data), 1, photovoltaicInvertYx1);
        } else if (pointAddress >= 28 && pointAddress <= 55) {
            photovoltaicInvertYx2 = photovoltaicInvertYxForwardHandler.parsePointData(address, String.valueOf(data), 1, photovoltaicInvertYx2);
        } else if (pointAddress >= 56 && pointAddress <= 83) {
            photovoltaicInvertYx3 = photovoltaicInvertYxForwardHandler.parsePointData(address, String.valueOf(data), 1, photovoltaicInvertYx3);
        } else if (pointAddress >= 84 && pointAddress <= 111) {
            photovoltaicInvertYx4 = photovoltaicInvertYxForwardHandler.parsePointData(address, String.valueOf(data), 1, photovoltaicInvertYx4);
        } else if (pointAddress >= 112 && pointAddress <= 133) {
            measureControlDeviceYX = nariMeasureControlDeviceYXForwardHandler.parsePointData(address, String.valueOf(data), 1, measureControlDeviceYX);
        }
    }

    @Override
    public PromiseWrapper setControlPromiseWrapper(Channel channel, ChannelPromise channelPromise, ControlCommandCallback controlCommandCallback) {
        promiseWrapper = new PromiseWrapper(channel, channelPromise, controlCommandCallback);
        return promiseWrapper;
    }

    @RequestMapping("/getPhotovoltaic")
    public Photovoltaic getPhotovoltaic() {
        for (int i = 0; i <= 39; i++) {
            parsePhotovoltaicPointYCByAddress(FIRST_ADDRESS_YC + i, 0);
        }
        for (int i = 0; i <= 27; i++) {
            parsePhotovoltaicPointYXByAddress(FIRST_ADDRESS_YX + i, 1);
        }
        //=======
        for (int i = 40; i <= 79; i++) {
            parsePhotovoltaicPointYCByAddress(FIRST_ADDRESS_YC + i, 1);
        }
        for (int i = 28; i <= 55; i++) {
            parsePhotovoltaicPointYXByAddress(FIRST_ADDRESS_YX + i, 0);
        }
        //=======
        for (int i = 80; i <= 119; i++) {
            parsePhotovoltaicPointYCByAddress(FIRST_ADDRESS_YC + i, 2);
        }
        for (int i = 56; i <= 83; i++) {
            parsePhotovoltaicPointYXByAddress(FIRST_ADDRESS_YX + i, 1);
        }
        //=======
        for (int i = 120; i <= 159; i++) {
            parsePhotovoltaicPointYCByAddress(FIRST_ADDRESS_YC + i, 3);
        }
        for (int i = 84; i <= 111; i++) {
            parsePhotovoltaicPointYXByAddress(FIRST_ADDRESS_YX + i, 0);
        }

        Photovoltaic photovoltaic = new Photovoltaic();
        photovoltaic.setPhotovoltaicInverter1(photovoltaicInverterInverter1);
        photovoltaic.setPhotovoltaicInverter2(photovoltaicInverterInverter2);
        photovoltaic.setPhotovoltaicInverter3(photovoltaicInverterInverter3);
        photovoltaic.setPhotovoltaicInverter4(photovoltaicInverterInverter4);
        photovoltaic.setPhotovoltaicExtra(photovoltaicExtra);
        resetNull();
        return photovoltaic;
    }

    public static void main(String[] args) {
        List<PhotovoltaicInverter> list = new ArrayList<>();
        PhotovoltaicInverter photovoltaicInverter = new PhotovoltaicInverter();
        photovoltaicInverter.setA_net_current(1.1f);
        list.add(photovoltaicInverter);
        PhotovoltaicInverter photovoltaicInverter1 = new PhotovoltaicInverter();
        photovoltaicInverter1.setA_net_current(1.2f);
        list.add(photovoltaicInverter1);
        PhotovoltaicInverter photovoltaicInverter2 = new PhotovoltaicInverter();
        photovoltaicInverter2.setA_net_current(1.3f);
        list.add(photovoltaicInverter2);
        String s = JSON.toJSONString(list);
        System.out.println(s);
        List<PhotovoltaicInverter> list1 = JSON.parseObject(s, List.class);
        System.out.println(list1);
    }
}
