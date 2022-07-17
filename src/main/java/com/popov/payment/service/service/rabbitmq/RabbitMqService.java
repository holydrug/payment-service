package com.popov.payment.service.service.rabbitmq;

import com.popov.payment.service.entity.Payment;
import com.popov.payment.service.properties.YAMLProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqService {
    private final YAMLProperties properties;
    private final RabbitTemplate rabbitTemplate;

    public void convertAndSend(Payment payment) {
        rabbitTemplate.convertAndSend(
                properties.getRabbitMq().getExchanges().getInternal(),
                properties.getRabbitMq().getRoutingKeys().getInternal(),
                payment
        );
    }

}
