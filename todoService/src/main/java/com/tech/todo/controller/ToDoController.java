package com.tech.todo.controller;

import com.tech.todo.dto.TODOResponse;
import com.tech.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public ResponseEntity<com.tech.todo.dto.TODOResponse> getToDo(String id) {
        return ResponseEntity.ok(toDoService.getToDo(id));
    }

    //write remaining methods
    @PostMapping
    public ResponseEntity<com.tech.todo.dto.TODOResponse> createToDo(@RequestBody com.tech.todo.dto.TODORequest request) {
        return ResponseEntity.ok(toDoService.createToDo(request));
    }

    @PutMapping
    public ResponseEntity<com.tech.todo.dto.TODOResponse> updateToDo(@PathVariable String id, @RequestBody com.tech.todo.dto.TODORequest request) {
        TODOResponse response = new TODOResponse(Long.parseLong(id), request.name(), request.description(), request.priority(), "Completed");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteToDo(@PathVariable String id) {
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }

}