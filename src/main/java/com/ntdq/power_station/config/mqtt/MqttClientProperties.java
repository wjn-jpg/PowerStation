package com.ntdq.power_station.config.mqtt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttClientProperties {

    /**
     * 客户端账户
     */
    @Value("${mqtt.client.username}")
    private String username;
    /**
     * 客户端密码
     */
    @Value("${mqtt.client.password}")
    private String password;

    /**
     * tcp连接url
     */
    @Value("${mqtt.client.url}")
    private String url;

    /**
     * 客户端ID
     */
    @Value("${mqtt.client.clientId}")
    private String clientId;

    /**
     * 默认主题
     */
    @Value("${mqtt.client.defaultTopic}")
    private String defaultTopic;

    /**
     * 超时时间
     */
    @Value("${mqtt.client.completionTimeout}")
    private int completionTimeout;

    /**
     * 长连接时间
     */
    @Value("${mqtt.client.keepAliveInterval}")
    private int keepAliveInterval;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDefaultTopic() {
        return defaultTopic;
    }

    public void setDefaultTopic(String defaultTopic) {
        this.defaultTopic = defaultTopic;
    }

    public int getCompletionTimeout() {
        return completionTimeout;
    }

    public void setCompletionTimeout(int completionTimeout) {
        this.completionTimeout = completionTimeout;
    }

    public int getKeepAliveInterval() {
        return keepAliveInterval;
    }

    public void setKeepAliveInterval(int keepAliveInterval) {
        this.keepAliveInterval = keepAliveInterval;
    }
}
