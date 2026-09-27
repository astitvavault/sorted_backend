package com.astitva.service;

import com.astitva.dto.TodoDTO;
import com.astitva.entity.Profile;
import com.astitva.entity.Todo;
import com.astitva.exception.ResourceNotFoundException;
import com.astitva.repository.ProfileRepository;
import com.astitva.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TodoDTO> getTodosByProfileId(Long profileId) {
        return todoRepository.findByProfileId(profileId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
        return toDTO(todo);
    }

    public TodoDTO createTodo(TodoDTO dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.isCompleted());
        todo.setDueDate(dto.getDueDate());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + dto.getProfileId()));
            todo.setProfile(profile);
        }

        return toDTO(todoRepository.save(todo));
    }

    public TodoDTO updateTodo(Long id, TodoDTO dto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.isCompleted());
        todo.setDueDate(dto.getDueDate());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + dto.getProfileId()));
            todo.setProfile(profile);
        }

        return toDTO(todoRepository.save(todo));
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Todo not found with id " + id);
        }
        todoRepository.deleteById(id);
    }

    private TodoDTO toDTO(Todo todo) {
        Long profileId = (todo.getProfile() != null) ? todo.getProfile().getId() : null;
        return new TodoDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted(),
                todo.getDueDate(),
                profileId
        );
    }
}
