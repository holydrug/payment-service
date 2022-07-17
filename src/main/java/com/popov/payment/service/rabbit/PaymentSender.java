package com.popov.payment.service.rabbit;

import com.popov.payment.service.properties.YAMLProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class PaymentSender {

    private final RabbitTemplate rabbitTemplate;
    private final YAMLProperties properties;

    public void sendPayment(String payload) {
        rabbitTemplate
                .convertAndSend(properties.getRabbitMq().getExchanges().getInternal(),
                        properties.getRabbitMq().getRoutingKeys().getInternal(),
                        payload);
    }
}
