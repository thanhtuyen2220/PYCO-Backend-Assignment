package com.Pyco.Assginment.repository;
import com.Pyco.Assginment.model.ToDo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, ObjectId> {
    ToDo findToDoById(ObjectId Id);
    ToDo getToDoById(ObjectId Id);
}
