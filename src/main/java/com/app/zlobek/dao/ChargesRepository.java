package com.app.zlobek.dao;


import com.app.zlobek.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargesRepository extends JpaRepository<Attendance, Integer> {
}