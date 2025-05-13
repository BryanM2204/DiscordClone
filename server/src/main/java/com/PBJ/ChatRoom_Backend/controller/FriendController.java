package com.PBJ.ChatRoom_Backend.controller;

import com.PBJ.ChatRoom_Backend.dto.friend.FriendListDTO;
import com.PBJ.ChatRoom_Backend.model.Friend;
import com.PBJ.ChatRoom_Backend.model.FriendRequest;
import com.PBJ.ChatRoom_Backend.service.FriendService;
import com.PBJ.ChatRoom_Backend.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<String>> getAllFriends() {
        List<FriendListDTO> friendList = friendService.getAllFriends();

        ApiResponse<String> response = new ApiResponse<>(
                "Get all Friends",
                Map.of("Executed", true,
                        "friendsList", friendList)
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/requests/{username}")
    public ResponseEntity<ApiResponse<String>> sendFriendRequest(@PathVariable String username){
        FriendRequest friendRequest = friendService.sendFriendRequest(username);

        ApiResponse<String> response = new ApiResponse<>(
                "Friend Request Sent",
                Map.of("Executed", true,
                        "message", "Friend request was sent, waiting on approval")
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // The person who sent the friend request has their username sent as a path variable
    // This is from the stance of a user accepting a friend request
    @PostMapping("/requests/accept/{username}")
    public ResponseEntity<ApiResponse<String>> acceptFriendRequest(@PathVariable String username){

        Friend friend = friendService.acceptFriendRequest(username);

        ApiResponse<String> response = new ApiResponse<>(
                "Friend Request accepted",
                Map.of("Executed", true)
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/requests/deny/{username}")
    public ResponseEntity<ApiResponse<String>> denyFriendRequest(@PathVariable String username){

        FriendRequest friendRequest = friendService.denyFriendRequest(username);

        ApiResponse<String> response = new ApiResponse<>(
                "Friend Request denied",
                Map.of("Executed", true)
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
