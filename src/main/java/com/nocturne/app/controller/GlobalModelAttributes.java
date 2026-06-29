package com.nocturne.app.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Makes "currentUser" available in every Thymeleaf template without each
 * controller needing to add it by hand. Individual controllers can still
 * add it explicitly (e.g. when they also need it for their own logic) —
 * the value will simply be the same.
 */
@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentUser")
    public Object currentUser(HttpSession session) {
        return session.getAttribute("currentUser");
    }
}
