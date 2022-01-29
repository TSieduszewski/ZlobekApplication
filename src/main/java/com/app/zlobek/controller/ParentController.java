package com.app.zlobek.controller;

import com.app.zlobek.entity.Message;
import com.app.zlobek.entity.Parent;
import com.app.zlobek.service.MessageService;
import com.app.zlobek.service.ParentService;
import com.app.zlobek.util.messages.MessageWithReceivers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/parents")
public class ParentController {

    private ParentService parentService;
    private MessageService messageService;

    @Autowired
    public ParentController(ParentService parentService, MessageService messageService) {
        this.parentService = parentService;
        this.messageService = messageService;
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
    public String singleParentMessages(@RequestParam("parentId") int theId,
                                       @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size,
                                       Model model){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Parent tempParent = parentService.findById(theId);
        model.addAttribute("singleParent", tempParent);

        Page<Message> messagePage = messageService.findPaginated(PageRequest.of(currentPage-1,pageSize), tempParent.getMessages());

        model.addAttribute("messages", messagePage);

        int totalPages = messagePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "parents/messages";
    }

    @GetMapping("/addParent")
    public String addParent(Model model){

        Parent parent = new Parent();

        model.addAttribute("parent", parent);

        return "parents/parentsForm";
    }

    @GetMapping("/updateParent")
    public String updateParent(@RequestParam("parentId") int id, Model model){

        Parent parent = parentService.findById(id);

        model.addAttribute("parent", parent);

        return "parents/parentsForm";
    }

    @PostMapping("/saveParent")
    public String saveParent(@Valid @ModelAttribute("parent") Parent parent, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "parents/parentsForm";
        } else {
            parentService.save(parent);
        }

        return "redirect:/parents/list";
    }

    @GetMapping("/deleteParent")
    public String deleteParent(@RequestParam("parentId") int id){

        parentService.deleteById(id);

        return "redirect:/parents/list";
    }
}
