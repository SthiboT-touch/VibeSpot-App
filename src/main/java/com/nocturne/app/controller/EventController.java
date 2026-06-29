package com.nocturne.app.controller;

import com.nocturne.app.service.EventService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String list(Model model, HttpSession session) {
        model.addAttribute("events", eventService.findAll());
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        model.addAttribute("activePage", "events");
        return "events";
    }

    @GetMapping("/events/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        var event = eventService.findById(id);
        if (event.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "That event doesn't exist (anymore).");
            return "redirect:/events";
        }
        model.addAttribute("event", event.get());
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        model.addAttribute("activePage", "events");
        return "event-detail";
    }

    @PostMapping("/events/{id}/interested")
    public String markInterested(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        eventService.markInterested(id);
        redirectAttributes.addFlashAttribute("successMessage", "You're on the list for this one.");
        return "redirect:/events/" + id;
    }
}
