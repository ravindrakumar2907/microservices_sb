package com.tech.todo.dto;

public record TODORequest(
    String name,
    String description,
    int priority,
    String status) {
}
