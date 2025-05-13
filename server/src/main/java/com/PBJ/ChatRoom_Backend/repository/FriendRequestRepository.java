package com.PBJ.ChatRoom_Backend.repository;


import com.PBJ.ChatRoom_Backend.model.FriendRequest;
import com.PBJ.ChatRoom_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {
    boolean existsBySenderAndReceiver(User sender, User receiver);

    FriendRequest findBySenderAndReceiver(User sender, User receiver);
}
