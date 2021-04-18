package com.Pyco.Assginment.service;

import com.Pyco.Assginment.model.ToDo;
import com.Pyco.Assginment.repository.ToDoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceImp implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;
    @Override
    public ToDo createTask(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @Override
    public List<ToDo> getAllToDo() {
        return toDoRepository.findAll();
    }

    @Override
    public ToDo findToDoById(ObjectId Id) {
        return toDoRepository.findToDoById(Id);
    }


    @Override
    public ToDo UpdateToDo(String toDoId,ToDo updateToDo) {
        //ToDo toDo = toDoRepository.findToDoById(new ObjectId(toDoId));
        ToDo toDo = toDoRepository.findToDoById(new ObjectId(toDoId));
            if(updateToDo.getContent()!= null){
                toDo.setContent(updateToDo.getContent());
            }
            if(updateToDo.getTittle()!=null){
                toDo.setTittle(updateToDo.getTittle());
            }
            if(updateToDo.getPriority()!=null){
                toDo.setPriority(updateToDo.getPriority());
            }
            if(updateToDo.isStatus()!= toDo.isStatus()){
                toDo.setStatus(updateToDo.isStatus());
            }
        return toDoRepository.save(toDo);
    }

    @Override
    public void deleteToDo(String id) {
        toDoRepository.deleteById(new ObjectId(id));
    }
}
