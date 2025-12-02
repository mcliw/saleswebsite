package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByAmount(Double amount);
}
