package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        boolean valid = userRepository.validate(username, password);
        if (valid) {
            model.addAttribute("username", username);
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}

