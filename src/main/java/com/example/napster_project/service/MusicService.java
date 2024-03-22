package com.example.napster_project.service;

import com.example.napster_project.apiresponse.NapsterApiResponse;
import com.example.napster_project.enitty.TrackMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MusicService {
    private final RestTemplate restTemplate;

    @Value("${napster.api.key}")
    private String napsterApiKey;

    @Value("${napster.api.baseuri}")
    private String napsterApiBaseUrl;

    @Value("${napster.image.baseurl}")
    private String napsterImageBaseUrl;

    public MusicService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TrackMetadata fetchTrackMetadata(String isrc) {
        String url = UriComponentsBuilder.fromHttpUrl(napsterApiBaseUrl + "/v2.2/tracks/isrc/" + isrc)
                .queryParam("apikey", napsterApiKey)
                .toUriString();

        NapsterApiResponse response = restTemplate.getForObject(url, NapsterApiResponse.class);

        if (response != null && response.getTracks() != null && !response.getTracks().isEmpty()) {
            return response.getTracks().get(0);
        } else {
            return null;
        }
    }

    public byte[] fetchAlbumCover(String albumId) {
        String url = String.format("%s/%s/images/500x500.jpg", napsterImageBaseUrl, albumId);
        return restTemplate.getForObject(url, byte[].class);
    }

}
