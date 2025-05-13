package com.PBJ.ChatRoom_Backend.service;

import com.PBJ.ChatRoom_Backend.dto.friend.FriendListDTO;
import com.PBJ.ChatRoom_Backend.exception.friendRequest.FriendRequestExistsException;
import com.PBJ.ChatRoom_Backend.exception.friendRequest.FriendRequestMissingException;
import com.PBJ.ChatRoom_Backend.exception.user.UserNotFoundException;
import com.PBJ.ChatRoom_Backend.model.Friend;
import com.PBJ.ChatRoom_Backend.model.FriendRequest;
import com.PBJ.ChatRoom_Backend.model.User;
import com.PBJ.ChatRoom_Backend.repository.FriendRepository;
import com.PBJ.ChatRoom_Backend.repository.FriendRequestRepository;
import com.PBJ.ChatRoom_Backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class FriendService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private HttpSession session;

    public FriendRequest sendFriendRequest(String username) {

        Integer userId = (Integer) session.getAttribute("userId");

        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        User receiver = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        if(friendRequestRepository.existsBySenderAndReceiver(sender, receiver)){
            throw new FriendRequestExistsException("Friend request already exists. User with id: " + userId + " and username" + sender.getUsername()
            + " sent request to user with username: " + receiver.getUsername());
        }

        FriendRequest friendRequest = new FriendRequest(sender, receiver, "PENDING");
        friendRequestRepository.save(friendRequest);

        return friendRequest;
    }

    public Friend acceptFriendRequest(String username) {
        Integer userId = (Integer) session.getAttribute("userId");


        User receiver = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Person that sent the friend request
        User sender = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        if(friendRequestRepository.existsBySenderAndReceiver(sender, receiver)){
            FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver);

            friendRequest.setStatus("ACCEPTED");
            friendRequestRepository.save(friendRequest);

            // create entry in Friend table after accepting request
            Friend friendEntry = new Friend(sender, receiver);

            friendRepository.save(friendEntry);

            return friendEntry;
        } else {
            throw new FriendRequestMissingException("Friend request is missing");
        }

    }

    public FriendRequest denyFriendRequest(String username) {
        Integer userId = (Integer) session.getAttribute("userId");

        User receiver = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Person that sent friend request
        User sender = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        if(friendRequestRepository.existsBySenderAndReceiver(sender, receiver)){
            FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver);

            friendRequest.setStatus("DENIED");
            friendRequestRepository.save(friendRequest);

            return friendRequest;
        } else {
            throw new FriendRequestMissingException("Friend request is missing");
        }
    }

    public List<FriendListDTO> getAllFriends() {
        Integer userId = (Integer) session.getAttribute("userId");

        List<Friend> friends = friendRepository.getAllByUserId(userId);

        List<FriendListDTO> friendList = new ArrayList<>();
        for(Friend friend : friends) {
            User User;
            if(!Objects.equals(friend.getUser1().getId(), userId)) {
                User = friend.getUser1();
            } else {
                User = friend.getUser2();
            }
            friendList.add(new FriendListDTO(User.getId(), User.getUsername()));
        }

        return friendList;

    }


}
