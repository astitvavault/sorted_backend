package com.astitva.service;

import com.astitva.dto.MeetingDTO;
import com.astitva.entity.Meeting;
import com.astitva.entity.Profile;
import com.astitva.exception.ResourceNotFoundException;
import com.astitva.repository.MeetingRepository;
import com.astitva.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<MeetingDTO> getAllMeetings() {
        return meetingRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MeetingDTO> getMeetingsByProfileId(Long profileId) {
        return meetingRepository.findByProfileId(profileId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MeetingDTO getMeetingById(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found with id " + id));
        return toDTO(meeting);
    }

    public MeetingDTO createMeeting(MeetingDTO dto) {
        Meeting meeting = new Meeting();
        meeting.setTitle(dto.getTitle());
        meeting.setAgenda(dto.getAgenda());
        meeting.setMeetingTime(dto.getMeetingTime());
        meeting.setParticipants(dto.getParticipants());
        meeting.setLocation(dto.getLocation());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + dto.getProfileId()));
            meeting.setProfile(profile);
        }

        return toDTO(meetingRepository.save(meeting));
    }

    public MeetingDTO updateMeeting(Long id, MeetingDTO dto) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found with id " + id));

        meeting.setTitle(dto.getTitle());
        meeting.setAgenda(dto.getAgenda());
        meeting.setMeetingTime(dto.getMeetingTime());
        meeting.setParticipants(dto.getParticipants());
        meeting.setLocation(dto.getLocation());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + dto.getProfileId()));
            meeting.setProfile(profile);
        }

        return toDTO(meetingRepository.save(meeting));
    }

    public void deleteMeeting(Long id) {
        if (!meetingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Meeting not found with id " + id);
        }
        meetingRepository.deleteById(id);
    }

    private MeetingDTO toDTO(Meeting meeting) {
        Long profileId = (meeting.getProfile() != null) ? meeting.getProfile().getId() : null;
        return new MeetingDTO(
                meeting.getId(),
                meeting.getTitle(),
                meeting.getAgenda(),
                meeting.getMeetingTime(),
                meeting.getParticipants(),
                meeting.getLocation(),
                profileId
        );
    }
}
