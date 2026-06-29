package com.nocturne.app.controller;

import com.nocturne.app.service.EventService;
import com.nocturne.app.service.VenueService;
import com.nocturne.app.service.VibeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExploreController {

    private final EventService eventService;
    private final VenueService venueService;
    private final VibeService vibeService;

    public ExploreController(EventService eventService, VenueService venueService, VibeService vibeService) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.vibeService = vibeService;
    }

    @GetMapping("/")
    public String explore(Model model, HttpSession session) {
        model.addAttribute("topVenues", venueService.findTopVibing(3));
        model.addAttribute("upcomingEvents", eventService.findUpcoming(4));
        model.addAttribute("recentVibes", vibeService.findRecent(5));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        model.addAttribute("activePage", "explore");
        return "explore";
    }
}
