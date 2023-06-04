package com.ntdq.power_station.authority.client;

import org.eclipse.paho.client.mqttv3.*;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*
 * mqtt回调处理类
 */

public class MqttConsumerCallback implements MqttCallbackExtended {

    private MqttClient client;
    private MqttConnectOptions options;
    private String[] topic;
    private int[] qos;

    public MqttConsumerCallback(MqttClient client, MqttConnectOptions options, String[] topic, int[] qos) {
        this.client = client;
        this.options = options;
        this.topic = topic;
        this.qos = qos;
    }

/*
     * 断开重连
   */

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("MQTT连接断开，发起重连......");
        try {
            if (null != client && !client.isConnected()) {
                client.reconnect();
                System.out.println("尝试重新连接");
            } else {
                client.connect(options);
                System.out.println("尝试建立新连接");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/**
     * 接收到消息调用令牌中调用
     */

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

        //log.info("deliveryComplete---------" + Arrays.toString(topic));
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws UnsupportedEncodingException {
        byte[] payload = message.getPayload();
        String s = new String(payload, "UTF-8");
        System.out.println("转发接受到的消息:"+s);
    }

/**
     * 消息处理
     */

    @Override
//    public void messageArrived(String topic, MqttMessage message) {
//        try {
//
//            //清除每组录波数据集合
//            Global.waveformDataList.clear();
//            //清除所有录波数据
//            Global.datData.clear();
//            String msg = new String(message.getPayload(),"UTF-8");//获取订阅的消息
//            Global.log.info("收到topic:" + topic);
//            System.out.println("收到topic:" + topic );
//            byte[] payload = message.getPayload();
//
//            Global.byteToString = DataUtil.toHexString(payload);
//            Global.byteToString = Global.byteToString.toUpperCase();
//            System.out.println(Global.byteToString.length());
//            System.out.println(Global.byteToString);
//            Global.log.info("数据处理方式"+Global.byteToString.substring(0,4));
//            System.out.println("数据处理方式"+Global.byteToString.substring(0,4));
//
//            Global.log.info("设备代号ID"+Global.byteToString.substring(4,8));
//            System.out.println("设备代号ID"+Global.byteToString.substring(4,8));
//
//            Global.log.info("协议类型"+Global.byteToString.substring(8,12));
//            System.out.println("协议类型"+Global.byteToString.substring(8,12));
//
//            Global.log.info("预留"+Global.byteToString.substring(12,16));
//            System.out.println("预留"+Global.byteToString.substring(12,16));
//
//            Global.log.info("上报设备信息时间戳"+Global.byteToString.substring(16,32));
//            System.out.println("上报设备信息时间戳"+Global.byteToString.substring(16,32));
//
//            Global.log.info("数据"+Global.byteToString.substring(32,Global.byteToString.length()));
//            System.out.println("数据"+Global.byteToString.substring(32,Global.byteToString.length()));
//
//            if(topic.equals("app/gw/v1/data")) {
//                if (Global.byteToString.substring(0, 4).equals("0001") && Global.byteToString.substring(4, 8).equals("0002") && Global.byteToString.substring(8, 12).equals("0003")) {
//                    Global.log.info("英锐祺Lora设备数据解析");
//                    System.out.println("英锐祺Lora设备数据解析");
//                    System.out.println("==============================================================================");
//
//                    Global.data = Global.byteToString.substring(32,Global.byteToString.length());
//                    //System.out.println("数据转换成比特长度" + data.length());
//                    System.out.println(Global.byteToString.substring(32,Global.byteToString.length()));
//                    Global.log.info("传感器ID:" + Global.data.substring(0, 12));
//                    System.out.println("传感器ID:" + Global.data.substring(0, 12));
//                    Global.log.info("参数个数:" + Global.data.substring(12,13) + "==" + Integer.parseInt(Global.data.substring(12,13)));
//                    System.out.println("参数个数:" + Global.data.substring(12,13) + "==" + Integer.parseInt(Global.data.substring(12,13)));
//                    System.out.println(DataUtil.hexStr2BinStr(Global.data.substring(12, 14)));
//                    Global.log.info("分片指示:" + DataUtil.hexStr2BinStr(Global.data.substring(12, 14)).substring(4,5));
//                    System.out.println("分片指示:" + DataUtil.hexStr2BinStr(Global.data.substring(12, 14)).substring(4,5));
//                    Global.type = DataUtil.hexStr2BinStr(Global.data.substring(12, 14)).substring(5,8);
//                    Global.log.info("报文类型:" + Global.type);
//                    System.out.println("报文类型:" + Global.type);
//                    if (Global.type.equals("000")) {//监测数据报文
//                        int id = 1;
//                        for (int j = 14,i=0; i < Integer.parseUnsignedInt(Global.data.substring(12,13)); i++) {
//                            Global.realData = "";
//                            System.out.println(Global.data.substring(j + 2, j + 4) + Global.data.substring(j, j + 2));
//                            String nm = DataUtil.hexStr2BinStr(Global.data.substring(j + 2, j + 4) + Global.data.substring(j, j + 2));
//                            System.out.println(nm);
//                            Global.log.info("参数类型：" + nm.substring(0, 14));
//                            System.out.println("参数类型：" + nm.substring(0, 14));
//                            Global.log.info("数据长度字段指示位：" + nm.substring(14, 16));
//                            System.out.println("数据长度字段指示位：" + nm.substring(14, 16));
//                            if (Integer.parseUnsignedInt(nm.substring(14, 16), 2) == 0) {
//                                Global.log.info("数据长度字段指示位为4字节");
//                                System.out.println("数据长度字段指示位为4字节");
//                                //System.out.println("数据长度："+data.substring(j+16,j+48)+"=="+Integer.parseUnsignedInt(data.substring(j+16,j+48),2));
//                                Global.log.info("数据内容：" + Global.data.substring(j + 4, j + 12));
//                                System.out.println("数据内容：" + Global.data.substring(j + 4, j + 12));
//                                Global.realData = Global.data.substring(j + 10, j + 12)+Global.data.substring(j + 8, j + 10)+Global.data.substring(j + 6, j + 8)+Global.data.substring(j + 4, j + 6);
//                                Global.log.info("实际数据内容：" + Global.realData);
//                                System.out.println("实际数据内容：" + Global.realData);
//                                j = j + 12;
//
//                            } else if (Integer.parseUnsignedInt(nm.substring(14, 16), 2) == 1) {
//                                Global.log.info("数据长度字段指示位为1字节");
//                                System.out.println("数据长度字段指示位为1字节");
//                                Global.log.info("数据长度："+Global.data.substring(j+4,j+6)+"=="+Integer.parseInt(Global.data.substring(j+4,j+6)));
//                                System.out.println("数据长度："+Global.data.substring(j+4,j+6)+"=="+Integer.parseInt(Global.data.substring(j+4,j+6)));
//                                System.out.println("数据内容：" + Global.data.substring(j + 6, j + 6+Integer.parseInt(Global.data.substring(j+4,j+6))*2));
//                                for(int k = j + 6+Integer.parseInt(Global.data.substring(j+4,j+6))*2;k>j + 6;k=k-2){
//                                        System.out.println(Global.data.substring(k-2,k));
//                                        Global.realData+=Global.data.substring(k-2,k);
//                                }
//                                Global.log.info("实际数据："+Global.realData);
//                                System.out.println("实际数据："+Global.realData);
//                                j = j + 6+Integer.parseInt(Global.data.substring(j+4,j+6))*2;
//
//                            } else if (Integer.parseUnsignedInt(nm.substring(14, 16), 2) == 2) {
//                                Global.log.info("数据长度字段指示位为2字节");
//                                System.out.println("数据长度字段指示位为2字节");
//                                Global.log.info("数据长度："+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6)+"=="+Integer.parseInt(Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6)));
//                                System.out.println("数据长度："+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6)+"=="+Integer.parseInt(Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6)));
//                                System.out.println("数据内容：" + Global.data.substring(j + 8, j + 8+2*Integer.parseInt(Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6))));
//                                for(int k = j + 8+2*Integer.parseInt(Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6));k>j + 8;k=k-2){
//                                    System.out.println(Global.data.substring(k-2,k));
//                                    Global.realData+=Global.data.substring(k-2,k);
//                                }
//                                Global.log.info("实际数据："+Global.realData);
//                                System.out.println("实际数据："+Global.realData);
//                                j = j + 8+2*Integer.parseInt(Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6));
//
//                            } else if (Integer.parseUnsignedInt(nm.substring(14, 16), 2) == 3) {
//                                Global.log.info("数据长度字段指示位为3字节");
//                                System.out.println("数据长度字段指示位为3字节");
//                                Global.log.info("数据长度："+Global.data.substring(j+8,j+10)+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6)+"=="+Integer.parseInt(Global.data.substring(j+8,j+10)+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6)));
//                                System.out.println("数据长度："+Global.data.substring(j+8,j+10)+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6)+"=="+Integer.parseInt(Global.data.substring(j+8,j+10)+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6)));
//                                System.out.println("数据内容：" + Global.data.substring(j + 10, j + 10+Integer.parseInt(Global.data.substring(j+8,j+10)+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6))*2));
//                                for(int k = j + 10+Integer.parseInt(Global.data.substring(j+8,j+10)+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6))*2;k>j + 10;k=k-2){
//                                    System.out.println(Global.data.substring(k-2,k));
//                                    Global.realData+=Global.data.substring(k-2,k);
//                                }
//                                Global.log.info("实际数据："+Global.realData);
//                                System.out.println("实际数据："+Global.realData);
//                                j = j + 10+Integer.parseInt(Global.data.substring(j+8,j+10)+Global.data.substring(j+6,j+8)+Global.data.substring(j+4,j+6))*2;
//
//                            }
//
//                            //查询数据解析格式
//                            if(SqlUtil.selectValTypeIDByValCode(nm.substring(0, 14)).equals("0")){
//                                Global.log.info("厂商自定义预留字段"+Long.parseLong(Global.realData, 16));
//                                System.out.println("厂商自定义预留字段");
//                                System.out.println(Long.parseLong(Global.realData, 16));//十六进制转十进制
//                                SqlUtil.updateRealAna(Global.data.substring(0, 12), SqlUtil.selectValNameIDByValCode(nm.substring(0, 14)),String.valueOf(Long.parseLong(Global.realData, 16)), GetTime.getTime());
//
//                                //数据存放到生成xml文件的集合中
//                               /* XmlData xml = new XmlData();
//                                xml.setId(id);
//                                id++;
//                                xml.setName( SqlUtil.selectValNameIDByValCode(nm.substring(0, 14)));
//                                xml.setValue(String.valueOf(Long.parseLong(Global.realData, 16)));
//                                Global.xmlData.add(xml);*/
//                            }else if(SqlUtil.selectValTypeIDByValCode(nm.substring(0, 14)).equals("1")){
//                                Global.log.info("无符号整型:"+Long.parseLong(Global.realData, 16));
//                                System.out.println("无符号整型");
//                                System.out.println(Long.parseLong(Global.realData, 16));//十六进制转整型
//                                SqlUtil.updateRealAna(Global.data.substring(0, 12), SqlUtil.selectValNameIDByValCode(nm.substring(0, 14)),String.valueOf(Long.parseLong(Global.realData, 16)), GetTime.getTime());
//
//
//                                //数据存放到生成xml文件的集合中
//                                /*XmlData xml = new XmlData();
//                                xml.setId(id);
//                                id++;
//                                xml.setName(SqlUtil.selectValNameIDByValCode(nm.substring(0, 14)));
//                                xml.setValue(String.valueOf(Long.parseLong(Global.realData, 16)));
//                                Global.xmlData.add(xml);*/
//                            }else if(SqlUtil.selectValTypeIDByValCode(nm.substring(0, 14)).equals("2")){
//                                Global.log.info("浮点数:"+Float.intBitsToFloat(new BigInteger(Global.realData, 16).intValue()));
//                                System.out.println("浮点数");
//                                System.out.println(Float.intBitsToFloat(new BigInteger(Global.realData, 16).intValue()));//十六进制转浮点数
//                                SqlUtil.updateRealAna(Global.data.substring(0, 12), SqlUtil.selectValNameIDByValCode(nm.substring(0, 14)),String.valueOf(Float.intBitsToFloat(new BigInteger(Global.realData, 16).intValue())), GetTime.getTime());
//
//                                //数据存放到生成xml文件的集合中
//                                /*XmlData xml = new XmlData();
//                                xml.setId(id);
//                                id++;
//                                xml.setName(SqlUtil.selectValNameIDByValCode(nm.substring(0, 14)));
//                                xml.setValue(String.valueOf(Float.intBitsToFloat(new BigInteger(Global.realData, 16).intValue())));
//                                Global.xmlData.add(xml);*/
//                            }
//                        }
//                        Global.log.info("校验" + Global.byteToString.substring(Global.byteToString.length()-4,Global.byteToString.length()));
//                        //XmlUtilLora.create();
//                        //System.out.println("**************生成Lora数据的xml文件***************");
//                        Global.xmlData.clear();
//                    } else if (Global.type.equals("001")) {//监测数据响应报文
//                        Global.log.info("报文内容" + Global.byteToString.substring(14, 16));//数据发送状态：①0xFF成功  ②0x00失败
//                        Global.log.info("校验" + Global.byteToString.substring(Global.byteToString.length()-4,Global.byteToString.length()));
//
//                    }
//                    //System.out.println("data数据转换内容"+DataUtil.convertHexToString(s.substring(32,s.length())));
//                }
//                else if(Global.byteToString.substring(0,4).equals("0001") && Global.byteToString.substring(4,8).equals("0003") && Global.byteToString.substring(8,12).equals("0004")){
//                    //System.out.println("ASCII码转换为16进制" + DataUtil.convertHexToString(s.substring(32, s.length())));
//                    System.out.println("****************************************NT500*****************************************");
//                    Global.log.info("NT500设备数据解析");
//                    System.out.println("NT500设备数据解析");
//                    String NT500 = Global.byteToString.substring(32, Global.byteToString.length());
//                    Global.log.info("录波头"+NT500.substring(400,472));
//                    System.out.println("录波头"+NT500.substring(400,472));
//
//                    //录波序号
//                    Global.cfgNum = Integer.parseInt(NT500.substring(400,404),16);
//                    Global.log.info("录波序号"+Global.cfgNum);
//                    System.out.println("录波序号"+Global.cfgNum);
//
//
//                    if(!ComtradeUtil.cfgNumisHas()){//判断当前录波序号是否已解析
//                        //在录波序号集合中添加当前序号
//                        Global.ComtradeNumList.add(Global.byteToString.substring(12,16)+"-"+Global.cfgNum);
//                        //Global.ComtradeNumList.add(Global.cfgNum);
//
//                        //操作类型
//                        if(NT500.substring(404,408).equals("0001")){
//                            Global.cfgType = "合闸";
//                        }else if(NT500.substring(404,408).equals("0002")){
//                            Global.cfgType = "储能";
//                        }else if(NT500.substring(404,408).equals("0000")){
//                            Global.cfgType = "分闸";
//                        }
//                        Global.log.info("操作类型"+Global.cfgType);
//                        System.out.println("操作类型"+Global.cfgType);
//
//                        //触发时间
//                        Global.cfgY = String.valueOf(Integer.parseInt(NT500.substring(408,412),16));//年
//
//                        Global.cfgM = String.valueOf(Integer.parseInt(NT500.substring(412,416),16));//月
//
//                        Global.cfgD = String.valueOf(Integer.parseInt(NT500.substring(416,420),16));//日
//
//                        Global.cfgH = String.valueOf(Integer.parseInt(NT500.substring(420,424),16));//时
//
//                        Global.cfgm = String.valueOf(Integer.parseInt(NT500.substring(424,428),16));//分
//
//                        Global.cfgS = String.valueOf(Integer.parseInt(NT500.substring(428,432),16));//秒
//
//                        Global.cfgU = String.valueOf(Integer.parseInt(NT500.substring(432,436),16));//毫秒
//
//                        Global.log.info("触发时间"+Global.cfgY+" "+Global.cfgM+" "+Global.cfgD+" "+Global.cfgH+" "+Global.cfgm+" "+Global.cfgS+" "+Global.cfgU);
//                        System.out.println("触发时间"+Global.cfgY+" "+Global.cfgM+" "+Global.cfgD+" "+Global.cfgH+" "+Global.cfgm+" "+Global.cfgS+" "+Global.cfgU);
//                        System.out.println("触发时间"+Global.cfgY+" "+Global.cfgM+" "+Global.cfgD+" "+Global.cfgH+" "+Global.cfgm+" "+Global.cfgS+" "+(Integer.parseInt(Global.cfgU)+20));
//
//                        //通道系数
//                        Global.cfgOne = Global.df.format(Float.intBitsToFloat(Integer.valueOf(NT500.substring(444,448)+NT500.substring(440,444), 16)));
//                        Global.cfgTwo = Global.df.format(Float.intBitsToFloat(Integer.valueOf(NT500.substring(452,456)+NT500.substring(448,452), 16)));
//                        Global.cfgThree = Global.df.format(Float.intBitsToFloat(Integer.valueOf(NT500.substring(460,464)+NT500.substring(456,460), 16)));
//                        Global.cfgFour = Global.df.format(Float.intBitsToFloat(Integer.valueOf(NT500.substring(468,472)+NT500.substring(464,468), 16)));
//                        Global.log.info("通道系数1"+Global.cfgOne);
//                        System.out.println(Global.cfgOne);
//                        Global.log.info("通道系数2"+Global.cfgTwo);
//                        System.out.println(Global.cfgTwo);
//                        Global.log.info("通道系数3"+Global.cfgThree);
//                        System.out.println(Global.cfgThree);
//                        Global.log.info("通道系数4"+Global.cfgFour);
//                        System.out.println(Global.cfgFour);
//
//
//                        for(int i = 0;i<NT500.length();i=i+472){
//                            //System.out.println("录波数据"+NT500.substring(i,i+400));
//                            Global.waveformData = NT500.substring(i,i+400);//100个数据点的录波数据
//                            for(int j = 0;j < 400; j=j+4){
//                                Global.waveformDataList.add(Global.waveformData.substring(j+2,j+4)+Global.waveformData.substring(j,j+2));
//                            }
//                        }
//                        System.out.println(Global.waveformDataList.size());
//                        Global.fileTime = GetTime.getTimeT();
//                        ComtradeUtil.createDat();
//                        ComtradeUtil.createCfg();
//
//                        //录波文件存库
//
//                        if(SqlUtil.selectFileHas(Global.filePath+String.valueOf(Global.byteToString.substring(12,16))+"\\"+Global.cfgNum)==null){
//
//                            SqlUtil.insertNt500cfg(Global.byteToString.substring(12,16),
//                                    Global.filePath+String.valueOf(Global.byteToString.substring(12,16))+"\\"+Global.cfgNum,
//                                    Global.deviceType.get(Global.cfgType)+"_"+Global.cfgNum+"_"+Global.cfgY+Global.cfgM+Global.cfgD+Global.cfgH+Global.cfgm+Global.cfgS+Global.cfgU,
//                                    Global.fileTime,
//                                    Global.cfgType);
//                        }
//
//                        //XmlUtilNT501.create();
//                        //System.out.println("**************生成录波数据的xml文件***************");
//
//                    }
//
//
//                }
//                //System.out.println("收到topic:" + topic + " 消息：" + message.getPayloadBuffer());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("处理mqtt消息异常:" + e);
//        }
//    }

/*
     * mqtt连接后订阅主题

*/
    public void connectComplete(boolean b, String s) {
        try {
            if (null != topic && null != qos) {
                if (client.isConnected()) {
                    client.subscribe(topic, qos);
                    System.out.println("mqtt连接成功，客户端ID：" + PropertiesUtil.MQTT_CLIENT_ID);
                    System.out.println("--订阅主题:：" + Arrays.toString(topic));
                } else {
                    System.out.println("mqtt连接失败，客户端ID：" + PropertiesUtil.MQTT_CLIENT_ID);
                }
            }
        } catch (Exception e) {
            System.out.println("mqtt订阅主题异常:" + e);
        }
    }
}

