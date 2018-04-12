package com.ifood.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttIfoodHealthSignalClient {

    public static void main(String[] args) throws MqttException {

        MqttClient client = new MqttClient ("tcp://localhost:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload("restaurant1".getBytes());
        client.publish("topic1", message);
        client.disconnect();
    }
}
