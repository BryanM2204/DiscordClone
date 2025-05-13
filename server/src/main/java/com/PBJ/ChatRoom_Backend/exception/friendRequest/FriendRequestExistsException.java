package com.PBJ.ChatRoom_Backend.exception.friendRequest;

public class FriendRequestExistsException extends RuntimeException {
    public FriendRequestExistsException(String message) {
        super(message);
    }
}
