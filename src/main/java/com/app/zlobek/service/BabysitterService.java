package com.app.zlobek.service;

import com.app.zlobek.entity.Babysitter;

import java.util.List;

public interface BabysitterService {

    List<Babysitter> findAllBabysitters();

    Babysitter findById(int id);

    void save(Babysitter babysitter);

    void deleteById(int id);
}
