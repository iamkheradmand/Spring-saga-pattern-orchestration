package com.common.common;

import com.common.enums.RabbitMqRouting;
import lombok.AllArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class RabbitMqSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(RabbitMqRouting routing, Object message) {
        rabbitTemplate.convertAndSend(routing.getExchange(), routing.getRoutingKey(), message);
    }
}