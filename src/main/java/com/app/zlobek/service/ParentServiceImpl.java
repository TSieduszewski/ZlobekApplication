package com.app.zlobek.service;

import com.app.zlobek.dao.MessageRepository;
import com.app.zlobek.dao.ParentRepository;
import com.app.zlobek.dao.PaymentRepository;
import com.app.zlobek.entity.Parent;
import com.app.zlobek.entity.Payment;
import com.app.zlobek.util.global.GlobalValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParentServiceImpl implements ParentService {

    private ParentRepository parentRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository, PaymentRepository paymentRepository) {
        this.parentRepository = parentRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    @Override
    public Parent findById(int id) {
        Optional<Parent> result = parentRepository.findById(id);

        Parent tempParent = null;

        if(result.isPresent()){
            tempParent = result.get();
        } else {
            throw new RuntimeException("Nie znalazłem klienta");
        }
        return tempParent;
    }

    @Override
    public void save(Parent parent) {
        parentRepository.save(parent);
        LocalDate actualMonth = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-01")));
        paymentRepository.save(new Payment(GlobalValues.tuition, 0, actualMonth, GlobalValues.tuition, parent));

    }

    @Override
    public void deleteById(int id) {
        parentRepository.deleteById(id);
    }
}
