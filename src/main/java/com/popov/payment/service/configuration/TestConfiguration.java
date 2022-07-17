package com.popov.payment.service.configuration;


import com.popov.payment.service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
@RequiredArgsConstructor
public class TestConfiguration {
    private final PaymentRepository paymentRepository;
    private final DateTimeFormatter formatter;

    /*@Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            paymentRepository.save(new Payment(10, formatter.format(Instant.now()),
                    "test payment",
                    "test@test.com",
                    "test_101", true,
                    Cycle.WEEK, formatter.format(Instant.now()), 10));
        };
    }*/
}
