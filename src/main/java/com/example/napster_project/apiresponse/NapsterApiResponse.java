package com.example.napster_project.apiresponse;

import com.example.napster_project.enitty.TrackMetadata;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NapsterApiResponse {

    @JsonProperty("tracks")
    private List<TrackMetadata> tracks;
}
