package com.app.zlobek.service;

import com.app.zlobek.entity.Parent;
import com.app.zlobek.entity.Payment;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface PaymentService {

    List<Payment> findAll();

    Payment findById(int id);

    void save(Payment payment);

    void deleteById(int id);

    List<Payment> findAllByMonthOrderByMonthDesc(LocalDate month);
}
