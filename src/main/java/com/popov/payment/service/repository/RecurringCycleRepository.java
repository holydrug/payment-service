package com.popov.payment.service.repository;

import com.popov.payment.service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurringCycleRepository extends JpaRepository<Payment, Long> {
}
