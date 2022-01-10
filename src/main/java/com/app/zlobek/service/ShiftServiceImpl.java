package com.app.zlobek.service;

import com.app.zlobek.dao.ShiftRepository;
import com.app.zlobek.entity.Babysitter;
import com.app.zlobek.entity.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ShiftServiceImpl implements ShiftService {

    private ShiftRepository shiftRepository;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public List<Shift> findAllShiftsOfSingleBabysitter(Babysitter babysitter) {
        return shiftRepository.findAllByBabysitterAndStartShiftAfterOrderByStartShiftAsc(babysitter, LocalDateTime.now().minusDays(7));
    }

    @Override
    public List<Shift> findAllShifts() {
        return shiftRepository.findAllByStartShiftAfterOrderByStartShiftAsc(LocalDateTime.now().minusDays(7));
    }

    @Override
    public void save(Shift shift) {
        shiftRepository.save(shift);
    }
}
