package com.PBJ.ChatRoom_Backend.service;

import com.PBJ.ChatRoom_Backend.dto.server.CreateDTO;
import com.PBJ.ChatRoom_Backend.dto.server.JoinDTO;
import com.PBJ.ChatRoom_Backend.dto.server.ServerListDTO;
import com.PBJ.ChatRoom_Backend.exception.server.ServerCreationException;
import com.PBJ.ChatRoom_Backend.exception.server.ServerNotFoundException;
import com.PBJ.ChatRoom_Backend.exception.user.UserNotFoundException;
import com.PBJ.ChatRoom_Backend.model.Server;
import com.PBJ.ChatRoom_Backend.model.ServerMember;
import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.repository.ServerMemberRepository;
import com.PBJ.ChatRoom_Backend.repository.ServerRepository;

import com.PBJ.ChatRoom_Backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ServerService {
    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerMemberRepository serverMemberRepository;

    @Autowired
    private HttpSession session;

    public Server createServer(CreateDTO createDTO) {
        // Check if the owner exists
        int userId = (int) session.getAttribute("userId");

        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        // Validate that the server name is unique
        if (serverRepository.existsByName(createDTO.getName())) {
            throw new ServerCreationException("A server with the name '" + createDTO.getName() + "' already exists.");
        }

        // Create and save the server
        Server server = new Server();
        server.setName(createDTO.getName());
        server.setDescription(createDTO.getDescription());
        server.setOwner(owner);
        serverRepository.save(server);

        // Update server member list with the owner's id, server id, and the role of the owner
        ServerMember serverMember = new ServerMember();
        serverMember.setServer(server);
        serverMember.setUser(owner);
        serverMember.setRole("Owner");
        serverMemberRepository.save(serverMember);

        return server;
    }

    public Server joinServer(@Valid JoinDTO joinDTO) {
        Integer userId = joinDTO.getUserId();
        Integer serverId = joinDTO.getServerId();
        String role = joinDTO.getRole();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new ServerNotFoundException("Server not found"));

        // Check if the user is already a member
        if (serverMemberRepository.existsByUserAndServer(user, server)){
            throw new IllegalStateException("User is already a member of the server");
        }

        ServerMember serverMember = new ServerMember();
        serverMember.setUser(user);
        serverMember.setServer(server);
        serverMember.setRole(role);
        serverMemberRepository.save(serverMember);

        return server;
    }

    public List<ServerListDTO> getAllServers() {
        // obtain userID from session
        Integer userId = (Integer) session.getAttribute("userId");

        // Fetch all serverMember entries for a specific userId
        List<ServerMember> serverMembers = serverMemberRepository.findByUserId(userId);

        // Extract all servers related to userId
        List<ServerListDTO> serversList = new ArrayList<>();
        for (ServerMember serverMember : serverMembers) {
            Server server = serverMember.getServer();
            serversList.add(new ServerListDTO(server.getId(), server.getName()));
        }

        return serversList;
    }


}
