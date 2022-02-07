package com.app.zlobek.controller;

import com.app.zlobek.entity.BabyStuff;
import com.app.zlobek.security.GetUserID;
import com.app.zlobek.service.BabyStuffService;
import com.app.zlobek.service.ParentService;
import com.app.zlobek.util.global.GlobalValues;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/babystuff")
public class BabyStuffController {

    private BabyStuffService babyStuffService;
    private ParentService parentService;
    private int parentId;

    @Autowired
    public BabyStuffController(BabyStuffService babyStuffService, ParentService parentService) {
        this.babyStuffService = babyStuffService;
        this.parentService = parentService;
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
    public String saveStuff(@Valid @ModelAttribute("babyStuff") BabyStuff babyStuff, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "stuff/updateStuffPage";
        } else {
            babyStuffService.save(babyStuff);
        }

        return "redirect:/babystuff/showAllStuff";

    }

    @GetMapping("/showParentStuff")
    private String showSingleParentStuff(Model model, Authentication authentication) throws Exception {

        GetUserID userID = new GetUserID(authentication);
        parentId = userID.get();

        BabyStuff parentStuff = babyStuffService.findById(parentId);
        model.addAttribute("parentStuff", parentStuff);

        return "stuff/listOfParentStuff";
    }


    private Integer stuffGuard(Integer stuffGuardCounter) {

        if (stuffGuardCounter > 0) {
            return stuffGuardCounter;
        } else {
            return 0;
        }
    }
}
