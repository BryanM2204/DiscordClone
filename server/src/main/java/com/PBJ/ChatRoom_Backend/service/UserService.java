package com.PBJ.ChatRoom_Backend.service;


import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }


}
