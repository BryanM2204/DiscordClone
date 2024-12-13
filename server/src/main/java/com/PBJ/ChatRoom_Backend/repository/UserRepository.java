package com.PBJ.ChatRoom_Backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.PBJ.ChatRoom_Backend.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
