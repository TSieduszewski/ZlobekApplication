package com.app.zlobek.controller;

import com.app.zlobek.entity.Parent;
import com.app.zlobek.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/parents")
public class ParentController {

    private ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/main")
    public String menu(){
        return "parents/menu";
    }

    @GetMapping("/list")
    public String listOfParents(Model model) {

        List<Parent> parentList = parentService.findAll();

        model.addAttribute("parents", parentList);

        return "parents/listOfParents";

    }

    @GetMapping("/showSingleParentMessages")
    public String singleParentMessages(@RequestParam("parentId") int theId, Model model){
        Parent tempParent = parentService.findById(theId);
        model.addAttribute("singleParent", tempParent);
        model.addAttribute("messages", tempParent.getMessages());

        return "parents/messages";
    }


}
