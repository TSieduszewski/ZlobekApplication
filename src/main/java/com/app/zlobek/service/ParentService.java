package com.app.zlobek.service;

import com.app.zlobek.entity.Parent;

import java.util.List;

public interface ParentService {

    List<Parent> findAll();
//
//    List<Parent> findSelected();

    Parent findById(int id);


    void save(Parent parent);

    void deleteById(int id);

}
