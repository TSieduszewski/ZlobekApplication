package com.app.zlobek.controller;

import com.app.zlobek.entity.Babysitter;
import com.app.zlobek.entity.Shift;
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

import javax.validation.Valid;
import java.util.List;

@Controller
@Secured("ROLE_DIRECTOR")
@RequestMapping("/shift")
public class ShiftController {

    private ShiftService shiftService;
    private BabysitterService babysitterService;

    @Autowired
    public ShiftController(ShiftService shiftService, BabysitterService babysitterService) {
        this.shiftService = shiftService;
        this.babysitterService = babysitterService;
    }

    @GetMapping("/showAllShifts")
    public String showAllShifts(Model model) {

        List<Shift> shiftList = shiftService.findAllShifts();
        model.addAttribute("shiftList", shiftList);

        return "babysittersShifts/showList";
    }

    @GetMapping("/addShift")
    public String addShift(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String url, @RequestParam(value = "babysitterId", required = false) Integer id, Model model) {

        if(id!=null){
            ShiftWithBabysitter shift = new ShiftWithBabysitter(id);
            model.addAttribute("shift", shift);
            model.addAttribute("url", url);
        } else {
            ShiftWithBabysitter shift = new ShiftWithBabysitter();
            List<Babysitter> babysitters = babysitterService.findAllBabysitters();
            model.addAttribute("shift", shift);
            model.addAttribute("url", url);
            model.addAttribute("babysitters", babysitters);
        }

        return "babysittersShifts/shiftForm";
    }

    @GetMapping("/updateShift")
    public String updateShift(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String url, @RequestParam("shiftId") int id, Model model) {

        Shift tempShift = shiftService.findById(id);
        ShiftWithBabysitter shift = new ShiftWithBabysitter(tempShift, tempShift.getBabysitter().getId());
        model.addAttribute("shift", shift);
        model.addAttribute("url", url);

        return "babysittersShifts/shiftForm";
    }

    @GetMapping("/deleteShift")
    public String deleteShift(@RequestParam("shiftId") int id) {
        Shift shift = shiftService.findById(id);
        shiftService.deleteById(id);

        return "redirect:/babysitter/showSingleBabysitter?babysitterId=" + shift.getBabysitter().getId();
    }

    @PostMapping("/saveShift")
    public String saveShift(@Valid @ModelAttribute("shift") ShiftWithBabysitter shift, BindingResult bindingResult) {
        System.out.println("bindingResult = " + bindingResult);
        if (bindingResult.hasErrors()) {

            return "babysittersShifts/shiftForm";
        } else {
            if(shift.getIdOfBabysitter() != 0){
                shift.getShift().setBabysitter(babysitterService.findById(shift.getIdOfBabysitter()));
                shiftService.save(shift.getShift());
            } else {
                shiftService.save(shift.getShift());
            }
        }

        return "redirect:/babysitter/showSingleBabysitter?babysitterId=" + shift.getIdOfBabysitter();
    }

}
