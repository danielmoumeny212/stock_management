package com.daniel.gestiondestock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception{
        http.securityMatcher("/gestiondestock/**")
            .authorizeHttpRequests(authorize ->
              authorize.anyRequest().anonymous());
        
        return http.build();
        
    }

    
    
}
