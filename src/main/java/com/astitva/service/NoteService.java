package com.astitva.service;

import com.astitva.dto.NoteDTO;
import com.astitva.entity.Note;
import com.astitva.entity.Profile;
import com.astitva.exception.ResourceNotFoundException;
import com.astitva.repository.NoteRepository;
import com.astitva.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<NoteDTO> getNotesByProfileId(Long profileId) {
        return noteRepository.findByProfileId(profileId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + id));
        return toDTO(note);
    }

    public NoteDTO createNote(NoteDTO dto) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + dto.getProfileId()));
            note.setProfile(profile);
        }

        return toDTO(noteRepository.save(note));
    }

    public NoteDTO updateNote(Long id, NoteDTO dto) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + id));

        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setUpdatedAt(LocalDateTime.now());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + dto.getProfileId()));
            note.setProfile(profile);
        }

        return toDTO(noteRepository.save(note));
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Note not found with id " + id);
        }
        noteRepository.deleteById(id);
    }

    private NoteDTO toDTO(Note note) {
        Long profileId = (note.getProfile() != null) ? note.getProfile().getId() : null;
        return new NoteDTO(note.getId(), note.getTitle(), note.getContent(), profileId);
    }
}
