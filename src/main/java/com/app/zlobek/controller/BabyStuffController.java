package com.app.zlobek.controller;

import com.app.zlobek.entity.BabyStuff;
import com.app.zlobek.service.BabyStuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/babystuff")
public class BabyStuffController {

    private BabyStuffService babyStuffService;

    @Autowired
    public BabyStuffController(BabyStuffService babyStuffService) {
        this.babyStuffService = babyStuffService;
    }

    public String showBabyStuff() {
        return "";
    }

    @GetMapping("/showAllStuff")
    public String showAllStuff(Model model) {

        List<BabyStuff> listOfAllStuff = babyStuffService.findAll();

        model.addAttribute("listOfAllStuff", listOfAllStuff);

        return "stuff/showAllStuff";
    }

    @GetMapping("/update")
    public String updateStuff(@RequestParam("parentId") int id, @RequestParam("change") int change, @RequestParam("stuff") String stuff) {

        BabyStuff babyStuff = babyStuffService.findById(id);
        final Integer stuffGuardCounter;

        switch (stuff) {
            case "dryWipes":
                stuffGuardCounter = stuffGuard(babyStuff.getDryWipes() + change);
                babyStuff.setDryWipes(stuffGuardCounter);
                break;
            case "wetWipes":
                stuffGuardCounter = stuffGuard(babyStuff.getWetWipes() + change);
                babyStuff.setWetWipes(stuffGuardCounter);
                break;
            case "diapers":
                stuffGuardCounter = stuffGuard(babyStuff.getDiapers() + change);
                babyStuff.setDiapers(stuffGuardCounter);
                break;
            case "towels":
                stuffGuardCounter = stuffGuard(babyStuff.getTowels() + change);
                babyStuff.setTowels(stuffGuardCounter);
                break;
            case "bibs":
                stuffGuardCounter = stuffGuard(babyStuff.getBibs() + change);
                babyStuff.setBibs(stuffGuardCounter);
                break;
        }

        babyStuffService.save(babyStuff);
        return "redirect:/babystuff/showAllStuff";
    }

    @GetMapping("/showFormForUpdateStuff")
    public String showFormForUpdateStuff(@RequestParam("parentId") int id, Model model) {

        BabyStuff babyStuff = babyStuffService.findById(id);
        model.addAttribute("babyStuff", babyStuff);

        return "stuff/updateStuffPage";
    }

    @PostMapping("/save")
    public String saveStuff(@Valid @ModelAttribute("babyStuff") BabyStuff babyStuff) {
        babyStuffService.save(babyStuff);

        return "redirect:/babystuff/showAllStuff";

    }

    private Integer stuffGuard(Integer stuffGuardCounter) {

        if (stuffGuardCounter > 0) {
            return stuffGuardCounter;
        } else {
            return 0;
        }
    }
}
