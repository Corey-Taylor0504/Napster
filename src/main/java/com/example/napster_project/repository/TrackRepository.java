package com.example.napster_project.repository;

import com.example.napster_project.enitty.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, String> {
}
