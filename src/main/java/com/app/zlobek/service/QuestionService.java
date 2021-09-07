package com.app.zlobek.service;

import com.app.zlobek.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    List<Question> findAll();

    Question findById(int id);

    void save(Question question);

    void deleteById(int id);

    List<Question> findAllByParentAndDate();

    List<Question> findAllByDate();
}
