package com.app.zlobek.dao;

import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import com.app.zlobek.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByParentAndDateAfterOrderByDateDesc(Parent parent, LocalDateTime date);

    List<Question> findALlByDateAfterOrderByDateDesc(LocalDateTime date);
}
