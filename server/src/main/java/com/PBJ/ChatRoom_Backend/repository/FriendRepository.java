package com.PBJ.ChatRoom_Backend.repository;

import com.PBJ.ChatRoom_Backend.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Query(value = "SELECT * FROM friend WHERE user1_id = :id or user2_id = :id", nativeQuery = true)
    List<Friend> getAllByUserId(int id);
}
