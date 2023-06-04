package com.ntdq.power_station.nettyHandler.tcp.master104.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static cn.hutool.core.convert.Convert.toFloat;
import static com.ntdq.power_station.util.CommonByteUtils.BinaryToHexString;
import static com.ntdq.power_station.util.FeedConversionUtil.HexB;
import static com.ntdq.power_station.util.FeedConversionUtil.hexStr2BinStr;

public class Tcp104ServerHandler extends ChannelInboundHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(Tcp104ServerHandler.class);

    //主站
    private int sendNumMaster = 0;
    private int receiverNumMaster = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);

        String date = BinaryToHexString(bytes);
        log.info("接收"+date);
        String[] DateArray = date.split(" ");

        if (DateArray[0].equals("68")) {
            int num = Integer.parseInt(DateArray[1], 16);
            if (num == 4) {
                if (DateArray[2].equals("0B") && DateArray[3].equals("00") && DateArray[4].equals("00") && DateArray[5].equals("00")) {//U帧
                    log.info("U帧接收成功");
                    sendNumMaster = 0;
                    receiverNumMaster = 0;

                    //总召唤
                    String sHex = "68 0E 00 00 00 00 64 01 06 00 01 00 00 00 00 14"; // 输入十六进制字符串
                    log.info("发送"+sHex);
                    ctx.channel().writeAndFlush(sHex);
//                    send(sHex);
                    sendNumMaster = 1;
                } else if (DateArray[2].equals("43") && DateArray[3].equals("00") && DateArray[4].equals("00") && DateArray[5].equals("00")) {//68 04 83 00 00 00
                    log.info("激活");
                    //总召唤
                    String sHex = "68 04 83 00 00 00"; // 输入十六进制字符串
                    log.info("发送"+sHex);
                    ctx.channel().writeAndFlush(sHex);
                    //send(sHex);
                }
            } else {//I帧

                //发送序号
                int sendNum = Integer.parseInt(DateArray[3] + DateArray[2], 16) / 2;
                receiverNumMaster = sendNum;//主站接收次数

                //接收序号
                int receiverNum = Integer.parseInt(DateArray[5] + DateArray[4], 16) / 2;
                if (receiverNum != sendNumMaster) {
                    log.info("出现漏帧或重复帧");
                } else {

                    log.info("从站发送"+sendNum+"接收"+receiverNum);
                    if (DateArray.length > 6) {


                        //获取可变结构限定词，及数据个数
                        String hexStr2BinStr = hexStr2BinStr(DateArray[7]);
                        int dateNum = Integer.valueOf(hexStr2BinStr.substring(1, hexStr2BinStr.length()), 2);

                        //传输原因
                        switch (Integer.parseInt(DateArray[9] + DateArray[8], 16)) {
                            case 3://突发
                                log.info("突发");
                                break;
                            case 7://激活确认
                                log.info("激活确认");
                                break;
                            case 10://激活终止
                                log.info("激活终止");
                                break;
                            case 20://响应站召唤
                                log.info("响应站召唤");
                                break;

                        }


                        //公共地址
                        int publicAddress = Integer.parseInt(DateArray[11] + DateArray[10], 16);

                        //类型标识
                        switch (Integer.parseInt(DateArray[6], 16)) {
                            case 1://单点遥信
                                if(Integer.parseInt(DateArray[9] + DateArray[8], 16)==20) {//只有第一个地址
                                    int Addresss = Integer.parseInt(DateArray[14] + DateArray[13] + DateArray[12], 16);
                                    log.info("数据个数：" + dateNum);
                                    log.info("数据地址：" + Addresss);
                                    for (int i = 0; i < dateNum; i++) {
                                        String dateN = "";
                                        for (int j = 1; j > 0; j--) {
                                            dateN += DateArray[14 + i * 1 + j];
                                            //log.info(DateArray[13+i*4+j]);
                                        }
                                        log.info(dateN);
                                        int dateTrue = Integer.parseInt(dateN);
                                        log.info("地址" + Addresss + "开始的第" +( i + 1) + "个数据为：" + dateTrue);

                                        //log.info("品质值：" + DateArray[14 + i * 5 + 5]);
                                        //数据库更新
                                        //updateDgt(String.valueOf(Addresss+i), String.valueOf(dateTrue));
                                    }
                                }else{
                                    log.info("数据个数：" + dateNum);
                                    for (int i = 0; i < dateNum; i++) {

                                        //地址
                                        int Addresss = 0;
                                        String AddressN = "";
                                        for (int x = 3;x>0;x--){

                                            AddressN += DateArray[11 + i * 4 + x];

                                        }
                                        Addresss = Integer.parseInt(AddressN, 16);
                                        log.info("数据地址：" + Addresss);

                                        //数据
                                        String dateN = "";
                                        dateN = DateArray[11 + i * 4 +4];
                                        //log.info(DateArray[13+i*4+j]);
                                        log.info(dateN);
                                        int dateTrue = Integer.parseInt(dateN);
                                        log.info("地址" + Addresss + "的数据为：" + dateTrue);

                                        //数据库更新
                                        //updateDgt(String.valueOf(Addresss), String.valueOf(dateTrue));
                                    }
                                }
                                break;
                            case 2://遥控
                                int DAddress = Integer.parseInt(DateArray[14] + DateArray[13] + DateArray[12], 16);

                                //遥控状态存库
                                //updateControl(String.valueOf(DAddress), String.valueOf(Integer.parseInt(DateArray[15] , 16)));
                                log.info("数据地址：" + DAddress+"，空开状态"+Integer.parseInt(DateArray[15] , 16));
                                break;
                            case 9:
                                if(Integer.parseInt(DateArray[9] + DateArray[8], 16)==20) {//只有第一个地址
                                    int Addresss1 = Integer.parseInt(DateArray[14] + DateArray[13] + DateArray[12], 16);
                                    log.info("数据个数：" + dateNum);
                                    log.info("数据地址：" + Addresss1);
                                    for (int i = 0; i < dateNum; i++) {
                                        String dateN = "";
                                        for (int j = 2; j > 0; j--) {
                                            dateN += DateArray[14 + i * 3 + j];
                                            //log.info(DateArray[13+i*4+j]);
                                        }
                                        log.info(dateN);
                                        float dateTrue = 0;
                                        if(dateN.substring(0,1).equals("F")){
                                            String valueOf = String.valueOf(Long.parseLong(HexB(dateN), 16) + Long.parseLong("01", 16));

                                            dateTrue = 0-Integer.parseInt(valueOf);
                                        }else {

                                            dateTrue = Integer.parseInt(dateN, 16);
                                        }
                                        log.info("地址" + Addresss1 + "开始的第" +( i + 1) + "个数据为：" + dateTrue);

                                        //log.info("品质值：" + DateArray[14 + i * 3 + 3]);
                                        //数据库更新
                                        //updateAna(String.valueOf(Addresss1+i), String.valueOf(dateTrue));
                                    }
                                }else {


                                    log.info("数据个数：" + dateNum);
                                    for (int i = 0; i < dateNum; i++) {

                                        //地址
                                        int Addresss = 0;
                                        String AddressN = "";
                                        for (int x = 3;x>0;x--){

                                            AddressN += DateArray[11 + i * 6 + x];

                                        }
                                        Addresss = Integer.parseInt(AddressN, 16);
                                        log.info("数据地址：" + Addresss);

                                        //数据
                                        String dateN = "";
                                        for (int j = 2; j > 0; j--) {
                                            dateN += DateArray[11 + i * 6 + j+3];
                                            //log.info(DateArray[13+i*4+j]);
                                        }

                                        log.info(dateN);
                                        float dateTrue = 0;
                                        if(dateN.substring(0,1).equals("F")){
                                            String valueOf = String.valueOf(Long.parseLong(HexB(dateN), 16) + Long.parseLong("01", 16));

                                            dateTrue = 0-Integer.parseInt(valueOf);
                                        }else {

                                            dateTrue = Integer.parseInt(dateN, 16);
                                        }
                                        log.info("地址" + Addresss + "数据为：" + dateTrue);

                                        //log.info("品质值：" + DateArray[11 + i * 6 + 6]);
                                        //数据库更新
                                        //updateAna(String.valueOf(Addresss), String.valueOf(dateTrue));
                                    }
                                }

                                break;


                            case 13://短浮点遥测值 带品质描述，不带时标
                                if(Integer.parseInt(DateArray[9] + DateArray[8], 16)==20){//只有第一个地址
                                    int Addresss1 = Integer.parseInt(DateArray[14] + DateArray[13] + DateArray[12], 16);
                                    log.info("数据个数：" + dateNum);
                                    log.info("数据地址：" + Addresss1);
                                    for (int i = 0; i < dateNum; i++) {
                                        String dateN = "";
                                        for (int j = 4; j > 0; j--) {
                                            dateN += DateArray[14 + i * 5 + j];
                                            //log.info(DateArray[13+i*4+j]);
                                        }
                                        log.info(dateN);
                                        float dateTrue = Float.valueOf(toFloat(dateN));
                                        log.info("地址" + Addresss1 + "开始的第" +( i + 1) + "个数据为：" + dateTrue);

                                        //log.info("品质值：" + DateArray[14 + i * 5 + 5]);
                                        //数据库更新
                                        //updateAna(String.valueOf(Addresss1+i), String.valueOf(dateTrue));
                                    }
                                }else {//数据都包含地址位

                                    int dateAddressHasP = Integer.parseInt(DateArray[14] + DateArray[13] + DateArray[12], 16);
                                    log.info("数据个数：" + dateNum);
                                    log.info("数据地址：" + dateAddressHasP);
                                    for (int i = 0; i < dateNum; i++) {
                                        String dateN = "";
                                        for (int j = 4; j > 0; j--) {
                                            dateN += DateArray[11 + i * 8 + j+3];
                                            //log.info(DateArray[13+i*4+j]);
                                        }
                                        log.info(dateN);
                                        float dateTrue = Float.valueOf(toFloat(dateN));
                                        log.info("地址" + dateAddressHasP + "数据为：" + dateTrue);

                                        //log.info("品质值：" + DateArray[11 + i * 8 + 8]);

                                        //数据库更新
                                        //updateAna(String.valueOf(dateAddressHasP+i), String.valueOf(dateTrue));
                                    }
                                }

                                break;
                            case 36://短浮点遥测值 带品质描述，带时标
                                int dateAddress = Integer.parseInt(DateArray[14] + DateArray[13] + DateArray[12], 16);
                                log.info("数据个数：" + dateNum);
                                log.info("数据地址：" + dateAddress);
                                for (int i = 0; i < dateNum; i++) {
                                    String dateN = "";
                                    for (int j = 4; j > 0; j--) {
                                        dateN += DateArray[14 + i * 12 + j];
                                        //log.info(DateArray[13+i*4+j]);
                                    }
                                    log.info(dateN);
                                    float dateTrue = Float.valueOf(toFloat(dateN));
                                    log.info("地址" + dateAddress + "开始的第" + i + 1 + "个数据为：" + dateTrue);


                                    //数据库更新
                                    //updateAna(String.valueOf(dateAddress), String.valueOf(dateTrue));

                                    log.info("品质值：" + DateArray[18 + i * 12 + 1]);

                                    int i1 = Integer.parseInt(DateArray[18 + i * 12 + 2] + DateArray[18 + i * 12 + 3], 16);//毫秒
                                    int i2 = Integer.valueOf(hexStr2BinStr(DateArray[18 + i * 12 + 4]).substring(2, 8), 2);//分钟
                                    int i3 = Integer.valueOf(hexStr2BinStr(DateArray[18 + i * 12 + 5]).substring(3, 8), 2);//小时
                                    int i4 = Integer.valueOf(hexStr2BinStr(DateArray[18 + i * 12 + 6]).substring(0, 3), 2);//周
                                    int i5 = Integer.valueOf(hexStr2BinStr(DateArray[18 + i * 12 + 6]).substring(3, 8), 2);//日
                                    int i6 = Integer.valueOf(hexStr2BinStr(DateArray[18 + i * 12 + 7]).substring(4, 8), 2);//月
                                    int i7 = Integer.valueOf(hexStr2BinStr(DateArray[18 + i * 12 + 8]).substring(1, 8), 2);//年

                                    log.info("接收数据时间：" + i7 + "年" + i6 + "月" + i5 + "日（周" + i4 + "）" + i3 + "时" + i2 + "分" + i1 + "秒");


                                }


                                break;
                            case 45://单点控制
                                log.info("单点控制");

                                break;
                            case 100://总召唤

                                break;

                        }
                    }
                }


            }
        }

    }


}
