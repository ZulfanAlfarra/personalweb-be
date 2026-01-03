package com.zulfan.personal_web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String publicPage(){
        return "index";
    }

    @GetMapping("/private")
    public String privatePage(Model model, Authentication authentication){
        model.addAttribute("name", getName(authentication));
        return "private";
    }

    public static String getName(Authentication authentication){
        return authentication.getName();
    }
}
