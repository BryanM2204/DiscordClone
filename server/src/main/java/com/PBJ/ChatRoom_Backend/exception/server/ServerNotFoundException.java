package com.PBJ.ChatRoom_Backend.exception.server;

public class ServerNotFoundException extends RuntimeException {
    public ServerNotFoundException(String message) {
        super(message);
    }
}
