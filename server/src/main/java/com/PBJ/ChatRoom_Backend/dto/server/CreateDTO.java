package com.PBJ.ChatRoom_Backend.dto.server;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateDTO {
    @NotBlank(message="Name is required")
    private String name;

    @NotBlank(message="Description is required")
    private String description;

    public CreateDTO() {}

    public CreateDTO(String name, String description, Integer ownerId) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
