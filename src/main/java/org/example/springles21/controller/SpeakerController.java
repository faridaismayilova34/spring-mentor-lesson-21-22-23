package org.example.springles21.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.springles21.dto.request.SpeakerRequestDto;
import org.example.springles21.dto.response.SpeakerResponseDto;
import org.example.springles21.dto.response.TalkResponseDto;
import org.example.springles21.service.SpeakerService;
import org.example.springles21.service.impl.SpeakerServiceImpl;
import org.example.springles21.service.impl.TalkServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/speakers")
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerService speakerService;

    @PostMapping
    public ResponseEntity<SpeakerResponseDto> createSpeaker(@Valid @RequestBody SpeakerRequestDto requestDto) {
        return new ResponseEntity<>(speakerService.createSpeaker(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SpeakerResponseDto>> getAllSpeakers() {
        return ResponseEntity.ok(speakerService.getAllSpeakers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeakerResponseDto> getSpeakerById(@PathVariable
                                                             @Positive(message = "Id must be a positive number") Long id) {
        return ResponseEntity.ok(speakerService.getSpeakerById(id));
    }

    @GetMapping("/{id}/talks")
    public ResponseEntity<List<TalkResponseDto>> getTalksBySpeakerId(@PathVariable
                                                                     @Positive(message = "Id must be a positive number") Long id) {
        return ResponseEntity.ok(speakerService.getTalksBySpeakerId(id));
    }
}