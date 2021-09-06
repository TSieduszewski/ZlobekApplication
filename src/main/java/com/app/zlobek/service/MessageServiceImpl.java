package com.app.zlobek.service;

import com.app.zlobek.dao.MessageRepository;
import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findById(int id) {
        Optional<Message> result = messageRepository.findById(id);

        Message tempMessage = null;

        if(result.isPresent()){
            tempMessage = result.get();
        } else {
            throw new RuntimeException("Nie znalazłem wiadomości");
        }
        return tempMessage;
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void deleteById(int id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<Message> findAllByParentAndDate() {

        //tutaj zamiast liczby na sztywno wartość która będzie przekazywana po zalogowaniu - zrobić, żeby ustalać id po logowaniu
        int parentId = 1;


        return messageRepository.findAllByParentAndDateAfterOrderByDateDesc(new Parent(parentId),
                                                             LocalDateTime.now().minusDays(30));



    }
}
