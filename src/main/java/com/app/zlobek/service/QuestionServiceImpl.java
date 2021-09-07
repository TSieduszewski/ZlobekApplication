package com.app.zlobek.service;

import com.app.zlobek.dao.QuestionRepository;
import com.app.zlobek.entity.Parent;
import com.app.zlobek.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

   QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(int id) {
        Optional<Question> result = questionRepository.findById(id);

        Question tempMessage = null;

        if(result.isPresent()){
            tempMessage = result.get();
        } else {
            throw new RuntimeException("Nie znalazłem wiadomości");
        }
        return tempMessage;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> findAllByParentAndDate() {

        //tutaj zamiast liczby na sztywno wartość która będzie przekazywana po zalogowaniu - zrobić, żeby ustalać id po logowaniu
        int parentId = 1;


        return questionRepository.findAllByParentAndDateAfterOrderByDateDesc(new Parent(parentId),
                                                             LocalDateTime.now().minusDays(30));

    }

    @Override
    public List<Question> findAllByDate() {

        return questionRepository.findALlByDateAfterOrderByDateDesc(LocalDateTime.now().minusDays(10));
    }
}
