package com.app.zlobek.controller;

import com.app.zlobek.entity.Babysitter;
import com.app.zlobek.entity.Shift;
import com.app.zlobek.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/shift")
public class ShiftController {

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping("/showAllShifts")
    public String showAllShifts(Model model){

        List<Shift> shiftList = shiftService.findAllShifts();
        model.addAttribute("shiftList", shiftList);

        return "babysittersShifts/showList";
    }

}
