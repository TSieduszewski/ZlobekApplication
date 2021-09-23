package com.app.zlobek.dao;

import com.app.zlobek.entity.Attendance;
import com.app.zlobek.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findAllByParentAndAttendanceDateBetweenOrderByAttendanceDateDesc(Parent parent, LocalDate start, LocalDate end);

    List<Attendance> findByAttendanceDate(LocalDate date);

}
