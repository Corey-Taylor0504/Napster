package com.example.napster_project.service;

import com.example.napster_project.enitty.Track;
import com.example.napster_project.enitty.TrackMetadata;
import com.example.napster_project.repository.TrackRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final MusicService musicService;

    public TrackService(TrackRepository trackRepository, MusicService musicService) {
        this.trackRepository = trackRepository;
        this.musicService = musicService;
    }

    @Transactional
    public void saveTrack(String isrc) {
        TrackMetadata metadata = musicService.fetchTrackMetadata(isrc);
        if (metadata != null) {
            Track track = new Track(); // Assuming Track is your entity class
            track.setIsrc(isrc);
            track.setName(metadata.getName());
            track.setArtistName(metadata.getArtistName());
            track.setAlbumName(metadata.getAlbumName());
            track.setAlbumId(metadata.getAlbumId());
            track.setExplicit(metadata.isExplicit());
            track.setPlaybackSeconds(metadata.getPlaybackSeconds());

            byte[] coverImage = musicService.fetchAlbumCover(metadata.getAlbumId());
            if (coverImage != null) {
                track.setCoverImage(coverImage);
            }

            trackRepository.save(track);
        }
    }

    public Track findByIsrc(String isrc) {
        Optional<Track> track = trackRepository.findById(isrc);
        return track.orElse(null);
    }

    public byte[] findCoverByIsrc(String isrc) {
        Track track = trackRepository.findById(isrc).orElse(null);
        if(track != null) {
            return track.getCoverImage();
        }
        else {
            return null;
        }
    }
}
