package com.first.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.first.project.model.CustomUserDetails;
import com.first.project.repo.SignInRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SignInRepo repo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        CustomUserDetails user = repo.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(user.getUserName(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
