package com.app.zlobek.controller;


import com.app.zlobek.entity.Parent;
import com.app.zlobek.entity.Question;
import com.app.zlobek.security.GetUserID;
import com.app.zlobek.service.ParentService;
import com.app.zlobek.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    private ParentService parentService;

    private int parentId;

    @Autowired
    public QuestionController(QuestionService questionService, ParentService parentService) {
        this.questionService = questionService;
        this.parentService = parentService;
    }

    @Secured("ROLE_USER")
    @GetMapping("/showFormForAddQuestion")
    public String showFormForAddMessage(Model model, Authentication authentication) throws Exception  {

        GetUserID userID = new GetUserID(authentication);
        parentId = userID.get();

        Question question = new Question(new Parent(parentId), LocalDateTime.now());

        model.addAttribute("question", question);

        return "newMessages/newQuestionForm";
    }

    @Secured("ROLE_USER")
    @PostMapping("/save")
    public String saveMessage(@Valid @ModelAttribute("question") Question question) {

        questionService.save(question);

        return "redirect:/menu";

    }

    @Secured("ROLE_DIRECTOR")
    @GetMapping("/showListOfQuestions")
    public String showListOfQuestions(Model model){

        List<Question> questionList = questionService.findAllByDate();

        model.addAttribute("listOfQuestions", questionList);

        return "questions/questionList";
    }
}
