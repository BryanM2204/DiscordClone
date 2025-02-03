package com.PBJ.ChatRoom_Backend.service;

import com.PBJ.ChatRoom_Backend.dto.user.LoginDTO;
import com.PBJ.ChatRoom_Backend.dto.user.RegisterDTO;
import com.PBJ.ChatRoom_Backend.exception.user.UserAlreadyExistsException;
import com.PBJ.ChatRoom_Backend.exception.user.UserNotFoundException;
import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User loginUser(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User with username" + username + " not found"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Create authentication token
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, password);

        // Authenticate
        Authentication authentication = authenticationManager.authenticate(authToken);

        // Create new security context and store it
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Store in session
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());

        System.out.println("Session Created for User " + username);

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
