package com.encypher.rest.webservices.todoapplicationapi.controller;

import com.encypher.rest.webservices.todoapplicationapi.service.TodoService;
import com.encypher.rest.webservices.todoapplicationapi.user.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoResource {
    private TodoService todoService;

    public ToDoResource(TodoService todoService) {
       this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodoList(@PathVariable String username) {
        return todoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
