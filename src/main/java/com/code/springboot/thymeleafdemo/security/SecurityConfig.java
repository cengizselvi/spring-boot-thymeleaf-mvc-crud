package com.code.springboot.thymeleafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManagerConfigurer(){
        UserDetails c = User.builder()
                .username("cengiz")
                .password("{noop}123")
                .build();
        return new InMemoryUserDetailsManager(c);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "news","image","search").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form

                        .loginPage("/news")
                        .loginProcessingUrl("/login")

                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
