package com.PBJ.ChatRoom_Backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    // Create a session and store an attribute
    @GetMapping("/create/{userId}")
    public String createSession(HttpSession session, @PathVariable String userId) {
        // Set a session attribute (e.g., username)
        session.setAttribute("userId", userId);

        // Retrieve and return the session ID
        String sessionId = session.getId();
        return "Session created with ID: " + sessionId;
    }

    // Retrieve session attribute
    @GetMapping("/get")
    public String getSession(HttpSession session) {
        // Get the session attribute (username)
        String user_id = (String) session.getAttribute("userId");

        // If no session exists, return an error message
        if (user_id == null) {
            return "No session found!";
        }

        // Return session data to the client
        return "Session found with userId: " + user_id;
    }

    // Invalidate the session
    @GetMapping("/invalidate")
    public String invalidateSession(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        return "Session invalidated!";
    }
}
