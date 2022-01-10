package com.app.zlobek.controller;

import com.app.zlobek.entity.Babysitter;
import com.app.zlobek.entity.Shift;
import com.app.zlobek.service.BabysitterService;
import com.app.zlobek.service.ShiftService;
import com.app.zlobek.util.shift.ShiftWithBabysitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/babysitter")
public class BabysitterController {

    private BabysitterService babysitterService;
    private ShiftService shiftService;

    @Autowired
    public BabysitterController(BabysitterService babysitterService, ShiftService shiftService) {
        this.babysitterService = babysitterService;
        this.shiftService = shiftService;
    }


    @GetMapping("/showSingleBabysitter")
    public String showSingleBabysitter(@RequestParam("babysitterId") int id, Model model){
        Babysitter babysitter = babysitterService.findById(id);

        Collections.sort(babysitter.getShifts());
        model.addAttribute("babysitter", babysitter);

        return "babysittersShifts/showSingleBabysitter";
    }

    @GetMapping("/addShift")
    public String addShift(@RequestParam("babysitterId") int id, Model model){

        ShiftWithBabysitter shift = new ShiftWithBabysitter(id);

        model.addAttribute("shift", shift);

        return "babysittersShifts/shiftForm";
    }

    @PostMapping("/saveShift")
    public String saveShift(@Valid @ModelAttribute("shift") ShiftWithBabysitter shift){

        shift.getShift().setBabysitter(babysitterService.findById(shift.getIdOfBabysitter()));
        shiftService.save(shift.getShift());
        return "redirect:/babysitter/showSingleBabysitter?babysitterId="+shift.getIdOfBabysitter();
    }
}
