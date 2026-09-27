package com.astitva.dto;

import java.time.LocalDateTime;

public class ReminderDTO {

    private Long id;
    private String title;
    private LocalDateTime reminderTime;
    private boolean isTriggered;
    private Long profileId;

    public ReminderDTO() {}

    public ReminderDTO(Long id, String title, LocalDateTime reminderTime, boolean isTriggered) {
        this.id = id;
        this.title = title;
        this.reminderTime = reminderTime;
        this.isTriggered = isTriggered;
    }

    public ReminderDTO(Long id, String title, LocalDateTime reminderTime, boolean isTriggered, Long profileId) {
        this.id = id;
        this.title = title;
        this.reminderTime = reminderTime;
        this.isTriggered = isTriggered;
        this.profileId = profileId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getReminderTime() { return reminderTime; }
    public void setReminderTime(LocalDateTime reminderTime) { this.reminderTime = reminderTime; }

    public boolean isTriggered() { return isTriggered; }
    public void setTriggered(boolean triggered) { isTriggered = triggered; }

    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }
}
