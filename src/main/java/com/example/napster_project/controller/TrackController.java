package com.example.napster_project.controller;

import com.example.napster_project.enitty.Track;
import com.example.napster_project.enitty.TrackMetadata;
import com.example.napster_project.service.MusicService;
import com.example.napster_project.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackController {

    private final MusicService musicService;
    private final TrackService trackService;

    public TrackController(MusicService musicService, TrackService trackService) {
        this.musicService = musicService;
        this.trackService = trackService;
    }

    @PostMapping("/codechallenge/createTrack")
    public ResponseEntity<?> createTrack(@RequestParam("isrc") String isrc) {
        try {
            trackService.saveTrack(isrc);
            return new ResponseEntity<>("Track created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create track: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/codechallenge/getTrackMetadata")
    public ResponseEntity<?> getTrackMetadata(@RequestParam("isrc") String isrc) {
        try {
            Track track = trackService.findByIsrc(isrc);
            if (track == null) {
                return new ResponseEntity<>("Track not found for ISRC: " + isrc, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(track, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch track metadata: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/codechallenge/getCover")
    public ResponseEntity<?> getCover(@RequestParam("isrc") String isrc) {
        try {
            byte[] coverImage = trackService.findCoverByIsrc(isrc);
            if (coverImage == null) {
                return new ResponseEntity<>("Cover image not found for ISRC: " + isrc, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(coverImage);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch cover image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
