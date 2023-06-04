package com.ntdq.power_station;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

public class Tbh {
    public static void main(String[] args) {
        String message = "00 08 40 00 CD CC CC 3D 00 0A 40 00 CD CC CC 3D 00 05 40 00 9A 99 A3 43 00 09 40 00 00 00 00 00 00 01 40 00 CD EC 1A 44 00 07 40 00 66 26 A4 43 00 0C 40 00 00 00 80 3F 00 0D 40 00 CD CC 4C 3E 00 06 40 00 9A D9 A3 43 00 02 40 00 66 66 66 3F 00 0E 40 00 48 E1 47 42 00 04 40 00 00 00 00 00 00 03 40 00 66 66 A6 3F 00 0B 40 00 CD CC 4C 3F 00 1F 40 00 00 00 00 00 00 1B 40 00 00 00 00 00 00 1D 40 00 00 00 00 00 00 12 40 00 66 66 C0 42 00 10 40 00 00 00 00 00 00 13 40 00 00 00 E4 41 00 11 40 00 00 00 00 00 00 1A 40 00 00 00 00 00 00 16 40 00 9A 99 E1 41 00 15 40 00 66 66 DE 41 00 14 40 00 9A 99 DD 41 00 1C 40 00 00 00 00 00 00 1E 40 00 00 00 00 00 00 18 40 00 00 00 E4 41 00 17 40 00 9A 99 E1 41 00";
        String[] s = message.split(" ");
        System.out.println(s.length);

        Student student = new Student();
        student.Name = "wjn";
        System.out.println(JSON.toJSONString(student));
        float a = 3.131412412f;
        BigDecimal bigDecimal = new BigDecimal(a);
        System.out.println(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue());
    }


    public static class Student {
        public String Name;
    }
}
