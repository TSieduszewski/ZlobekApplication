package com.app.zlobek.service;

import com.app.zlobek.entity.Charges;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChargesServiceImpl implements ChargesService {

    @Override
    public List<Charges> findAll() {
        return null;
    }

    @Override
    public Charges findById(int id) {
        return null;
    }

    @Override
    public void save(Charges attendance) {

    }
}
