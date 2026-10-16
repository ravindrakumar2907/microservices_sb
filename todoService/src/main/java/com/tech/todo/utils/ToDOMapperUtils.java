package com.tech.todo.utils;

import com.tech.todo.dto.TODORequest;
import com.tech.todo.dto.TODOResponse;
import com.tech.todo.entity.TodoEntity;

public class ToDOMapperUtils {
    public static TodoEntity convertToToDo(TODORequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setName(request.name());
        todoEntity.setDescription(request.description());
        todoEntity.setPriority(request.priority());
        todoEntity.setStatus(request.status());
        return todoEntity;
    }

    public static TODOResponse convertToTODOResponse(TodoEntity savedTodo) {
        return new TODOResponse(
                savedTodo.getId(),
                savedTodo.getName(),
                savedTodo.getDescription(),
                savedTodo.getPriority(),
                savedTodo.getStatus()
        );
    }
}
