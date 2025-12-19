package com.Smart_Contact_Manager.Config;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.Smart_Contact_Manager.Entity.UserEntity;
import com.Smart_Contact_Manager.Repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class OAuthAuthenthicationSuccessHandeler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenthicationSuccessHandeler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.info("Authentication Success");

        // identify the provider
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        // logger.info(user.getName());

        // user.getAttributes().forEach((key, value) -> {
        // logger.info(key + " : " + value);
        // });

        // user.getAuthorities().toString();

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");

        UserEntity user1 = new UserEntity();

        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setUserId(UUID.randomUUID().toString());
        user1.setPassword("password");
        user1.setPhoneNumber("0000000000");
        user1.setAbout("Login by Google");
        user1.setEmailVerified(true);
        user1.setActive(true);
        user1.setProvider("Google");
        user1.setProviderId(UUID.randomUUID().toString());

        UserEntity user2 = userRepo.findByEmail(email).orElse(null);
        if (user2 == null) {
            userRepo.save(user1);
            logger.info("New User Registered: " + email);
        }

        response.sendRedirect("/user/dashboard");
    }

}
