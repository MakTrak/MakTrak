package com.codeup.maktrak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm(Model m) {
        m.addAttribute("error", "");
        return "users/login";
    }

    @GetMapping("/login/register-error")
    public String showLoginForm2(Model m) {
        m.addAttribute("error", "You registered with an existing Username or Email: Try logging in!");
        return "users/login";
    }
}
