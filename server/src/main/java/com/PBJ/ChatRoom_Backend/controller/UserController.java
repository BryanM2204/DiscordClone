package com.PBJ.ChatRoom_Backend.controller;

import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.repository.UserRepository;
import com.PBJ.ChatRoom_Backend.util.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ApiResponse<String> addNewUser(@RequestParam String username, @RequestParam String email) {
        try {
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            User n = new User();
            n.setUsername(username);
            n.setEmail(email);
            System.out.println(n);
            userRepository.save(n);

            return new ApiResponse<>("reply", "add_user", Map.of("Executed", true, "message", "User added successfully"));
        } catch (Exception e) {
            System.out.println("DIDN'T WORK" + e.getMessage());
            return new ApiResponse<>("reply", "error", Map.of("Executed", false, "message", "Error occurred: " + e.getMessage()));
        }
    }

    @GetMapping(path="/all")
    public ApiResponse<String> getAllUsers() {
        return new ApiResponse<>("reply", "all_users", Map.of("Executed", true, "message", userRepository.findAll()));
    }
}
