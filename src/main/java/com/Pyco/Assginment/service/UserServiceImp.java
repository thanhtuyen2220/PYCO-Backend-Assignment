package com.Pyco.Assginment.service;

import com.Pyco.Assginment.model.ToDo;
import com.Pyco.Assginment.model.User;
import com.Pyco.Assginment.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(ObjectId Id) {
        return userRepository.findUserById(Id);
    }

    @Override
    public User UpdateUser(String userId,User updateUser) {
        User user = userRepository.findUserById(new ObjectId(userId));
        if(updateUser.getName()!= null){
            user.setName(updateUser.getName());
        }
        if(updateUser.getUsername()!=null){
            user.setUsername(updateUser.getUsername());
        }
        if(updateUser.getEmail()!=null){
            user.setEmail(updateUser.getEmail());
        }
        if(updateUser.getEnabled() != user.getEnabled()){
            user.setEnabled(updateUser.getEnabled());
        }
        return userRepository.save(user);
    }

    @Override
    public void DeleteUser(String id) {
        userRepository.deleteById(new ObjectId(id));
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserbyId(ObjectId id) {
        return userRepository.getById(id);
    }

    public Page<User> getUserByPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAll(pageable);
    }

}
