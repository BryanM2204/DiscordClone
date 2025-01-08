package com.PBJ.ChatRoom_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PBJ.ChatRoom_Backend.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
