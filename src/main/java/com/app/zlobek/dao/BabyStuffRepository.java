package com.app.zlobek.dao;

import com.app.zlobek.entity.BabyStuff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BabyStuffRepository extends JpaRepository<BabyStuff, Integer> {
}
