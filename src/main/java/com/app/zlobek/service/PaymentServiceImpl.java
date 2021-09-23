package com.app.zlobek.service;

import com.app.zlobek.dao.ParentRepository;
import com.app.zlobek.dao.PaymentRepository;
import com.app.zlobek.entity.Parent;
import com.app.zlobek.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private ParentRepository parentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ParentRepository parentRepository) {
        this.paymentRepository = paymentRepository;
        this.parentRepository = parentRepository;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(int id) {
        Optional<Payment> result = paymentRepository.findById(id);

        Payment tempPayment = null;

        if (result.isPresent()) {
            tempPayment = result.get();
        } else {
            throw new RuntimeException("Nie znalazłem klienta");
        }
        return tempPayment;
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void deleteById(int id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Payment> findAllByMonthOrderByMonthDesc(LocalDate month) {
        LocalDate acualMonth = LocalDate.parse(month.format(DateTimeFormatter.ofPattern("yyyy-MM-01")));

        if (paymentRepository.findAllByMonthOrderByMonthDesc(acualMonth).size() == 0) {
            addNewPaymentMonth(acualMonth);
        }

        return paymentRepository.findAllByMonthOrderByMonthDesc(acualMonth);
    }

    @Override
    public Payment findByParent() {

        //tutaj zamiast liczby na sztywno wartość która będzie przekazywana po zalogowaniu - zrobić, żeby ustalać id po logowaniu
        int parentId = 1;

        return paymentRepository.findByParentAndMonth(new Parent(parentId), LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-01"))));

    }

    private void addNewPaymentMonth(LocalDate actualMonth) {
        List<Parent> parentList = parentRepository.findAll();

        for (Parent tempParent : parentList) {
            paymentRepository.save(new Payment(690, 0, actualMonth, 690, tempParent));
        }
    }
}
