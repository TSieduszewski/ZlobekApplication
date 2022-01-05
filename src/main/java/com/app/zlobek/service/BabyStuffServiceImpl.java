package com.app.zlobek.service;

import com.app.zlobek.dao.BabyStuffRepository;
import com.app.zlobek.entity.BabyStuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BabyStuffServiceImpl implements BabyStuffService {

    private BabyStuffRepository babyStuffRepository;

    @Autowired
    public BabyStuffServiceImpl(BabyStuffRepository babyStuffRepository) {
        this.babyStuffRepository = babyStuffRepository;
    }

    @Override
    public BabyStuff findById(int id) {
        Optional<BabyStuff> result = babyStuffRepository.findById(id);

        BabyStuff tempBabyStuff = null;

        if (result.isPresent()) {
            tempBabyStuff = result.get();
        } else {
            throw new RuntimeException("Nie znalazłem klienta i jego rzeczy");
        }
        return tempBabyStuff;
    }

    @Override
    public List<BabyStuff> findAll() {
        return babyStuffRepository.findAll();
    }

    @Override
    public void save(BabyStuff babyStuff) {
        babyStuffRepository.save(babyStuff);
    }
}
