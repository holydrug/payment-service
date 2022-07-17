package com.popov.payment.service.service;

import com.popov.payment.service.configuration.redis.etc.RedisMessagePublisher;
import com.popov.payment.service.entity.Payment;
import com.popov.payment.service.rabbit.PaymentSender;
import com.popov.payment.service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentService {

    public static final String HASH_KEY = "Payment";

    private final PaymentRepository paymentRepository;
    private final RedisTemplate redisTemplate;
    private final RedisMessagePublisher redisMessagePublisher;
    private final PaymentSender paymentSender;


    public void createPayment(Payment payment) {
/*        redisTemplate.opsForHash().put(HASH_KEY, payment.getNumber(), payment);
        paymentSender.sendPayment(redisTemplate.opsForHash().get(HASH_KEY, payment.getNumber()).toString());
        log.info(redisTemplate.opsForHash().get(HASH_KEY, payment.getNumber()));*/

        redisMessagePublisher.publish(payment.toString());

    }

    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }
}
