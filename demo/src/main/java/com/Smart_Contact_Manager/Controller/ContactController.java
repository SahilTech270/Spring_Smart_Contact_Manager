package com.Smart_Contact_Manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Smart_Contact_Manager.Entity.ContactEntity;
import com.Smart_Contact_Manager.Entity.UserEntity;
import com.Smart_Contact_Manager.Form.ContactForm;
import com.Smart_Contact_Manager.Services.ContactService;
import com.Smart_Contact_Manager.Services.UserService;
import com.Smart_Contact_Manager.helpers.Helper;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String addContact(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contact", contactForm);
        contactForm.setName("Sahil");
        contactForm.setFavorite(true);
        return "User/add_contact";
    }

    @PostMapping(value = "/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, Authentication authentication) {

        String userName = Helper.getLoggedEmailId(authentication);
        UserEntity user = userService.getUserByEmail(userName);

        ContactEntity contact = new ContactEntity();

        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setFavorite(contactForm.isFavorite());
        contact.setWebsite(contactForm.getWebsite());
        contact.setLinkedIn(contactForm.getLinkedin());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setPhone(contactForm.getPhone());
        contact.setUserEntity(user);

        contactService.saveContact(contact);
        System.out.println(contactForm);

        return "redirect:/user/contact/add";
    }

}
