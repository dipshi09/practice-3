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
        return "login"; // login.html
    }

    // Handle login form submission
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        try {
            User existing = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (existing != null) {
                model.addAttribute("message", "Welcome, " + existing.getUsername() + "!");
                return "login-success";  // login-success.html
            } else {
                model.addAttribute("message", "Invalid credentials. Please try again.");
                return "error"; // error.html for login failure
            }
        } catch (Exception ex) {
            model.addAttribute("message", "Unexpected error: " + ex.getMessage());
            return "error"; // fallback error.html
        }
    }

    // Optional: redirect target for successful login (if using redirect)
    @GetMapping("/login-success")
    public String showSuccessPage() {
        return "login-success";
    }

    // Optional: register page if needed
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
}

