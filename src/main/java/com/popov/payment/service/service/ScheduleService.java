package com.popov.payment.service.service;

import com.popov.payment.service.entity.Payment;
import com.popov.payment.service.entity.etc.Cycle;
import com.popov.payment.service.repository.PaymentRepository;
import com.popov.payment.service.service.rabbitmq.RabbitMqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@EnableScheduling
@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {
    private final static long DAY = TimeUnit.DAYS.toMillis(1);
    private final static long WEEK = TimeUnit.DAYS.toMillis(7);
    private final static long BIWEEKLY = TimeUnit.DAYS.toMillis(14);
    private final static long MONTH = TimeUnit.DAYS.toMillis(30);

    private final PaymentRepository paymentRepository;
    private final RabbitMqService rabbitMqService;

    @Scheduled(fixedDelay = 5000)
    public void start() {
        List<Payment> payments = paymentRepository.findAllByRecurringStartDate(Date.from(Instant.now()));
        payments.forEach(rabbitMqService::convertAndSend);
        payments
                .stream()
                .peek(x -> x.setRecurringInstallments(x.getRecurringInstallments()-1))
                .peek(x -> updateDate(x))
                .collect(Collectors.toList());
        paymentRepository.saveAll(payments);

        log.info("message send?");
    }

   public Payment updateDate(Payment payment) {
       if (payment.getRecurringInstallments() > 0 && payment.isRecurring()) {
           if (payment.getCycle() == Cycle.DAY) {
               payment.setRecurringStartDate(java.util.Date.from(Instant.now().plusMillis(DAY)));
           }
           if (payment.getCycle() == Cycle.WEEK) {
               payment.setRecurringStartDate(java.util.Date.from(Instant.now().plusMillis(WEEK)));
           }
           if (payment.getCycle() == Cycle.BIWEEKLY) {
               payment.setRecurringStartDate(java.util.Date.from(Instant.now().plusMillis(BIWEEKLY)));
           }
           if (payment.getCycle() == Cycle.MONTH) {
               payment.setRecurringStartDate(java.util.Date.from(Instant.now().plusMillis(MONTH)));
           }
       } else {
           payment.setRecurring(false);
       }
       return payment;
   }
}
