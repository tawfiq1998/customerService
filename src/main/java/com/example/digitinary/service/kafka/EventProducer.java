package com.example.digitinary.service.kafka;

import com.example.digitinary.dto.request.CreateCustomerRequestDTO;
import com.example.digitinary.service.kafka.request.CreateAccountRequestDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
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

