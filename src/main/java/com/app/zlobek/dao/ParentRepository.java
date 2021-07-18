package com.app.zlobek.dao;

import com.app.zlobek.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

    @Override
    public List<Parent> findAllById(Iterable<Integer> iterable);
}
