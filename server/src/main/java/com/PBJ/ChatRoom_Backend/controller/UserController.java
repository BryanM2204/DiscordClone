package com.PBJ.ChatRoom_Backend.controller;

import com.PBJ.ChatRoom_Backend.model.User;
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


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        boolean Result = userService.loginUser(loginDTO);

        if (Result) {
            ApiResponse<String> response = new ApiResponse<>(
                    "Login",
                    Map.of("Executed", true, "Message", "Login was a success!")
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<String> response = new ApiResponse<>(
                    "Login Fail",
                    Map.of("Executed", false, "Message", "Login failed! Wrong username or password!")
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);

        ApiResponse<String> response = new ApiResponse<>(
                "Registration",
                Map.of("Executed", true, "Message", "Registration was a success!")
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(path="/all")
    public ResponseEntity<ApiResponse<String>> getAllUsers() {

        Iterable<User> data = userService.getUsers();

        ApiResponse<String> response = new ApiResponse<>(
                "all_users",
                Map.of("Executed", true, "data", data)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);


    }
}

