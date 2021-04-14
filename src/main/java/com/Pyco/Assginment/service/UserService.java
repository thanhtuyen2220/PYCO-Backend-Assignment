package com.Pyco.Assginment.service;

import com.Pyco.Assginment.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(ObjectId Id);
    User UpdateUser(User user,User user_new);
    User findUserByEmail(String email);
    Page<User>getUserByPage(int page,int size);

}
