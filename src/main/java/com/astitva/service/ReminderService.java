package com.astitva.service;

import com.astitva.dto.ReminderDTO;
import com.astitva.entity.Profile;
import com.astitva.entity.Reminder;
import com.astitva.exception.ResourceNotFoundException;
import com.astitva.repository.ProfileRepository;
import com.astitva.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<ReminderDTO> getAllReminders() {
        return reminderRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ReminderDTO> getRemindersByProfileId(Long profileId) {
        return reminderRepository.findByProfileId(profileId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReminderDTO getReminderById(Long id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id " + id));
        return toDTO(reminder);
    }

    public ReminderDTO createReminder(ReminderDTO dto) {
        Reminder reminder = new Reminder();
        reminder.setTitle(dto.getTitle());
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setTriggered(dto.isTriggered());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + dto.getProfileId()));
            reminder.setProfile(profile);
        }

        return toDTO(reminderRepository.save(reminder));
    }

    public ReminderDTO updateReminder(Long id, ReminderDTO dto) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id " + id));

        reminder.setTitle(dto.getTitle());
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setTriggered(dto.isTriggered());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + dto.getProfileId()));
            reminder.setProfile(profile);
        }

        return toDTO(reminderRepository.save(reminder));
    }

    public void deleteReminder(Long id) {
        if (!reminderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reminder not found with id " + id);
        }
        reminderRepository.deleteById(id);
    }

    private ReminderDTO toDTO(Reminder reminder) {
        Long profileId = (reminder.getProfile() != null) ? reminder.getProfile().getId() : null;
        return new ReminderDTO(
                reminder.getId(),
                reminder.getTitle(),
                reminder.getReminderTime(),
                reminder.isTriggered(),
                profileId
        );
    }
}
