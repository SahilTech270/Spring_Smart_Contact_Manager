package com.Smart_Contact_Manager.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class Helper {

    public static String getLoggedEmailId(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {

            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String clientId = oauthToken.getAuthorizedClientRegistrationId();

            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            // Google
            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Google User Logged In");
                username = oauth2User.getAttribute("email").toString();

                // GitHub
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("GitHub User Logged In");
                String email = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                        : null;
                String login = oauth2User.getAttribute("login") != null ? oauth2User.getAttribute("login").toString()
                        : null;

                username = email != null ? email : login + "@github.com";
            }

            return username;
        }

        // Normal LOGIN (username == email)
        System.out.println("Form User Logged In");
        return authentication.getName();
    }
}
