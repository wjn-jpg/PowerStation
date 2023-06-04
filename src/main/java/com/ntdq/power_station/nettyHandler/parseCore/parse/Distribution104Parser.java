package com.ntdq.power_station.nettyHandler.parseCore.parse;

import com.ntdq.power_station.domain.Distribution;
import com.ntdq.power_station.nettyHandler.parseCore.handler.ForwardHandler;
import com.ntdq.power_station.nettyHandler.tcp.master104.controller.TestController;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.YcAndYxProcess;
import com.ntdq.power_station.util.CommonByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;

import static com.ntdq.power_station.util.FeedConversionUtil.*;
import static com.ntdq.power_station.util.FeedConversionUtil.toFloat;

@Component
public class Distribution104Parser implements IParse{

    private final static Logger logger = LoggerFactory.getLogger(Distribution104Parser.class);

    private final ForwardHandler<Distribution> distributionForwardHandler = new ForwardHandler<Distribution>(){};

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
                            logger.info("单点遥信...");
                            // 变电 不带地址 光伏 储能带地址 (遥信)
                            if (Integer.parseInt(hexDataArray[9] + hexDataArray[8], 16) == 20) {//只有第一个地址
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
                                    TestController.addressYXMap.put(finalAddress, dateTrue);
//                                    logger.info("地址" + Addresss + "开始的第" + (i + 1) + "个数据为：" + dateTrue);
                                    logger.info("地址" + finalAddress + "数据为：" + dateTrue);
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
//                                    logger.info("数据地址：" + Addresss);
                                    //数据
                                    String dateN = "";
                                    dateN = hexDataArray[11 + i * 4 + 4];
                                    int dateTrue = Integer.parseInt(dateN);
                                    TestController.addressYXMap.put(Addresss, dateTrue);
                                    logger.info("地址" + Addresss + "的数据为：" + dateTrue);
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
//                                    logger.info("地址" + Addresss1 + "开始的第" + (i + 1) + "个数据为：" + dateTrue);
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
//                                    logger.info("地址" + Addresss + "数据开始的第" + (i + 1) + "个数据为:" + dateTrue);
                                    logger.info("地址" + finalAddress + "个数据为:" + dateTrue);
                                    //log.info("品质值：" + DateArray[11 + i * 6 + 6]);
                                }
                            }
                            break;
                        case 13: //遥测 短浮点数
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
//                                    logger.info("地址" + Addresss1 + "开始的第" + (i + 1) + "个数据为：" + dateTrue);
                                    logger.info("地址" + finalAddress + "数据为：" + dateTrue);
                                    TestController.addressValueMap.put(finalAddress, dateTrue);
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
//                                    logger.info("地址" + dateAddressHasP + "开始的第" + (i + 1) + "个数据为：" + dateTrue);
//                                    logger.info("地址" + dateAddressHasP + "数据为：" + dateTrue);
                                    logger.info("地址" + finalAddress + "数据为：" + dateTrue);
                                    //log.info("品质值：" + DateArray[11 + i * 8 + 8]);
                                    TestController.addressValueMap.put(finalAddress, dateTrue);
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
                            break;

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAddressFieldMap(Map<Integer, String> addressFieldMap) {
        distributionForwardHandler.setAddressFieldNameMap(addressFieldMap);
    }
}
