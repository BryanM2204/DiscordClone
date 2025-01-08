package com.PBJ.ChatRoom_Backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Username is required")
    private String username;

    @NotBlank(message="Email is required")
    @Email(message="Email must be valid")
    private String email;

    @NotBlank(message="Password is required")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServerMember> serverMembers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ServerMember> getServerMembers() { return serverMembers; }

    public void setServerMembers(List<ServerMember> serverMembers) { this.serverMembers = serverMembers; }
}
