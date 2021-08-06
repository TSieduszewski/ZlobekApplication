package com.app.zlobek.controller;


import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import com.app.zlobek.service.MessageService;
import com.app.zlobek.service.ParentService;
import com.app.zlobek.util.messages.MessageWithReceivers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

        System.out.println("showFormForAddMessage = " + messageReadyToSend.isSingleParentStatus());
        return "newMessages/newMessageForm";
    }


    @PostMapping("/save")
    public String saveMessage(@Valid @ModelAttribute("listOfParents") MessageWithReceivers messageReadyToSave) {
        System.out.println("saveMessage = " + messageReadyToSave.isSingleParentStatus());

        if (messageReadyToSave.isSingleParentStatus()) {
            int tempId = messageReadyToSave.getIdOfSingleParents();
            messageReadyToSave.getMessage().setParent(parentService.findById(tempId));
            messageReadyToSave.getMessage().setDate(LocalDateTime.now());
            messageService.save(messageReadyToSave.getMessage());
        } else {


            for (int tempId : messageReadyToSave.getSelectedParents()) {
                messageReadyToSave.getMessage().setDate(LocalDateTime.now());
                messageService.save(new Message(messageReadyToSave.getMessage().getMessage(),
                                                messageReadyToSave.getMessage().getDate(),
                                                parentService.findById(tempId)));
            }

        }

        return "redirect:/parents/list";

    }

}
