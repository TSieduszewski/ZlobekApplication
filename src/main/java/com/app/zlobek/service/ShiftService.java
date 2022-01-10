package com.app.zlobek.service;

import com.app.zlobek.entity.Babysitter;
import com.app.zlobek.entity.Shift;

import java.util.List;

public interface ShiftService {

    List<Shift> findAllShifts();

    List<Shift> findAllShiftsOfSingleBabysitter(Babysitter babysitter);

    void save(Shift shift);

}
