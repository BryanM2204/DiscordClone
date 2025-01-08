package com.PBJ.ChatRoom_Backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Server {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Server name is required")
    @Size(min = 2, max = 100, message="Server name must be between 2 to 100 characters")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private String description;

    @OneToMany(mappedBy = "server", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServerMember> serverMembers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ServerMember> getServerMembers() {
        return serverMembers;
    }

    public void setServerMembers(List<ServerMember> serverMembers) {
        this.serverMembers = serverMembers;
    }
}
