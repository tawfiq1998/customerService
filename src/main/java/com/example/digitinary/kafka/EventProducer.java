package com.example.digitinary.kafka;

import com.example.digitinary.kafka.request.CreateAccountRequestDTO;
import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;

public class EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic, CreateAccountRequestDTO message) {
        Gson gson = new Gson();
        kafkaTemplate.send(topic, gson.toJson(message));
    }
}

