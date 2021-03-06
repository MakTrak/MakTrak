package com.codeup.maktrak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/about")
    public String fetchAboutPage() {
        return "about";
    }

    @GetMapping("/landing")
    public String getLandingPage() {
        return "landing";
    }
}
