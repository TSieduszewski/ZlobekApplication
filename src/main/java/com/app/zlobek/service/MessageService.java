package com.app.zlobek.service;

import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService{

    List<Message> findAll();

    Message findById(int id);

    void save(Message message);

    void deleteById(int id);

}
