package com.app.zlobek.controller;

import com.app.zlobek.entity.Parent;
import com.app.zlobek.security.GetUserID;
import com.app.zlobek.service.UserServiceAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserControllerAdvice {

    private UserServiceAdvice userService;

    @Autowired
    public UserControllerAdvice(UserServiceAdvice userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void loggedUser(Model model, Authentication authentication) throws Exception {

        GetUserID userID = new GetUserID(authentication);
        Parent parent = userService.findById(userID.get());

        model.addAttribute("user", parent);

    }
}
