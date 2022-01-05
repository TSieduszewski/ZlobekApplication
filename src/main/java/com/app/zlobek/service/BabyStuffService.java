package com.app.zlobek.service;

import com.app.zlobek.entity.BabyStuff;

import java.util.List;

public interface BabyStuffService {

    List<BabyStuff> findAll();

    BabyStuff findById(int id);

    void save(BabyStuff babyStuff);
}
