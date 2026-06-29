package com.nocturne.app.controller;

import com.nocturne.app.model.User;
import com.nocturne.app.service.VibeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VibeController {

    private final VibeService vibeService;

    public VibeController(VibeService vibeService) {
        this.vibeService = vibeService;
    }

    @GetMapping("/post-vibe")
    public String postVibeForm(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("activePage", "post-vibe");
        return "post-vibe";
    }

    @PostMapping("/post-vibe")
    public String postVibe(@RequestParam String venueName, @RequestParam String caption,
                            @RequestParam int heatLevel, HttpSession session,
                            RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        vibeService.createPost(currentUser.getName(), venueName, caption, heatLevel, false);
        redirectAttributes.addFlashAttribute("successMessage", "Your vibe is live on the feed.");
        return "redirect:/";
    }

    @GetMapping("/go-live")
    public String goLiveForm(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("activePage", "go-live");
        return "go-live";
    }

    @PostMapping("/go-live")
    public String goLive(@RequestParam String venueName, @RequestParam String caption,
                          HttpSession session, RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        vibeService.createPost(currentUser.getName(), venueName, caption, 5, true);
        redirectAttributes.addFlashAttribute("successMessage", "You're live. Your broadcast is on the feed now.");
        return "redirect:/";
    }
}
