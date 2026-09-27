package com.astitva.controller;

import com.astitva.dto.*;
import com.astitva.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private MeetingService meetingService;

    @GetMapping
    public List<ProfileDTO> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public ProfileDTO getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileDTO createProfile(@RequestBody ProfileDTO dto) {
        return profileService.createProfile(dto);
    }

    @PutMapping("/{id}")
    public ProfileDTO updateProfile(@PathVariable Long id, @RequestBody ProfileDTO dto) {
        return profileService.updateProfile(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }

    // --- Endpoints to fetch data for a specific profile ---

    @GetMapping("/{profileId}/todos")
    public List<TodoDTO> getTodosByProfile(@PathVariable Long profileId) {
        return todoService.getTodosByProfileId(profileId);
    }

    @GetMapping("/{profileId}/notes")
    public List<NoteDTO> getNotesByProfile(@PathVariable Long profileId) {
        return noteService.getNotesByProfileId(profileId);
    }

    @GetMapping("/{profileId}/reminders")
    public List<ReminderDTO> getRemindersByProfile(@PathVariable Long profileId) {
        return reminderService.getRemindersByProfileId(profileId);
    }

    @GetMapping("/{profileId}/meetings")
    public List<MeetingDTO> getMeetingsByProfile(@PathVariable Long profileId) {
        return meetingService.getMeetingsByProfileId(profileId);
    }
}
