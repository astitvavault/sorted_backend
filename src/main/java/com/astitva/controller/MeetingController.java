package com.astitva.controller;

import com.astitva.dto.MeetingDTO;
import com.astitva.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping
    public List<MeetingDTO> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @GetMapping("/{id}")
    public MeetingDTO getMeetingById(@PathVariable Long id) {
        return meetingService.getMeetingById(id);
    }

    @GetMapping("/profile/{profileId}")
    public List<MeetingDTO> getMeetingsByProfileId(@PathVariable Long profileId) {
        return meetingService.getMeetingsByProfileId(profileId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MeetingDTO createMeeting(@RequestBody MeetingDTO dto) {
        return meetingService.createMeeting(dto);
    }

    @PutMapping("/{id}")
    public MeetingDTO updateMeeting(@PathVariable Long id, @RequestBody MeetingDTO dto) {
        return meetingService.updateMeeting(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
    }
}
