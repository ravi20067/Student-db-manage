package com.first.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.first.project.model.User;
import com.first.project.repo.SignUpRepo;


@RestController
public class SignUpController {
    

    @Autowired
    SignUpRepo repo;
    
    @PostMapping("/signup")
    public String postMethodName(@RequestBody User entity) {
        
        repo.save(entity);
        return "User registered successfully";
        
    }
}
