package com.app.zlobek.dao;

import com.app.zlobek.entity.Parent;
import com.app.zlobek.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByMonthOrderByMonthDesc(LocalDate month);

    Payment findByParentAndMonth(Parent parent, LocalDate month);
}
