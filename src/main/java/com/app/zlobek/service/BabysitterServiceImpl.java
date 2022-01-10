package com.app.zlobek.service;

import com.app.zlobek.dao.BabysitterRepository;
import com.app.zlobek.entity.Babysitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BabysitterServiceImpl implements BabysitterService {

    private BabysitterRepository babysitterRepository;

    @Autowired
    public BabysitterServiceImpl(BabysitterRepository babysitterRepository) {
        this.babysitterRepository = babysitterRepository;
    }

    @Override
    public List<Babysitter> findAllBabysitters() {
        return babysitterRepository.findAll();
    }

    @Override
    public Babysitter findById(int id) {

        Optional<Babysitter> result = babysitterRepository.findById(id);

        Babysitter babysitter = null;

        if(result.isPresent()){
            babysitter = result.get();
        } else {
            throw new RuntimeException("Nie znalazłem pracownika");
        }

        return babysitter;
    }

    @Override
    public void save(Babysitter babysitter) {
        babysitterRepository.save(babysitter);
    }

    @Override
    public void deleteById(int id) {
        babysitterRepository.deleteById(id);
    }
}
