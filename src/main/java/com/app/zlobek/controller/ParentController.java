package com.app.zlobek.controller;

import com.app.zlobek.entity.Parent;
import com.app.zlobek.service.ParentService;
import com.app.zlobek.util.messages.MessageWithReceivers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parents")
public class ParentController {

    private ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/list")
    public String listOfParents(Model model) {

        List<Parent> parentList = parentService.findAll();
        MessageWithReceivers listOfParents = new MessageWithReceivers();
        model.addAttribute("parents", parentList);
        model.addAttribute("listOfParents", listOfParents);

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
