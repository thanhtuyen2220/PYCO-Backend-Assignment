package com.Pyco.Assginment.controller;
import com.Pyco.Assginment.model.ToDo;
import com.Pyco.Assginment.model.User;
import com.Pyco.Assginment.service.ToDoService;
import com.Pyco.Assginment.service.UserService;
import com.Pyco.Assignment.api.TodoApi;
import com.Pyco.Assignment.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ToDoController implements TodoApi {
    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    @Autowired private ModelMapper modelMapper;
    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> createTodo(@Valid CreateToDoRequest createToDoRequest) {
        User user = userService.findUserById(new ObjectId(createToDoRequest.getAuthorId()));
        ToDo toDo = modelMapper.map(createToDoRequest, ToDo.class);
        toDo.setAuthor(user);
        ToDo persistToDo = toDoService.createTask(toDo);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(persistToDo.getId().toString());
        result.setResponseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TaskListResponse> getListTask() {
        List<ToDo> taskList = toDoService.getAllToDo();
        return buildTaskListResponse(taskList);
    }

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> updateTask(String toDoId,@Valid UpdateTaskRequest updateTaskRequest) {
        ToDo toDo = modelMapper.map(updateTaskRequest,ToDo.class);
        toDoService.UpdateToDo(toDoId,toDo);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(toDoId);
        result.setResponseCode(200);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> deleteTodo(String toDoId) {
        toDoService.deleteToDo(toDoId);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(toDoId);
        result.setResponseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    private ResponseEntity<TaskListResponse> buildTaskListResponse(List<ToDo> toDoList) {
        TaskListResponse taskListResponse = new TaskListResponse();
        if(toDoList != null) {
            toDoList.forEach(item -> taskListResponse.addTodoItem(modelMapper.map(item, TaskResponseModel.class)));
        }
        return new ResponseEntity<>(taskListResponse, HttpStatus.OK);
    }
}
