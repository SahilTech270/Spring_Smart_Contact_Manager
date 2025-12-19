package com.Smart_Contact_Manager.Config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.Smart_Contact_Manager.Services.Implement.SecurityCustomUserService;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserService securityCustomUserService;

    @Autowired
    private OAuthAuthenthicationSuccessHandeler handler;


    // User Details Service Bean
    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    // {

    // UserDetails admin = User.builder()
    // .username("Admin")
    // .password(passwordEncoder.encode("bankai"))
    // .roles("ADMIN", "USER")
    // .build();

    // return new InMemoryUserDetailsManager(admin);
    // }

    // Authentication Provider Bean
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(securityCustomUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Adding Authization rules(which user can access which url) Managing Routes
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authroize -> {
            authroize.requestMatchers("/user/**").authenticated();
            authroize.anyRequest().permitAll();
        });

        httpSecurity.formLogin(formlogin -> {
            formlogin.loginPage("/login");
            formlogin.loginProcessingUrl("/authenticate");
            formlogin.defaultSuccessUrl("/user/dashboard", true);
            formlogin.failureUrl("/login?error=true");

            formlogin.usernameParameter("email");
            formlogin.passwordParameter("password");

        });

        // httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login");
        });

        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);

        });

        return httpSecurity.build();
    }

}
