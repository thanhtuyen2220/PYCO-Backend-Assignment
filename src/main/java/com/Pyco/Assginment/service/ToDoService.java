package com.Pyco.Assginment.service;

import com.Pyco.Assginment.model.ToDo;
import org.bson.types.ObjectId;

import java.util.List;

public interface ToDoService {
    ToDo createTask(ToDo toDo);
    List<ToDo> getAllToDo();
    ToDo findToDoById(ObjectId Id);
    ToDo UpdateToDo(ToDo todo,String toDoId);
    void deleteToDo(String id);
}
