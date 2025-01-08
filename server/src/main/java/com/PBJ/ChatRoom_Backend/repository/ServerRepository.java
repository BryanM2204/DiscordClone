package com.PBJ.ChatRoom_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PBJ.ChatRoom_Backend.model.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Integer> {
    boolean existsByName(String name);
}
