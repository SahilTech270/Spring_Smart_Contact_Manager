package com.Smart_Contact_Manager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("home")
    public String home(){
        System.out.println("Hello");
        return "home";
    }


}
