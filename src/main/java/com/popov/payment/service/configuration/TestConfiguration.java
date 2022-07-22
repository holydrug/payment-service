package com.popov.payment.service.configuration;


import com.popov.payment.service.entity.Payment;
import com.popov.payment.service.entity.etc.Cycle;
import com.popov.payment.service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class TestConfiguration {
    private final PaymentRepository paymentRepository;
    private final DateTimeFormatter formatter;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Payment payment = new Payment(1L, 10, "Moda", "name", "email",
                    "123123", true, Cycle.WEEK, Date.from(Instant.now()), 10);
            paymentRepository.save(payment);
       };
    }
}
