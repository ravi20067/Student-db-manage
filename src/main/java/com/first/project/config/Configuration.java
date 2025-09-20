package com.first.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
public class Configuration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("student").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())//it only show login page by popuup
//                .csrf(csrf -> csrf.disable())//for disable csrf security
                .formLogin(Customizer.withDefaults());//it made a proper page
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withUsername("admin")
                .password("{noop}admin123")
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withUsername("admin")
                .password("{noop}admin123")
                .roles("USER")
                .build();
        
        return new InMemoryUserDetailsManager(user1,user2);
    }


}
