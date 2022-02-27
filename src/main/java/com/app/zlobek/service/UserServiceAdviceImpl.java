package com.app.zlobek.service;

import com.app.zlobek.dao.ParentRepository;
import com.app.zlobek.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceAdviceImpl implements UserServiceAdvice {

    private ParentService parentService;
    private ParentRepository parentRepository;

    @Autowired
    public UserServiceAdviceImpl(ParentService parentService, ParentRepository parentRepository) {
        this.parentService = parentService;
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent findById(int id) {

        if(id==-9999){
            Parent parent = new Parent();
            parent.setParentFirstName("Admin");
            return parent;
        } else {
            return parentRepository.findById(id).get();
        }
    }
}
