package com.PBJ.ChatRoom_Backend.service;

import com.PBJ.ChatRoom_Backend.dto.user.LoginDTO;
import com.PBJ.ChatRoom_Backend.dto.user.RegisterDTO;
import com.PBJ.ChatRoom_Backend.exception.user.UserAlreadyExistsException;
import com.PBJ.ChatRoom_Backend.exception.user.UserNotFoundException;
import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User loginUser(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User with username" + username + " not found"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return user;
    }

    public User registerUser(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String email = registerDTO.getEmail();

        User user = new User();

        // check to see if user doesn't exist in db
        if(userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User with username" + username + " already exists");
        }

        user.setUsername(username);
        user.setEmail(email);

        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);

        return userRepository.save(user);

    }
}
