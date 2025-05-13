package com.PBJ.ChatRoom_Backend.dto.friend;

public class FriendListDTO {
    private Integer friendId;
    private String username;

    public FriendListDTO() {}

    public FriendListDTO(Integer friendId, String username) {
        this.friendId = friendId;
        this.username = username;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
