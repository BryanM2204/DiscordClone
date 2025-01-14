package com.PBJ.ChatRoom_Backend.dto.server;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JoinDTO {
    @NotNull(message="User ID is required")
    private Integer userId;

    @NotNull(message="Server ID is required")
    private Integer serverId;

    @NotBlank(message="Role is required")
    private String role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
