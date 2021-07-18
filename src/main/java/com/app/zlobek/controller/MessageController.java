package com.app.zlobek.controller;


import com.app.zlobek.service.MessageService;
import com.app.zlobek.service.ParentService;
import com.app.zlobek.util.messages.MessageWithReceivers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    private ParentService parentService;

    @Autowired
    public MessageController(MessageService messageService, ParentService parentService) {
        this.messageService = messageService;
        this.parentService = parentService;
    }

    @GetMapping("/showFormForAddMessage")
    public String showFormForAddMessage(@RequestParam("parentId") int theId, Model model) {

        MessageWithReceivers messageReadyToSend = new MessageWithReceivers(theId);
        model.addAttribute("messageReadyToSendAttribute", messageReadyToSend);


        return "newMessages/newMessageForm";
    }

    @PostMapping("/save")
    public String saveMessage(MessageWithReceivers messageReadyToSave) {

        int tempId = messageReadyToSave.getIdOfSingleParents();

        messageReadyToSave.getMessage().setParent(parentService.findById(tempId));
        messageService.save(messageReadyToSave.getMessage());

        return "redirect:/parents/list";

    }

}
