package com.encypher.rest.webservices.todoapplicationapi.controller;

import com.encypher.rest.webservices.todoapplicationapi.service.TodoService;
import com.encypher.rest.webservices.todoapplicationapi.user.Todo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@RestController
public class ToDoResource {
    private final HandlerMapping defaultServletHandlerMapping;
    private TodoService todoService;

    public ToDoResource(TodoService todoService, @Qualifier("defaultServletHandlerMapping") HandlerMapping defaultServletHandlerMapping) {
       this.todoService = todoService;
        this.defaultServletHandlerMapping = defaultServletHandlerMapping;
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

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
       todoService.update(todo);
       return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username,  @RequestBody Todo todo) {
        Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone() );
        return createdTodo;
    }
}
