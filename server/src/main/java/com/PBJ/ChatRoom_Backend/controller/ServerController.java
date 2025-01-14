package com.PBJ.ChatRoom_Backend.controller;

import com.PBJ.ChatRoom_Backend.dto.server.CreateDTO;
import com.PBJ.ChatRoom_Backend.dto.server.JoinDTO;
import com.PBJ.ChatRoom_Backend.dto.server.ServerListDTO;
import com.PBJ.ChatRoom_Backend.model.Server;
import com.PBJ.ChatRoom_Backend.service.ServerService;
import com.PBJ.ChatRoom_Backend.util.ApiResponse;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<String>> joinServer(@Valid @RequestBody JoinDTO joinDTO){
        Server server = serverService.joinServer(joinDTO);

        ApiResponse<String> response = new ApiResponse<>(
                "Joined Server",
                Map.of("Executed", true,
                        "serverID", server.getId(),
                        "serverName", server.getName())
        );

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<ApiResponse<String>> listServers(@PathVariable Integer userId) {
        List<ServerListDTO> serverList = serverService.getAllServers(userId);

        ApiResponse<String> response = new ApiResponse<>(
                "List of Servers",
                Map.of("Executed", true,
                        "serverList", serverList)
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
