package com.tech.todo.dto;

public record TODOResponse(
        Long id,
        String name,
    String description,
    int priority,
    String status
) {
}
