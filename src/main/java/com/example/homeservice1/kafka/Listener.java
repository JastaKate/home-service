package com.example.homeservice1.kafka;

import com.example.homeservice1.service.HomeServiceimpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Listener {

    @KafkaListener(topics = "user-topic", groupId = "test")
    public void Listener1(String message) {
        System.out.println("Message: " + message);
        // log.info("Message: {}", message);
    }


    @KafkaListener(topics = "home-deleted", groupId = "test")
    public void Listener2(String message) {
        System.out.println("Message: " + message);
        // log.info("Message: {}", message);
    }


}
