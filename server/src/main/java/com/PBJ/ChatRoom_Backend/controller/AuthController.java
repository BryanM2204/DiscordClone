package com.PBJ.ChatRoom_Backend.controller;

import com.PBJ.ChatRoom_Backend.dto.user.LoginDTO;
import com.PBJ.ChatRoom_Backend.dto.user.RegisterDTO;
import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.service.AuthService;
import com.PBJ.ChatRoom_Backend.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@Valid @RequestBody LoginDTO loginDTO){
        User user = authService.loginUser(loginDTO);

        ApiResponse<String> response = new ApiResponse<>(
                "Login successful",
                Map.of("Executed", true,
                        "message", "Login was a success!",
                        "userID", user.getId())
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody RegisterDTO registerDTO){
        User user = authService.registerUser(registerDTO);

        ApiResponse<String> response = new ApiResponse<>(
                "Registration successful",
                Map.of("Executed", true,
                        "message", "Registration was successful",
                        "userID", user.getId())
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
