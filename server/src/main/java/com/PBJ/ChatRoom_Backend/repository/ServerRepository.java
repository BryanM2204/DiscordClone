package com.PBJ.ChatRoom_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PBJ.ChatRoom_Backend.model.Server;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Integer> {
    boolean existsByName(String name);

}
