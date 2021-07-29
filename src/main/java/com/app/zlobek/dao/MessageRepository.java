package com.app.zlobek.dao;

import com.app.zlobek.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Override
    <S extends Message> List<S> saveAll(Iterable<S> iterable);

    @Override
    <S extends Message> S saveAndFlush(S s);
}
