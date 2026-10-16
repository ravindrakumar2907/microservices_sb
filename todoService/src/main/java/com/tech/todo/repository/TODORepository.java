package com.tech.todo.repository;

import com.tech.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TODORepository extends JpaRepository<TodoEntity, Long> {
}
