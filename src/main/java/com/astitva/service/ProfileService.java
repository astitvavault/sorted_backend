package com.astitva.service;

import com.astitva.dto.ProfileDTO;
import com.astitva.entity.Profile;
import com.astitva.exception.ResourceNotFoundException;
import com.astitva.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProfileDTO getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
        return toDTO(profile);
    }

    public ProfileDTO createProfile(ProfileDTO dto) {
        Profile profile = new Profile();
        profile.setProfileImage(dto.getProfileImage());
        profile.setFullName(dto.getFullName());
        profile.setBirthDate(dto.getBirthDate());
        profile.setBio(dto.getBio());

        return toDTO(profileRepository.save(profile));
    }

    public ProfileDTO updateProfile(Long id, ProfileDTO dto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));

        profile.setProfileImage(dto.getProfileImage());
        profile.setFullName(dto.getFullName());
        profile.setBirthDate(dto.getBirthDate());
        profile.setBio(dto.getBio());

        return toDTO(profileRepository.save(profile));
    }

    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new ResourceNotFoundException("Profile not found with id " + id);
        }
        profileRepository.deleteById(id);
    }

    private ProfileDTO toDTO(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getProfileImage(),
                profile.getFullName(),
                profile.getBirthDate(),
                profile.getBio()
        );
    }
}
