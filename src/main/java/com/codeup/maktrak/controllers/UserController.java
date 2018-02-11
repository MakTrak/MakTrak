package com.codeup.maktrak.controllers;



import com.codeup.maktrak.daos.UserRepository;
import com.codeup.maktrak.models.DailyMacro;
import com.codeup.maktrak.models.User;
import com.codeup.maktrak.models.WeeklySchedule;
import com.codeup.maktrak.services.DaoOpService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    private DaoOpService service;
    private UserRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(DaoOpService service, UserRepository users, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model m) {
        m.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpNewUser(@ModelAttribute User user) {
        if(service.userNameOrEmailExists(user.getUsername(), user.getEmail())) {
            return "redirect:/login/register-error";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String showdashboard(Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!service.scheduleExists(user)) {
            service.newSchedule(user);
        }
        List<DailyMacro> macros = service.findMacrosByUser(user);
        m.addAttribute("macros", macros);
        m.addAttribute("user", new User());
        return "/dashboard";
    }

//    @PostMapping("/dashboard")
//    @ResponseBody
//    public String test() {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return "Username: "+currentUser.getUsername()+"; Password: "+currentUser.getPassword()+"; Email: "+currentUser.getEmail()+"; First Name: "+currentUser.getFirstname()+"; Last Name: "+currentUser.getLastname()+";";
//    }
}