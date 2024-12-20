package com.PBJ.ChatRoom_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PBJ.ChatRoom_Backend.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
