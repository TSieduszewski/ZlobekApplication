package com.app.zlobek.controller;

import com.app.zlobek.entity.Parent;
import com.app.zlobek.security.GetUserID;
import com.app.zlobek.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    private ParentService parentService;

    @Autowired
    public MenuController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/menu")
    public String menu(){
        return "menu/menu";
    }

}
