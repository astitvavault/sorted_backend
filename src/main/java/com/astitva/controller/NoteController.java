package com.astitva.controller;

import com.astitva.dto.NoteDTO;
import com.astitva.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<NoteDTO> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public NoteDTO getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @GetMapping("/profile/{profileId}")
    public List<NoteDTO> getNotesByProfileId(@PathVariable Long profileId) {
        return noteService.getNotesByProfileId(profileId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO createNote(@RequestBody NoteDTO dto) {
        return noteService.createNote(dto);
    }

    @PutMapping("/{id}")
    public NoteDTO updateNote(@PathVariable Long id, @RequestBody NoteDTO dto) {
        return noteService.updateNote(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}
