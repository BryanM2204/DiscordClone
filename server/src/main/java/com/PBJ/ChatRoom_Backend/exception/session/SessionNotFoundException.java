package com.PBJ.ChatRoom_Backend.exception.session;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }
}
