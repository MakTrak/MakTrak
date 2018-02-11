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
import org.springframework.web.bind.annotation.*;

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
        WeeklySchedule schedule = service.findSchedule(user);
        m.addAttribute("macros", macros);
        m.addAttribute("user", new User());
        if(schedule.getMondayMacro() == null) {
            m.addAttribute("mondayMacro", "None Selected");
        } else {
            m.addAttribute("mondayMacro", schedule.getMondayMacro().getTitle());
        }
        if(schedule.getTuesdayMacro() == null) {
            m.addAttribute("tuesdayMacro", "None Selected");
        } else {
            m.addAttribute("tuesdayMacro", schedule.getTuesdayMacro().getTitle());
        }
        if(schedule.getWednesdayMacro() == null) {
            m.addAttribute("wednesdayMacro", "None Selected");
        } else {
            m.addAttribute("wednesdayMacro", schedule.getWednesdayMacro().getTitle());
        }
        if(schedule.getThursdayMacro() == null) {
            m.addAttribute("thursdayMacro", "None Selected");
        } else {
            m.addAttribute("thursdayMacro", schedule.getThursdayMacro().getTitle());
        }
        if(schedule.getFridayMacro() == null) {
            m.addAttribute("fridayMacro", "None Selected");
        } else {
            m.addAttribute("fridayMacro", schedule.getFridayMacro().getTitle());
        }
        if(schedule.getSaturdayMacro() == null) {
            m.addAttribute("saturdayMacro", "None Selected");
        } else {
            m.addAttribute("saturdayMacro", schedule.getSaturdayMacro().getTitle());
        }
        if(schedule.getSundayMacro() == null) {
            m.addAttribute("sundayMacro", "None Selected");
        } else {
            m.addAttribute("sundayMacro", schedule.getSundayMacro().getTitle());
        }
        return "/dashboard";
    }

    @PostMapping("assign-daily-routine")
    public String changeDailyValue(@RequestParam(name = "day") String day, @RequestParam(name = "goalselect") long macroId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        switch (day) {
            case "monday":
                service.assignDayRoutine(user, (short)1, macroId);
                break;
            case "tuesday":
                service.assignDayRoutine(user, (short)2, macroId);
                break;
            case "wednesday":
                service.assignDayRoutine(user, (short)3, macroId);
                break;
            case "thursday":
                service.assignDayRoutine(user, (short)4, macroId);
                break;
            case "friday":
                service.assignDayRoutine(user, (short)5, macroId);
                break;
            case "saturday":
                service.assignDayRoutine(user, (short)6, macroId);
                break;
            case "sunday":
                service.assignDayRoutine(user, (short)7, macroId);
                break;
            default:
                break;
        }
        return "redirect:/dashboard";
    }

//    @PostMapping("/dashboard")
//    @ResponseBody
//    public String test() {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return "Username: "+currentUser.getUsername()+"; Password: "+currentUser.getPassword()+"; Email: "+currentUser.getEmail()+"; First Name: "+currentUser.getFirstname()+"; Last Name: "+currentUser.getLastname()+";";
//    }
}