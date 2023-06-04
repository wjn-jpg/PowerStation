package com.ntdq.power_station.nettyHandler.tcp.master104.controller;

import com.ntdq.power_station.nettyHandler.tcp.master104.constant.Iec104Constant;
import com.ntdq.power_station.nettyHandler.tcp.master104.domain.MessageDetail;
import com.ntdq.power_station.util.ByteUtil;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping(value = "/test")
@RestController
public class TestController {

    public static List<Integer> noAddressList;

    public static List<Integer> noAddressYXList;

    private static Channel channelYK;

    public static void setChannelYK(Channel channelYK) {
        TestController.channelYK = channelYK;
    }

    public static Map<Integer, Float> addressValueMap = new HashMap<>();

    public static Map<Integer, Integer> addressYXMap = new HashMap<>();

    static {
        for (int i = 16385; i <= 18654; i++) {
            addressValueMap.put(i, null);
        }
    }

    static {
        for (int i = 1; i <= 612; i++) {
            addressYXMap.put(i, null);
        }
    }


    @RequestMapping("/getNoAddress")
    public List<Integer> getNoAddress() {
        if (addressValueMap.size() > 0) {
            noAddressList = addressValueMap.entrySet().stream().filter(integerFloatEntry -> integerFloatEntry.getValue() == null).map(integerFloatEntry -> integerFloatEntry.getKey()).collect(Collectors.toList());
        }
        return noAddressList;
    }

    @RequestMapping("/getNoAddressYX")
    public List<Integer> getNoAddressYX() {
        if (addressYXMap.size() > 0) {
            noAddressYXList = addressYXMap.entrySet().stream().filter(integerFloatEntry -> integerFloatEntry.getValue() == null).map(integerFloatEntry -> integerFloatEntry.getKey()).collect(Collectors.toList());
        }
        return noAddressYXList;
    }

    /**
     * 遥控 65536 地址 转为十六进制发送字节 就是6001H
     * @param address
     */
    @RequestMapping("/controlInteger")
    public void getYK(Integer address) {
        StringBuilder finalCommand = getFinalCommandByInteger(address);
        MessageDetail messageDetail = new MessageDetail(Iec104Constant.SEND_NUMBER_YK).new MessageYK().setAddress(finalCommand.toString());
        channelYK.writeAndFlush(messageDetail);
    }

    /**
     * 遥调
     * @param address
     * @param value
     */
    @RequestMapping("/controlFloat")
    public void getYK(Integer address, Float value) {
        StringBuilder finalCommandAddress = getFinalCommandByInteger(address);
        StringBuilder finalCommandValue = getFinalCommandByFloat(value);
        MessageDetail messageDetail = new MessageDetail(Iec104Constant.SEND_NUMBER_YK_50).new MessageYK().setValue(finalCommandValue.toString()).setAddress(finalCommandAddress.toString());
        channelYK.writeAndFlush(messageDetail);
    }

    private static StringBuilder getFinalCommandByInteger(Integer address) {
        StringBuilder addressHex = new StringBuilder(String.format("%04x", address));
        if (addressHex.length() < 6) {
            int addZeroNums = 6 - addressHex.length();
            for (int i = 0; i < addZeroNums; i++) {
                addressHex.insert(0, 0);
            }
        }
        String[] hexArr = addressHex.toString().split("");
        StringBuilder finalCommand = new StringBuilder();
        for (int i = 2; i >= 0; i--) {
            finalCommand.append(hexArr[i * 2]);
            finalCommand.append(hexArr[i * 2 + 1]);
        }
        return finalCommand;
    }

    private static StringBuilder getFinalCommandByFloat(float address) {
        StringBuilder addressHex = new StringBuilder(ByteUtil.byteArrayToHexString(floatToByteArray(address)));
        if (addressHex.length() < 8) {
            int addZeroNums = 8 - addressHex.length();
            for (int i = 0; i < addZeroNums; i++) {
                addressHex.insert(0, 0);
            }
        }
        String[] hexArr = addressHex.toString().split("");
        StringBuilder finalCommand = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            finalCommand.append(hexArr[i * 2]);
            finalCommand.append(hexArr[i * 2 + 1]);
        }
        return finalCommand;
    }

    public static void main2(String[] args) {
        TestController testController = new TestController();

        int a = 24577;
        String format = String.format("%04x", a);
        System.out.println(format);

//        TestController testController = new TestController();
//        testController.getYK(24577,1);

        //将这个十六进制的字符串拿到 在通过字符串转换为十进制就出来了 我们发送的就是十六进制整体 但是发送的每个十六进制字符串的顺序可能不一样 倒叙
        //006001 是十六进制! 要转换为对应的字节数组才能发送
        String address = "006001";
        int i = Integer.parseInt(address, 16);
        System.out.println(i);
        byte[] bytes = new byte[3];
        bytes[0] = Byte.parseByte(address.substring(4, 6));
        bytes[1] = Byte.parseByte(address.substring(2, 4));
        bytes[2] = Byte.parseByte(address.substring(0, 2));

        System.out.println(encodeHexString(bytes));
        //6位 十六字节 就是3个字节
        byte[] bytes1 = hexStringToBytes("016000");
        byte[] bytes2 = ByteUtil.hexStringToBytes("016000");
        System.out.println(bytes1);
        String s = encodeHexString(bytes1);
        System.out.println(s);
        int i1 = Integer.parseInt("006001", 16);
        System.out.println(i1);

//        System.out.println(new String(bytes));
//        String s = CommonByteUtils.BinaryToHexString(bytes);
//        String[] s1 = s.split(" ");
//        int i1 = Integer.parseInt(s1[2] + s1[1] + s1[0], 16);
//        int i2 = Integer.parseInt("006001", 16);
//        System.out.println(i1);
//        System.out.println(i2);

//        byte[] bytes1 = new byte[3];
//        bytes1[0] = 0x00;
//        bytes1[1] = 0x3A;
//        bytes1[2] = (byte) 0x98;
//        int i3 = ByteUtil.bytesToInt(bytes1);
//        System.out.println(i3);


    }

    public static String encodeHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
            sb.append(" ");
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase(); // 十六进制转大写字母
        int length = hexString.length() / 2; // 获取十六进制的长度，2个字符为一个十六进制
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) {
        //将字节数组 转为1十六进制的字符串! 十六进制转字节数组 和 字节数组转十六进制就是单纯意义每个字面量转
//        byte[] bytes = ByteUtil.hexStringToBytes("016000");
//        System.out.println(bytes);
//        String s = encodeHexString(bytes);
//        System.out.println(s);
//        byte[] bytes = floatToByteArray(100);
//        System.out.println(ByteUtil.byteArrayToHexString(bytes));
        TestController testController = new TestController();
        Float a = 100f;
        testController.getYK(25089,a);
    }

    public static byte[] floatToByteArray(float f) {
        return ByteBuffer.allocate(4).putFloat(f).array();
    }

}
