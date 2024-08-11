package com.example.digitinary.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
public class EventConsumer {

    @KafkaListener(topics = "Customer", groupId = "group_id")
    public void consume(String message) {
        log.info("Received message: {}", message);
        // some notification config
    }
}
