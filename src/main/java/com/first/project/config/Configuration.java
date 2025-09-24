package com.first.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.first.project.service.CustomUserDetailsService;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
public class Configuration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/signup/register").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())//it only show login page by popuup
                .csrf(csrf -> csrf.disable())//for disable csrf security
                .formLogin(Customizer.withDefaults());//it made a proper page
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user1 = User
    //             .withUsername("admin")
    //             .password("{noop}admin123")
    //             .roles("USER")
    //             .build();
    //     return new InMemoryUserDetailsManager(user1);
    // }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder(5));

        return authProvider;
    }
}
