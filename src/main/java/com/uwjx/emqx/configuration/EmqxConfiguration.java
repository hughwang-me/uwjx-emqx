package com.uwjx.emqx.configuration;

import com.uwjx.emqx.service.MqttMsgCallback;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/16 14:30
 */
@Configuration
@Slf4j
public class EmqxConfiguration {

    @Value("${emqx.serverUri}")
    String serverUri;
    @Value("${emqx.clientId}")
    String clientId;
    @Value("${emqx.username}")
    String username;
    @Value("${emqx.password}")
    String password;
    @Value("${emqx.topic}")
    String topic;

    @Autowired
    MqttMsgCallback mqttMsgCallback;

    @Bean
    public MqttClient initMqttClient(){
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient mqttClient = new MqttClient(serverUri , clientId , persistence);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(username);
            connectOptions.setPassword(password.toCharArray());
            connectOptions.setCleanSession(true);
            mqttClient.setCallback(mqttMsgCallback);
            log.warn("开始建立连接...");
            mqttClient.connect(connectOptions);
            log.warn("建立成功 @@@");
            //订阅
            mqttClient.subscribe(topic);
            log.warn("订阅Topic -> {}" , topic);
            return mqttClient;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
