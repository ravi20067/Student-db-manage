package com.first.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.first.project.model.CustomUserDetails;
import com.first.project.repo.SignUpRepo;



@Controller
public class SignUpController {
    


    @Autowired
    private SignUpRepo repo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
    
    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("user", new CustomUserDetails());
        return "signup";
    }
    

    @PostMapping("/signup/register")
    public String postMethodName(@ModelAttribute CustomUserDetails entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        repo.save(entity);
        return "redirect:/login";

    }
}
