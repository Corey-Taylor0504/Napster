package com.example.napster_project.enitty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Track {
    @Id
    private String isrc;

    private String name;

    private String artistName;

    private String albumName;

    private String albumId;

    private boolean isExplicit;

    private int playbackSeconds;

    @Lob
    private byte[] coverImage;
}
