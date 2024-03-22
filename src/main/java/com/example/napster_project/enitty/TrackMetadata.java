package com.example.napster_project.enitty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TrackMetadata {

    @JsonProperty("name")
    private String name;

    @JsonProperty("artistName")
    private String artistName;

    @JsonProperty("albumName")
    private String albumName;

    @JsonProperty("albumId")
    private String albumId;

    @JsonProperty("isExplicit")
    private boolean isExplicit;

    @JsonProperty("playbackSeconds")
    private int playbackSeconds;
}
