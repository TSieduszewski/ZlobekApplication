package com.app.zlobek.service;

import com.app.zlobek.entity.Charges;

import java.util.List;

public interface ChargesService {

    List<Charges> findAll();

    Charges findById(int id);

    void save(Charges attendance);
}
