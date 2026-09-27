package com.astitva.controller;

import com.astitva.dto.TodoDTO;
import com.astitva.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoDTO getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @GetMapping("/profile/{profileId}")
    public List<TodoDTO> getTodosByProfileId(@PathVariable Long profileId) {
        return todoService.getTodosByProfileId(profileId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDTO createTodo(@RequestBody TodoDTO dto) {
        return todoService.createTodo(dto);
    }

    @PutMapping("/{id}")
    public TodoDTO updateTodo(@PathVariable Long id, @RequestBody TodoDTO dto) {
        return todoService.updateTodo(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
