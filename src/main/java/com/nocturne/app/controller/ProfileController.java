package com.nocturne.app.controller;

import com.nocturne.app.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("activePage", "profile");
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam String bio, @RequestParam String favoriteGenre, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        currentUser.setBio(bio);
        currentUser.setFavoriteGenre(favoriteGenre);
        return "redirect:/profile";
    }
}
