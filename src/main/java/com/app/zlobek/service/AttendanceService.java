package com.app.zlobek.service;

import com.app.zlobek.entity.Attendance;

import java.util.List;

public interface AttendanceService {
//sprawdzić, czy wszystkie crud-y będą potrzebne jak nie to wywalić
    List<Attendance> findAll();

    List<Attendance> findAllByIdAndDate();

    List<Attendance> findAllById(int id);

    List<Attendance> findAllByIdFromLastMonth(int id);

    List<Attendance> findAllByDate();

    Attendance findById(int id);

    void save(Attendance attendance);

    void deleteById(int id);
}
