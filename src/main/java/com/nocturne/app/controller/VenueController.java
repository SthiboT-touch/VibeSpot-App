package com.nocturne.app.controller;

import com.nocturne.app.service.EventService;
import com.nocturne.app.service.VenueService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VenueController {

    private final VenueService venueService;
    private final EventService eventService;

    public VenueController(VenueService venueService, EventService eventService) {
        this.venueService = venueService;
        this.eventService = eventService;
    }

    @GetMapping("/venues/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        var venue = venueService.findById(id);
        if (venue.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "That venue doesn't exist (anymore).");
            return "redirect:/events";
        }
        model.addAttribute("venue", venue.get());
        model.addAttribute("venueEvents", eventService.findByVenue(id));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        model.addAttribute("activePage", "events");
        return "venue-detail";
    }
}
