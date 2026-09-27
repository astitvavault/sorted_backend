package com.astitva.controller;

import com.astitva.dto.ReminderDTO;
import com.astitva.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @GetMapping
    public List<ReminderDTO> getAllReminders() {
        return reminderService.getAllReminders();
    }

    @GetMapping("/{id}")
    public ReminderDTO getReminderById(@PathVariable Long id) {
        return reminderService.getReminderById(id);
    }

    @GetMapping("/profile/{profileId}")
    public List<ReminderDTO> getRemindersByProfileId(@PathVariable Long profileId) {
        return reminderService.getRemindersByProfileId(profileId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderDTO createReminder(@RequestBody ReminderDTO dto) {
        return reminderService.createReminder(dto);
    }

    @PutMapping("/{id}")
    public ReminderDTO updateReminder(@PathVariable Long id, @RequestBody ReminderDTO dto) {
        return reminderService.updateReminder(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
    }
}
