package com.popov.payment.service.repository;

import com.popov.payment.service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByRecurringStartDate(Date recurringStartDate);
}
