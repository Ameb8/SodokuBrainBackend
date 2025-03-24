package com.example.SodokuBrainBackend.Config;

import com.example.SodokuBrainBackend.Security.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/secured/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.userService(customOAuth2UserService)
                                )
                                .successHandler((request, response, authentication) -> {
                                    response.sendRedirect("http://localhost:5173"); // Change to your frontend URL
                                })
                )
                .logout(logout -> logout.logoutSuccessUrl("/")); // Ensure logout redirects to a proper page



        return http.build();
    }
}

