package com.tech.todo.service;

import com.tech.todo.dto.TODORequest;
import com.tech.todo.dto.TODOResponse;


public interface ToDoService {

    TODOResponse createToDo(TODORequest request);

    TODOResponse getToDo(String id);

    void updateToDo(String id, TODORequest request);

    void deleteToDo(String id);
}
