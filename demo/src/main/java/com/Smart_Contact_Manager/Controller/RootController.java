package com.Smart_Contact_Manager.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Smart_Contact_Manager.Entity.UserEntity;
import com.Smart_Contact_Manager.Services.UserService;
import com.Smart_Contact_Manager.helpers.Helper;

@ControllerAdvice
public class RootController {

    Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUser(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }
        System.out.println("Adding Logged In User From Model Attribute");
        String userName = Helper.getLoggedEmailId(authentication);
        UserEntity user = userService.getUserByEmail(userName);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);
        logger.info("User Logged in : {} ", userName);
    }
}
