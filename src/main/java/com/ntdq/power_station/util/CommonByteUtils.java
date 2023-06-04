package com.ntdq.power_station.util;

public class CommonByteUtils {

    //输入流转16进制输出 用于输入流
    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex + " ";
        }
        return result;
    }

    public static Integer ByteArrToInteger(byte[] bytes){
        String data = BinaryToHexString(bytes);
        String trimData = data.trim();
        return Integer.parseInt(trimData,16);
    }

    /**
     * 将byte转换为一个长度为8的byte数组 数组每个值代表bit
     * @param b
     * @return
     */
    public static byte[] getBooleanArray(byte b){
        byte[] array = new byte[8];
        for (int i = 7; i >=0; i--) {
            array[i] = (byte) (b & 1);
            b = (byte) (b>>1);
        }
        return array;
    }

    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }


    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i+1), 16));
        }
        return bytes;
    }



}
