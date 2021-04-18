package com.Pyco.Assginment.repository;

import com.Pyco.Assginment.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);
    User findUserById (ObjectId Id);
    User getById(ObjectId id);
}
