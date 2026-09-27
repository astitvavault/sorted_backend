package com.astitva.dto;

import java.time.LocalDate;

public class ProfileDTO {

    private Long id;
    private String profileImage;
    private String fullName;
    private LocalDate birthDate;
    private String bio;

    public ProfileDTO() {}

    public ProfileDTO(Long id, String profileImage, String fullName, LocalDate birthDate, String bio) {
        this.id = id;
        this.profileImage = profileImage;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.bio = bio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
