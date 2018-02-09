package com.codeup.maktrak.controllers;



import com.codeup.maktrak.daos.UserRepository;
import com.codeup.maktrak.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
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
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String showdashboard(Model view) {
        view.addAttribute("user", new User());
        return "/dashboard";
    }

    @PostMapping("/dashboard")
    @ResponseBody
    public String test() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Username: "+currentUser.getUsername()+"; Password: "+currentUser.getPassword()+"; Email: "+currentUser.getEmail()+"; First Name: "+currentUser.getFirstname()+"; Last Name: "+currentUser.getLastname()+";";
    }
}