package com.PBJ.ChatRoom_Backend.controller;

import com.PBJ.ChatRoom_Backend.util.ApiResponse;
import com.PBJ.ChatRoom_Backend.service.UserService;
import com.PBJ.ChatRoom_Backend.dto.user.LoginDTO;
import com.PBJ.ChatRoom_Backend.dto.user.RegisterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    // To-Do - create endpoints:
    //  1. Fetch a user's info
    //  2. Update a user's info (use put)
    //  3. Search for a user

}

