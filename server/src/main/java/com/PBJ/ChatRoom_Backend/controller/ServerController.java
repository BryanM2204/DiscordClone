package com.PBJ.ChatRoom_Backend.controller;

import com.PBJ.ChatRoom_Backend.dto.server.CreateDTO;
import com.PBJ.ChatRoom_Backend.model.Server;
import com.PBJ.ChatRoom_Backend.service.ServerService;
import com.PBJ.ChatRoom_Backend.util.ApiResponse;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/server")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> createServer(@Valid @RequestBody CreateDTO createDTO){
        Server server = serverService.createServer(createDTO);

        ApiResponse<String> response = new ApiResponse<>(
                "Created Server",
                Map.of("Executed", true,
                        "message", "Server created successfully",
                        "serverID", server.getId(),
                        "serverName", server.getName()
                )
        );

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


}
