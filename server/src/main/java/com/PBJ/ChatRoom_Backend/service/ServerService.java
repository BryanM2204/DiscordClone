package com.PBJ.ChatRoom_Backend.service;

import com.PBJ.ChatRoom_Backend.dto.server.CreateDTO;
import com.PBJ.ChatRoom_Backend.exception.server.ServerCreationException;
import com.PBJ.ChatRoom_Backend.exception.user.UserNotFoundException;
import com.PBJ.ChatRoom_Backend.model.Server;
import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.repository.ServerRepository;

import com.PBJ.ChatRoom_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServerService {
    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private UserRepository userRepository;

    public Server createServer(CreateDTO createDTO) {
        // Check if the owner exists
        User owner = userRepository.findById(createDTO.getOwnerId())
                .orElseThrow(() -> new UserNotFoundException("User with ID " + createDTO.getOwnerId() + " not found"));

        // Validate that the server name is unique
        if (serverRepository.existsByName(createDTO.getName())) {
            throw new ServerCreationException("A server with the name '" + createDTO.getName() + "' already exists.");
        }

        // Create and save the server
        Server server = new Server();
        server.setName(createDTO.getName());
        server.setDescription(createDTO.getDescription());
        server.setOwner(owner);

        return serverRepository.save(server);
    }
}
