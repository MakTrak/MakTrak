package com.codeup.maktrak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
<<<<<<< Updated upstream
        return "dashboard";
=======
        return "home";
>>>>>>> Stashed changes
    }
}
