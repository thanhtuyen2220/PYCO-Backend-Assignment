package com.Pyco.Assginment.controller;

import com.Pyco.Assginment.model.User;
import com.Pyco.Assginment.service.UserService;
import com.Pyco.Assignment.api.UsersApi;
import com.Pyco.Assignment.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UserController implements UsersApi {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> createUser(@Valid CreateUserRequest createUserRequest) {
        User user = modelMapper.map(createUserRequest, User.class);
        User persistUser = userService.createUser(user);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(persistUser.getId().toString());
        result.setResponseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /*@Override
    public ResponseEntity<UserInfoResponse> getUserById(@Valid String Id) {
        /*User user = userService.findUserById(new ObjectId(Id));
        List<User> userList = new ArrayList<>();
        if(user != null) {
            userList.add(user);
        }
        return buildUserInfoResponse(userList);*/
       /* return null;
    }*/

    @Override
    public ResponseEntity<UserListResponse> getListUser() {
        List<User> userList = userService.getAllUsers();
        return buildUserListResponse(userList);
    }

    @Override
    public ResponseEntity<UserInfoResponse> getUserId(String id) {
       User user =  userService.getUserbyId(new ObjectId(id));
        return buildUserInfoResponse(user);
    }

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> updateUser(@Valid UpdateUserRequest updateUserRequest) {
        User old_info = userService.findUserById(new ObjectId(updateUserRequest.getUserId()));
        User temp = new User();
        temp.setEmail(updateUserRequest.getEmail());
        temp.setUsername(updateUserRequest.getUsername());
        temp.setName(updateUserRequest.getName());
        User new_info = userService.UpdateUser(old_info,temp);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(new_info.getId().toString());
        result.setResponseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> deleteUser(String id) {
        userService.DeleteUser(id);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(id);
        result.setResponseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    private ResponseEntity<UserListResponse> buildUserListResponse(List<User> userList) {
        UserListResponse userListResponse = new UserListResponse();
        if(userList != null) {
            userList.forEach(item -> userListResponse.addUsersItem(modelMapper.map(item, UserResponseModel.class)));
        }
        return new ResponseEntity<>(userListResponse, HttpStatus.OK);
    }
    private ResponseEntity<UserInfoResponse> buildUserInfoResponse(User user) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        if(user != null) {
            userInfoResponse.addUsersItem(modelMapper.map(user,UserInfoResponseModel.class));
        }
        return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
    }

}
