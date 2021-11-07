package com.app.zlobek.service;

import com.app.zlobek.entity.Attendance;
import com.app.zlobek.entity.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {

    List<Payment> findAll();

    Payment findById(int id);

    void save(Payment payment);

    void deleteById(int id);

    List<Payment> findAllByMonthOrderByMonthDesc(LocalDate month);

    Payment findByParent(int idParent);


}
