package com.nocturne.app.controller;

import com.nocturne.app.model.User;
import com.nocturne.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }
        model.addAttribute("activePage", "login");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,
                         HttpSession session, RedirectAttributes redirectAttributes) {
        var user = userService.authenticate(email, password);
        if (user.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email or password didn't match our records.");
            return "redirect:/login";
        }
        session.setAttribute("currentUser", user.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }
        model.addAttribute("activePage", "register");
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email,
                            @RequestParam String password, HttpSession session,
                            RedirectAttributes redirectAttributes) {
        if (userService.emailTaken(email)) {
            redirectAttributes.addFlashAttribute("errorMessage", "That email already has an account. Try logging in.");
            return "redirect:/register";
        }
        if (password == null || password.length() < 6) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password needs to be at least 6 characters.");
            return "redirect:/register";
        }
        User user = userService.register(name, email, password);
        session.setAttribute("currentUser", user);
        return "redirect:/";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordForm(Model model) {
        model.addAttribute("activePage", "forgot-password");
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, RedirectAttributes redirectAttributes) {
        // In a real app this would email a secure, expiring link.
        // For this demo we just hand the email straight to the reset form.
        redirectAttributes.addFlashAttribute("successMessage",
                "If that email has an account, a reset link just went out. For this demo, continue below.");
        redirectAttributes.addAttribute("email", email);
        return "redirect:/reset-password";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam(required = false) String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("activePage", "reset-password");
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String password,
                                 RedirectAttributes redirectAttributes) {
        if (!userService.emailTaken(email)) {
            redirectAttributes.addFlashAttribute("errorMessage", "We couldn't find an account with that email.");
            return "redirect:/forgot-password";
        }
        if (password == null || password.length() < 6) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password needs to be at least 6 characters.");
            return "redirect:/reset-password?email=" + email;
        }
        userService.resetPassword(email, password);
        redirectAttributes.addFlashAttribute("successMessage", "Password updated. Go ahead and log in.");
        return "redirect:/login";
    }
}
