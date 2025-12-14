package com.Smart_Contact_Manager.Controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Smart_Contact_Manager.Form.UserForm;

import org.springframework.ui.Model;



@Controller
public class PageController {

    @RequestMapping("home")
    public String home(){
        System.out.println("HI");
        return "home";
    }

    @RequestMapping("about")
    public String about(){
        return "about";
    }

    @RequestMapping("service")
    public String service(){
        return "service";
    }

    @RequestMapping("contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(Model model){

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        
        return "register";
    }

    @PostMapping(value = "/do-Register")
    public String processRegistraction(@ModelAttribute UserForm userForm){

        System.out.println(userForm);
    
        return "redirect:/register";
    }


}
