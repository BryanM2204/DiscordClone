package com.PBJ.ChatRoom_Backend.service;

import com.PBJ.ChatRoom_Backend.dto.user.RegisterDTO;
import com.PBJ.ChatRoom_Backend.dto.user.LoginDTO;

import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean loginUser(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = userRepository.findByUsername(username);

        if(user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());

    }

    public void registerUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());

        String hashedPassword = passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);

    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }


}
