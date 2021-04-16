package com.Pyco.Assginment.service;

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
    public User UpdateUser(User user,User user_new) {
        if(userRepository.findUserById(user.getId()) !=null){
            if(user.getUsername() != user_new.getUsername() || user.getName() != user_new.getName() || user.getEmail() != user_new.getEmail() ){
                if(user_new.getUsername() !=null){
                    user.setUsername(user_new.getUsername());
                }
                if(user_new.getEmail()!=null){
                    user.setEmail(user_new.getEmail());
                }
                if(user_new.getName()!=null){
                    user.setName(user_new.getName());
                }
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public void DeleteUser(String id) {
        userRepository.deleteById(new ObjectId(id));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Page<User> getUserByPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAll(pageable);
    }

}
