package com.example.loginapp.controller;

import com.example.loginapp.model.User;
import com.example.loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Show login form
    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    // Handle login form submit
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        User existing = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existing != null) {
            model.addAttribute("message", "Login successful!");
            return "login-success";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    // Show register form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle register form submit
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        userRepository.save(user);
        model.addAttribute("message", "Registration successful. Please log in.");
        model.addAttribute("user", new User());  // Clear form
        return "login";
    }
}

