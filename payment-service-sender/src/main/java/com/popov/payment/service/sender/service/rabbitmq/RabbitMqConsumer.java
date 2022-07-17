package com.popov.payment.service.sender.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popov.payment.service.sender.entity.Payment;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Log4j2
@EnableRabbit
@Component
public class RabbitMqConsumer {
    private final static long DAY = TimeUnit.DAYS.toMillis(1);
    private final static long WEEK = TimeUnit.DAYS.toMillis(7);
    private final static long BIWEEKLY = TimeUnit.DAYS.toMillis(14);
    private final static long MONTH = TimeUnit.DAYS.toMillis(30);
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @RabbitListener(queues = "${rabbitmq.queues.internal}")
    private void receive(Message message) {
        Payment payment = objectMapper.readValue(message.getBody(), Payment.class);
        log.info(payment);
    }
}
