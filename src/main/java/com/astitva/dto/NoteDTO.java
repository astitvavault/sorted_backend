package com.astitva.dto;

public class NoteDTO {

    private Long id;
    private String title;
    private String content;
    private Long profileId;

    public NoteDTO() {}

    public NoteDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteDTO(Long id, String title, String content, Long profileId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.profileId = profileId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }
}
