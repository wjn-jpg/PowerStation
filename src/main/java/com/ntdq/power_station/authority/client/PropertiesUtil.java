package com.ntdq.power_station.authority.client;

//MQTT配置工具类
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置信息
 **/
public class PropertiesUtil {

    public static String MQTT_HOST = "tcp://192.168.0.16:1883";
    public static String MQTT_CLIENT_ID;
    public static String MQTT_USER_NAME;
    public static String MQTT_PASSWORD;
    public static String MQTT_TOPIC;
    public static Integer MQTT_TIMEOUT;
    public static Integer MQTT_KEEP_ALIVE;

    /**
     *  mqtt配置
     */
    static {
        Properties properties = loadMqttProperties();
        MQTT_HOST = properties.getProperty("host");
       // MQTT_HOST = "tcp://127.0.0.1:8088";
        MQTT_CLIENT_ID = properties.getProperty("clientId");
        MQTT_USER_NAME = properties.getProperty("username");
        MQTT_PASSWORD = properties.getProperty("password");
        MQTT_TOPIC = properties.getProperty("topic");
        MQTT_TIMEOUT = Integer.valueOf(properties.getProperty("timeout"));
        MQTT_KEEP_ALIVE = Integer.valueOf(properties.getProperty("keepalive"));
    }

    private static Properties loadMqttProperties() {
        InputStream inputstream = PropertiesUtil.class.getResourceAsStream("/mqtt.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputstream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
