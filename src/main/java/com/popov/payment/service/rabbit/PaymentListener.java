package com.popov.payment.service.rabbit;

import com.popov.payment.service.properties.YAMLProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentListener {

    private final YAMLProperties properties;
    private final Jackson2JsonMessageConverter jsonMessageConverter;

    @RabbitListener(queues = "${rabbitmq.queues.internal}")
    public void handlePayment(Message text) {
        jsonMessageConverter.fromMessage(text);
    }
}
