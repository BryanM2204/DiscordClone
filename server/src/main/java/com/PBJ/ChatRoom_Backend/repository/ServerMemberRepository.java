package com.PBJ.ChatRoom_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PBJ.ChatRoom_Backend.model.ServerMember;

@Repository
public interface ServerMemberRepository extends JpaRepository<ServerMember, Integer> {
}
