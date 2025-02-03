package com.PBJ.ChatRoom_Backend.exception;

import com.PBJ.ChatRoom_Backend.exception.server.ServerCreationException;
import com.PBJ.ChatRoom_Backend.exception.server.ServerNotFoundException;
import com.PBJ.ChatRoom_Backend.exception.session.SessionNotFoundException;
import com.PBJ.ChatRoom_Backend.exception.user.UnauthorizedException;
import com.PBJ.ChatRoom_Backend.exception.user.UserAlreadyExistsException;
import com.PBJ.ChatRoom_Backend.exception.user.UserNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.PBJ.ChatRoom_Backend.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServerCreationException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleServerCreationException(ServerCreationException e) {
        logger.error("Server Creation Exception: ", e);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Failed to create server");
        errorDetails.put("error_code", "SERVER_CREATION_ERROR");
        errorDetails.put("timestamp", LocalDateTime.now());

        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(
                "error",
                errorDetails
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerNotFoundException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleServerNotFoundException(ServerNotFoundException e) {
        logger.error("Server Not Found Exception: ", e);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Server Not Found");
        errorDetails.put("error_code", "SERVER_NOT_FOUND_ERROR");
        errorDetails.put("timestamp", LocalDateTime.now());

        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(
                "error",
                errorDetails
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleBadCredentialsException(UsernameNotFoundException e) {
        logger.warn("Bad Credentials Exception: ", e);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Invalid Username or Password");
        errorDetails.put("error_code", "INVALID_CREDENTIALS");
        errorDetails.put("timestamp", LocalDateTime.now());
        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(
                "error",
                errorDetails
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleUserNotFoundException(UserNotFoundException e) {
        logger.warn("User Not Found Exception: ", e);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "User was not found");
        errorDetails.put("error_code", "USER_NOT_FOUND_ERROR");
        errorDetails.put("timestamp", LocalDateTime.now());
        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(
                "error",
                errorDetails
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        logger.warn("User Already Exists Exception: ", e);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "User Already Exists");
        errorDetails.put("error_code", "USER_ALREADY_EXISTS");
        errorDetails.put("timestamp", LocalDateTime.now());
        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(
                "error",
                errorDetails
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleSessionNotFoundException(SessionNotFoundException e) {
        logger.warn("Session Not Found Exception: ", e);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Session Not Found");
        errorDetails.put("error_code", "SESSION_NOT_FOUND_ERROR");
        errorDetails.put("timestamp", LocalDateTime.now());
        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(
                "error",
                errorDetails
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> UnauthorizedException(Exception e) {
        logger.warn("Unauthorized Exception: ", e);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Unauthorized");
        errorDetails.put("error_code", "UNAUTHORIZED");
        errorDetails.put("timestamp", LocalDateTime.now());
        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(
                "error",
                errorDetails
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleGenericException(Exception e) {
        logger.error("Unhandled Exception: ", e);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "An unexpected error occurred");
        errorDetails.put("error_code", "INTERNAL_ERROR");
        errorDetails.put("timestamp", LocalDateTime.now());

        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>(
                "error",
                errorDetails
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}