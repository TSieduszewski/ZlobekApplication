package com.app.zlobek.controller;

import com.app.zlobek.entity.Babysitter;
import com.app.zlobek.service.BabysitterService;
import com.app.zlobek.service.ShiftService;
import com.app.zlobek.util.shift.ShiftWithBabysitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;;
import java.util.Collections;
import java.util.List;

@Controller
@Secured("ROLE_DIRECTOR")
@RequestMapping("/babysitter")
public class BabysitterController {

    private BabysitterService babysitterService;
    private ShiftService shiftService;

    @Autowired
    public BabysitterController(BabysitterService babysitterService, ShiftService shiftService) {
        this.babysitterService = babysitterService;
        this.shiftService = shiftService;
    }

    @GetMapping("/showAllBabysitters")
    public String showAllBabysitters(Model model) {
        List<Babysitter> babysitters = babysitterService.findAllBabysitters();

        model.addAttribute("babysitters", babysitters);

        return "babysittersShifts/showAllBabysitters";
    }

    @GetMapping("/showSingleBabysitter")
    public String showSingleBabysitter(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String url, @RequestParam("babysitterId") int id, Model model) {
        Babysitter babysitter = babysitterService.findById(id);

        Collections.sort(babysitter.getShifts());
        model.addAttribute("babysitter", babysitter);
        model.addAttribute("url", url);

        return "babysittersShifts/showSingleBabysitter";
    }

    @GetMapping("/addBabysitter")
    public String addBabysitter(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String url, Model model) {

        Babysitter babysitter = new Babysitter();

        model.addAttribute("babysitter", babysitter);
        model.addAttribute("url", url);

        return "babysittersShifts/addBabysitter";
    }

    @GetMapping("/updateBabysitter")
    public String updateBabysitter(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String url, @RequestParam("babysitterId") int id, Model model) {

        Babysitter babysitter = babysitterService.findById(id);

        model.addAttribute("babysitter", babysitter);
        model.addAttribute("url", url);

        return "babysittersShifts/addBabysitter";
    }

    @GetMapping("/deleteBabysitter")
    public String deleteBabysitter(@RequestParam("babysitterId") int id) {

        babysitterService.deleteById(id);

        return "redirect:/babysitter/showAllBabysitters";
    }



    @PostMapping("/saveBabysitter")
    public String saveBabysitter(@Valid @ModelAttribute("babysitter") Babysitter babysitter, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "babysittersShifts/addBabysitter";
        } else {
            babysitterService.save(babysitter);
        }

        return "redirect:/babysitter/showAllBabysitters";
    }


}
