package com.astitva.dto;

import java.time.LocalDateTime;

public class MeetingDTO {

    private Long id;
    private String title;
    private String agenda;
    private LocalDateTime meetingTime;
    private String participants;
    private String location;
    private Long profileId;

    public MeetingDTO() {}

    public MeetingDTO(Long id, String title, String agenda, LocalDateTime meetingTime, String participants, String location) {
        this.id = id;
        this.title = title;
        this.agenda = agenda;
        this.meetingTime = meetingTime;
        this.participants = participants;
        this.location = location;
    }

    public MeetingDTO(Long id, String title, String agenda, LocalDateTime meetingTime, String participants, String location, Long profileId) {
        this.id = id;
        this.title = title;
        this.agenda = agenda;
        this.meetingTime = meetingTime;
        this.participants = participants;
        this.location = location;
        this.profileId = profileId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAgenda() { return agenda; }
    public void setAgenda(String agenda) { this.agenda = agenda; }

    public LocalDateTime getMeetingTime() { return meetingTime; }
    public void setMeetingTime(LocalDateTime meetingTime) { this.meetingTime = meetingTime; }

    public String getParticipants() { return participants; }
    public void setParticipants(String participants) { this.participants = participants; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }
}
