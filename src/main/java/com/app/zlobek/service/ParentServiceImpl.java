package com.app.zlobek.service;

import com.app.zlobek.dao.ParentRepository;
import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParentServiceImpl implements ParentService {

    private ParentRepository parentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

//    @Override
//    public List<Parent> findSelected(Iterable check){
//        return parentRepository.findAllById(check);
//    }

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
    }

    @Override
    public void deleteById(int id) {
        parentRepository.deleteById(id);
    }
}
