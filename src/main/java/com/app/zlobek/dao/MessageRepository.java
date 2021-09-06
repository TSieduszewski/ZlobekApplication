package com.app.zlobek.dao;

import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByParentAndDateAfterOrderByDateDesc(Parent parent, LocalDateTime date);
}
