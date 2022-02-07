package com.app.zlobek.service;

import com.app.zlobek.dao.MessageRepository;
import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
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

        if (result.isPresent()) {
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
    public List<Message> findAllByParentAndDate(int parentId) {


        return messageRepository.findAllByParentAndDateAfterOrderByDateDesc(new Parent(parentId),
                LocalDateTime.now().minusDays(30));


    }

    @Override
    public Page<Message> findPaginated(Pageable pageable, List<Message> messages) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = pageSize * currentPage;
        List<Message> pagedMessages;

        if (messages.size() < startItem) {
            pagedMessages = Collections.emptyList();
        } else {
            pagedMessages = messages.subList(startItem, Math.min(startItem + pageSize, messages.size()));
        }

        return new PageImpl<>(pagedMessages, PageRequest.of(currentPage, pageSize), messages.size());
    }
}
