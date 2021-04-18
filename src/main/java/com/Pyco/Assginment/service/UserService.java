package com.Pyco.Assginment.service;

import com.Pyco.Assginment.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User findUserById(ObjectId Id);
    User UpdateUser(String userId,User updateUser);
    void DeleteUser(String id);
    User findUserByEmail(String email);
    User getUserbyId(ObjectId id);
    Page<User>getUserByPage(int page,int size);

}
