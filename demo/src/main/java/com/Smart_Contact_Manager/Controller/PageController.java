package com.Smart_Contact_Manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Smart_Contact_Manager.Entity.UserEntity;
import com.Smart_Contact_Manager.Form.UserForm;
import com.Smart_Contact_Manager.Services.UserService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;



@Controller("/")
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(){
        System.out.println("HI");
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/service")
    public String service(){
        return "service";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        
        return "register";
    }

    @PostMapping(value = "/do-register")
    public String processRegistraction(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult){

            // UserEntity user = UserEntity.builder()
            //         .name(userForm.getName())
            //         .email(userForm.getEmail())
            //         .password(userForm.getPassword())
            //         .phoneNumber(userForm.getPhoneNumber())
            //         .about(userForm.getAbout())
            //         .build();
            
            if(rBindingResult.hasErrors()){
                System.out.println("Something went wrong");
                return "register";
            }

            UserEntity user = new UserEntity();
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());        
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setAbout(userForm.getAbout());


            userService.saveUser(user);
            System.out.println(userForm); 

    
        return "redirect:/login";
    }


}
