package com.astitva.dto;

import java.time.LocalDate;

public class TodoDTO {

    private Long id;
    private String title;
    private String description;
    private boolean isCompleted;
    private LocalDate dueDate;
    private Long profileId;

    public TodoDTO() {}

    public TodoDTO(Long id, String title, String description, boolean isCompleted, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }

    public TodoDTO(Long id, String title, String description, boolean isCompleted, LocalDate dueDate, Long profileId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
        this.profileId = profileId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }
}
