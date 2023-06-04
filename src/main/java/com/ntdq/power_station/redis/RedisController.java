package com.ntdq.power_station.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redis/hash")
public class RedisController {

    public static String json = "{\n" +
            " \"requestId\": \"c35a9bddc35943f8bfd62ebcc8bfdd45\",\n" +
            " \"appId\": \"c8fd23c9-1344-4b48-b3f3-7d5296dbec9a\",\n" +
            " \"service\": {\n" +
            "  \"serviceId\": \"InfoService\",\n" +
            "  \"serviceType\": \"InfoService\",\n" +
            "  \"eventTime\": \"20230308181637\",\n" +
            "  \"eventTimeUTC\": \"20230308T101637Z\",\n" +
            "  \"data\": {\n" +
            "   \"CoolingMode\": 0,\n" +
            "   \"HeatingMode\": 0,\n" +
            "   \"ShutdownMonitor\": 1,\n" +
            "   \"ReturnAirTemp\": 18.8,\n" +
            "   \"ReturnAirRH\": 77.0,\n" +
            "  }\n" +
            " },\n" +
            " \"manufacturerId\": \"\",\n" +
            " \"model\": \"\",\n" +
            " \"deviceType\": \"PrecisionAirConditioner_DH_1.0.0\",\n" +
            " \"notifyType\": \"deviceDataChanged\",\n" +
            " \"deviceId\": \"ababd412-d9d6-4484-8e85-66766c9e1017b\",\n" +
            " \"gatewayId\": \"4c859c45-5731-4334-8469-4b42ca116a34\",\n" +
            " \"gatewayType\": \"Basepoint\"\n" +
            "}";

    @Autowired
    private RedisClient redisClient;


    @RequestMapping("/json")
    public void testRedis() {
        Map<String, String> stringStringMap = JSONObject.parseObject(json, Map.class);

        String deviceId = stringStringMap.get("deviceId");
        String service = String.valueOf(stringStringMap.get("service"));
        System.out.println(service);
        JSONObject jsonObject = JSONObject.parseObject(service);
        System.out.println(jsonObject);
        String serviceType = (String) jsonObject.get("serviceType");
        String eventTime = (String) jsonObject.get("eventTime");
        String dataJSON = String.valueOf(jsonObject.get("data"));

        Map<String, Object> dataJsonObject = JSONObject.parseObject(dataJSON, Map.class);


        for (Map.Entry<String, Object> entry : dataJsonObject.entrySet()) {
            Map<String, String> stringMap = new HashMap<>();
            Object value = entry.getValue();
            String key = entry.getKey();
            String redisHash_Key = deviceId + "_" + key;
            System.out.println(value);
            stringMap.put("deviceId", deviceId);
            stringMap.put("atrCode", key);
            stringMap.put("serviceType", serviceType);
            stringMap.put("value",String.valueOf(value));
            stringMap.put("updateTime",String.valueOf(eventTime));
            redisClient.putHash("hzxz", redisHash_Key, stringMap);
        }

        System.out.println("成功!");


    }

    @RequestMapping("/get")
    public String getRedis(){
        String keyForStrValue = redisClient.getKeyForStrValue("hzxz:ababd412-d9d6-4484-8e85-66766c9e1017b_CoolingMode");
        System.out.println(keyForStrValue);
        return keyForStrValue;
    }

}
