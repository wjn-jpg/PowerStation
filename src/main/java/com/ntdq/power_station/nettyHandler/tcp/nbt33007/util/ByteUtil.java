package com.ntdq.power_station.nettyHandler.tcp.nbt33007.util;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * 
* @ClassName: ByteUtil  
* @Description: byte 工具类 
* @author YDL 
* @date 2020年5月13日
 */
public class ByteUtil {
	
	/**
	 * 
	* @Title: intToByteArray  
	* @Description: int 转换成 byte数组 
	* @param @param i
	* @param @return 
	* @return byte[]   
	* @throws
	 */
	public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }
	
	/**
	* @Title: shortToByteArray  
	* @Description: short 转换成 byte[] 
	* @param @param val
	* @param @return 
	* @return byte[]   
	* @throws
	 */
	public static byte[] shortToByteArray(short val) {
		byte[] b = new byte[2];
		b[0] = (byte) ((val >> 8) & 0xff);
		b[1] = (byte) (val & 0xff);
		return b;
	}
	
	/**
	 * 
	* @Title: byteArrayToInt  
	* @Description: byte[] 转换成 int
	* @param @param bytes
	* @param @return 
	* @return int   
	* @throws
	 */
	public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (3 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }
	
	/**
	 * 
	* @Title: byteArrayToShort  
	* @Description: byte[] 转换成short 
	* @param @param bytes
	* @param @return 
	* @return short   
	* @throws
	 */
	public static short byteArrayToShort(byte[] bytes) {
        short value = 0;
        for (int i = 0; i < 2; i++) {
            int shift = (1 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }
	
	
//	/**
//	 * 
//	* @Title: listToBytes  
//	* @Description: TODO 
//	* @param @param byteList
//	* @param @return 
//	* @return byte[]   
//	* @throws
//	 */
//	public static byte[] listToBytes(List<Byte> byteList) {
//		byte[] bytes = new byte[byteList.size()];
//		int index = 0;
//		for (Byte item : byteList) {
//			bytes[index++] = item;
//		}
//		return bytes;
//	}
	
	/**
	 * 
	* @Title: date2HByte  
	* @Description: 日期转换成 CP56Time2a
	* @param @param date
	* @param @return 
	* @return byte[]   
	* @throws
	 */
    public static byte[] date2Hbyte(Date date) {
    	ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 毫秒需要转换成两个字节其中 低位在前高位在后 
        // 先转换成short
        int millisecond = calendar.get(Calendar.SECOND) * 1000 + calendar.get(Calendar.MILLISECOND);
        
        // 默认的高位在前
        byte[] millisecondByte = intToByteArray(millisecond);
        bOutput.write((byte) millisecondByte[3]);
        bOutput.write((byte) millisecondByte[2]);
        
        // 分钟 只占6个比特位 需要把前两位置为零 
        bOutput.write((byte) calendar.get(Calendar.MINUTE));
        // 小时需要把前三位置零
        bOutput.write((byte) calendar.get(Calendar.HOUR_OF_DAY));
        // 星期日的时候 week 是0 
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.SUNDAY) {
        	week = 7;
        } else {
        	week--;
        } 
        // 前三个字节是 星期 因此需要将星期向左移5位  后五个字节是日期  需要将两个数字相加 相加之前需要先将前三位置零
        bOutput.write((byte) (week << 5) + (calendar.get(Calendar.DAY_OF_MONTH)));
        // 前四字节置零
        bOutput.write((byte) ((byte) calendar.get(Calendar.MONTH) + 1));
        bOutput.write((byte) (calendar.get(Calendar.YEAR) - 2000));
        return bOutput.toByteArray();
    }
    
    
    /**
	 * 
	* @Title: date2HByte  
	* @Description:CP56Time2a转换成  时间
	* @param @param date
	* @param @return 
	* @return byte[]   
	* @throws
	 */
    public static Date  byte2Hdate(byte[] dataByte) {
        int year = (dataByte[6] & 0x7F) + 2000;
        int month = dataByte[5] & 0x0F;
        int day = dataByte[4] & 0x1F;
        int hour = dataByte[3] & 0x1F;
        int minute = dataByte[2] & 0x3F;
        int second = dataByte[1] > 0 ? dataByte[1] : (int) (dataByte[1] & 0xff);
        int millisecond = dataByte[0] > 0 ? dataByte[0] : (int) (dataByte[0] & 0xff);
        millisecond = (second << 8) + millisecond;
        second = millisecond / 1000;
        millisecond = millisecond % 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

	public static String byteArrayToHexString(byte[] array) {
        return byteArray2HexString(array, Integer.MAX_VALUE, false);
    }

	public static String byteArray2HexString(byte[] arrBytes, int count, boolean blank) {
        String ret = "";
        if (arrBytes == null || arrBytes.length < 1) {
        	return ret;
        }
        if (count > arrBytes.length) {
        	count = arrBytes.length;
        }
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            ret = Integer.toHexString(arrBytes[i] & 0xFF).toUpperCase();
            if (ret.length() == 1) {
            	builder.append("0").append(ret);
            } else {
            	builder.append(ret);
            }
            if (blank) {
            	builder.append(" ");
            }
        }

        return builder.toString();

    }

    /**
     * 返回指定位置的数组
     * @param bytes
     * @param start 开始位置
     * @param length  截取长度
     * @return
     */
	public  static byte[] getByte(byte[] bytes, int start, int length) {
		byte[] ruleByte = new byte[length];
		int index = 0;
		while (index < length) {
			ruleByte[index++] = bytes[start++];
		}
		return ruleByte;
	}


    /**
     * 十六进制字符串转换成byte数组
     * @param hexStr
     * @return
     */
	public static  byte[] hexStringToByte(String hexStr){
        hexStr = hexStr.replaceAll(" ", "");
        hexStr = hexStr.toUpperCase();
        int len = (hexStr.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexStr.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 输入流转16进制输出
     * @param bytes
     * @return
     */
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

    /**
     * 高位在前，低位在后
     * @param bArray
     * @return
     */
    public static  String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (byte) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }


    /**
     * 整数转化为字节 大端模式
     * @param n
     * @return
     */
    public static byte[] intToBytesBig(int n) {
        byte[] src = new byte[2];
        src[0] = (byte) ((n>>8)&0xFF);
        src[1] = (byte) (n & 0xFF);
        return src;
    }


    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和intToBytes配套使用
     * @param src   byte数组
     * @param offset  从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToInt(byte[] src, int offset) {
        return ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8)
                | ((src[offset + 2] & 0xFF) << 16) | ((src[offset + 3] & 0xFF) << 24));
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和intToBytes配套使用
     * @param src byte数组
     * @return int数值
     */
    public static int bytesToInt(byte[] src) {
        return ByteUtil.bytesToInt(src, 0);
    }

    /**
     * 将字节数组转换成float数据
     * @param bytes 字节数组
     * @return float值
     */
    public static float bytesToFloat(byte[] bytes) {
        return Float.intBitsToFloat(bytesToInt(bytes, 0));
    }

    /**
     * 将字节数组转换成float数据
     * @param bytes 字节数组
     * @param offset 起始位置
     * @return float值
     */
    public static float bytesToFloat(byte[] bytes, int offset) {
        return Float.intBitsToFloat(bytesToInt(bytes,offset));
    }


    /**
     * 字节数组的复制
     * @param sourceBytes
     * @param targetBytes
     * @param beginIndex
     * @return
     */
    public static byte[] copyBytes(byte[] sourceBytes, byte[] targetBytes, int beginIndex) {
        if (targetBytes == null) {
            return sourceBytes;
        }
        int targetSize = targetBytes.length;
        if (sourceBytes == null) {
            beginIndex = 0;
            sourceBytes = new byte[targetSize];
        }
        int sourceSize = sourceBytes.length;
        if (sourceSize - beginIndex < targetSize) {
            return sourceBytes;
        } else {
            for (int i = 0; i < targetSize; i++) {
                sourceBytes[beginIndex + i] = targetBytes[i];
            }
        }
        return sourceBytes;
    }

    /**
     * 合并多个byte[]为一个byte[]
     * @param args
     * @return
     */
    public static byte[] byteMergerAll(byte[]... args) {
        int length_byte = 0;
        for (byte[] b : args) {
            length_byte += b.length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (byte[] b : args) {
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }

    public static String byteToBinary(int x) {
        //元素转2进制
        StringBuffer sb = new StringBuffer() ;
        while (x !=0) {
            if (x < 0) {
                x = 256 + x ;
            }
            int y = x%2 ;
            x = x/2 ;
            sb.append(y) ;
        }
        //反转元素二进制的编码
        sb.reverse();
        StringBuffer sb8 = new StringBuffer()   ;
        //每个元素长度为8位，不够前面补充0
        if (sb.length() < 8) {
            for (int i = 0; i < 8-sb.length(); i++) {
                sb8.append(0) ;
            }
        }
        String  team = sb8.toString() + sb.toString() ;
        return team ;
    }


    public static void main(String[] agrwu) {
            System.out.println(byteArrayToHexString(hexStringToBytes("68 14 8c 00 02 00 1e 01 03 00 01 00 0a 00 00 2d ad 28 0b 1c 09 14")));
    }
}
