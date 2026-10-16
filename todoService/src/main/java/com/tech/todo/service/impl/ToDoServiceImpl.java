package com.tech.todo.service.impl;

import com.tech.todo.dto.TODORequest;
import com.tech.todo.dto.TODOResponse;
import com.tech.todo.entity.TodoEntity;
import com.tech.todo.repository.TODORepository;
import com.tech.todo.service.ToDoService;
import com.tech.todo.utils.ToDOMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final TODORepository toDoRepository;

    @Autowired
    public ToDoServiceImpl(TODORepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public TODOResponse createToDo(TODORequest request) {
        TodoEntity savedTodo = toDoRepository.save(ToDOMapperUtils.convertToToDo(request));
        return ToDOMapperUtils.convertToTODOResponse(savedTodo);
    }

    @Override
    public TODOResponse getToDo(String id) {
        TodoEntity savedTodo = toDoRepository.findById(Long.parseLong(id)).orElseThrow(() -> new RuntimeException("Todo not found"));
        return ToDOMapperUtils.convertToTODOResponse(savedTodo);
    }

    @Override
    public void updateToDo(String id, TODORequest request) {
        TodoEntity todoEntity = toDoRepository.findById(Long.parseLong(id)).orElseThrow(() -> new RuntimeException("Todo not found"));
        todoEntity.setName(request.name());
        todoEntity.setDescription(request.description());
        todoEntity.setPriority(request.priority());
        todoEntity.setStatus(request.status());
        toDoRepository.save(todoEntity);
    }

    @Override
    public void deleteToDo(String id) {
        toDoRepository.deleteById(Long.parseLong(id));
    }

}
