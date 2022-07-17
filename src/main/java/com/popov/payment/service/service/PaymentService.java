package com.popov.payment.service.service;

import com.popov.payment.service.entity.Payment;
import com.popov.payment.service.repository.PaymentRepository;
import com.popov.payment.service.service.rabbitmq.RabbitMqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RabbitMqService rabbitMqService;


    public void createPayment(Payment payment) {
        rabbitMqService.convertAndSend(payment);
    }

    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }
}
