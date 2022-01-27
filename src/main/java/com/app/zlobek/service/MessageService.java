package com.app.zlobek.service;

import com.app.zlobek.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService{

    List<Message> findAll();

    Message findById(int id);

    void save(Message message);

    void deleteById(int id);

    List<Message> findAllByParentAndDate();

    Page<Message> findPaginated(Pageable pageable, List<Message> messages);
}
