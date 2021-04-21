package com.Pyco.Assginment.service;

import com.Pyco.Assginment.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private UserService userService;

    private static final String TEST_USER_EMAIL = "test@local";
    private static final String TEST_ID = new String();

    @Before
    public void initUser(){
        mongoTemplate.remove(User.class).all();
        mongoTemplate.save(User.builder()
                .name("A" + RandomStringUtils.random(40))
                .username("A"+RandomStringUtils.random(40))
                .password("A"+RandomStringUtils.random(40))
                .Enabled(true)
                .username(RandomStringUtils.random(40))
                .email(TEST_USER_EMAIL)
                .build());
        mongoTemplate.save(User.builder()
                .name("B" + RandomStringUtils.random(40))
                .username("B"+RandomStringUtils.random(40))
                .password("B"+RandomStringUtils.random(40))
                .Enabled(true)
                .username(RandomStringUtils.random(40))
                .email("B" + RandomStringUtils.random(40) + "@local.email")
                .build());
        mongoTemplate.save(User.builder()
                .name("C" + RandomStringUtils.random(40))
                .username("C"+RandomStringUtils.random(40))
                .password("C"+RandomStringUtils.random(40))
                .Enabled(true)
                .username(RandomStringUtils.random(40))
                .email("C" + RandomStringUtils.random(40) + "@local.email")
                .build());
    }

    @Test
    public void testFindAllMustReturnEnoughQuantity(){
        List<User> userList = userService.getAllUsers();
        Assert.assertEquals(3, userList.size());
    }
    @Test
    public void testFindByExistIdMustReturnResult(){
        /*User user = userService.findUserById();*/
    }


}
