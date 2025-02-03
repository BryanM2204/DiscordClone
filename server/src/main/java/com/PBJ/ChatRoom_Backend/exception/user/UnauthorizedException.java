package com.PBJ.ChatRoom_Backend.exception.user;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
