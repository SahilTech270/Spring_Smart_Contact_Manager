package com.Smart_Contact_Manager.Controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Smart_Contact_Manager.Entity.UserEntity;
import com.Smart_Contact_Manager.Services.UserService;
import com.Smart_Contact_Manager.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    @GetMapping(value = "/profile")
    public String userProfile(Authentication authentication) {

        String userName = Helper.getLoggedEmailId(authentication);

        UserEntity user = userService.getUserByEmail(userName);

        System.out.println(user.getName());
        System.out.println(user.getEmail());
        logger.info("User Logged in : {} ", userName);
        return "user/profile";
    }
}
