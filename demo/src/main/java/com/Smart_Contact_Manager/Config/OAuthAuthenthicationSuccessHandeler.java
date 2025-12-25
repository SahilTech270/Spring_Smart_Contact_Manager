package com.Smart_Contact_Manager.Config;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.Smart_Contact_Manager.Entity.UserEntity;
import com.Smart_Contact_Manager.Repository.UserRepo;
import com.Smart_Contact_Manager.helpers.Helper;

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

        var auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        String registrationId = auth2AuthenticationToken.getAuthorizedClientRegistrationId();

        logger.info(registrationId);

        var oauth2 = (DefaultOAuth2User) authentication.getPrincipal();

        oauth2.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        UserEntity user = new UserEntity();

        user.setUserId(UUID.randomUUID().toString());
        user.setActive(true);
        user.setEmailVerified(true);

        // Get user email using Helper to ensure consistency
        String email = Helper.getLoggedEmailId(authentication);
        user.setEmail(email);

        // google
        if (registrationId.equalsIgnoreCase("google")) {
            String name = oauth2.getAttribute("name");
            // String email = oauth2.getAttribute("email"); // Removed
            String picture = oauth2.getAttribute("picture");
            String googleId = oauth2.getAttribute("sub");

            user.setName(name);
            // user.setEmail(email); // Set above
            user.setProfilePic(picture);

            // Google users DON'T have passwords
            user.setPassword("password"); // dummy password (REQUIRED for DB)
            user.setPhoneNumber("00000000000");
            user.setAbout("Login with Google");
            user.setProvider("GOOGLE");
            user.setProviderId(googleId);

        } else if (registrationId.equalsIgnoreCase("github")) {

            // String email = oauth2.getAttribute("email") != null // Removed
            // ? oauth2.getAttribute("email").toString()
            // : oauth2.getAttribute("login").toString() + "@github.com";

            String name = oauth2.getAttribute("login").toString();
            String picture = oauth2.getAttribute("avatar_url").toString();
            String providerUserID = oauth2.getAttribute("name").toString();

            user.setName(name);
            // user.setEmail(email); // Set above
            user.setPassword("password");
            user.setProfilePic(picture);
            user.setPhoneNumber("00000000000");
            user.setAbout("Login with GitHub");
            user.setProvider("GitHub");
            user.setProviderId(providerUserID);

        } else {
            logger.warn("Unknown registration ID: " + registrationId);
        }

        UserEntity user2 = userRepo.findByEmail(email).orElse(null);

        if (user2 == null) {
            userRepo.save(user);

            logger.info("New User Registered: " + email);
        }

        response.sendRedirect("/user/profile");
    }

}
