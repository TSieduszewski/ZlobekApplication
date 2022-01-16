package com.app.zlobek.dao;

import com.app.zlobek.entity.Babysitter;
import com.app.zlobek.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    List<Shift> findAllByStartShiftAfterOrderByStartShiftAsc(LocalDateTime start);

    List<Shift> findAllByBabysitterAndStartShiftAfterOrderByStartShiftAsc(Babysitter babysitter, LocalDateTime start);
}
