package com.PBJ.ChatRoom_Backend.repository;

import com.PBJ.ChatRoom_Backend.model.Server;
import com.PBJ.ChatRoom_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PBJ.ChatRoom_Backend.model.ServerMember;

import java.util.List;


@Repository
public interface ServerMemberRepository extends JpaRepository<ServerMember, Integer> {
    boolean existsByUserAndServer(User user, Server server);

    List<ServerMember> findByUserId(Integer userId);
}
