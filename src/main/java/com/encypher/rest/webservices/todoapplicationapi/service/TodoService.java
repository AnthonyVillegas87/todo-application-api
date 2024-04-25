package com.encypher.rest.webservices.todoapplicationapi.service;

import com.encypher.rest.webservices.todoapplicationapi.user.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todoList = new ArrayList<>();
    private static int idCounter = 0;

    static {
        todoList.add(new Todo(++idCounter, "GeorgeTudor", "Learn Svelte", LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(++idCounter, "GeorgeTudor", "Learn Java", LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(++idCounter, "GeorgeTudor", "Learn DSA", LocalDate.now().plusYears(1), false));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todoList.stream().filter(predicate).toList();
    }

    public Todo addTodo(String username, String description, LocalDate targetDate, boolean completed) {
        Todo todo = new Todo(++idCounter, username, description, targetDate, completed);
        todoList.add(todo);
        return todo;
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todoList.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todoList.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void update(Todo todo) {
        deleteById(todo.getId());
        todoList.add(todo);
    }



}
