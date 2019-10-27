package com.codecool.videoservice.service;


import com.codecool.recommendationservice.model.Recommendation;
import com.codecool.videoservice.dao.VideoDaoJpa;
import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.model.VideoWithRecommendations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class RecommendationsControllerService {

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private VideoDaoJpa videoDaoJpa;

    @Autowired
    private RecommendationsControllerService recommendationsControllerService;

    @Value("${recommendations.url}")
    private String baseUrl;

    public List<Recommendation> getAllRec() {
        ResponseEntity<List<Recommendation>> response = restTemplate.exchange(
                baseUrl + "/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Recommendation>>() {
                }
        );

        return response.getBody();
    }


    public Recommendation getONeById(long id) {
        Recommendation r = restTemplate.getForEntity(baseUrl + "/" + id, Recommendation.class).getBody();
        return r;
    }

    public List<Recommendation> getRecForVideo(long videoId) {
        ResponseEntity<List<Recommendation>> response = restTemplate.exchange(
                baseUrl + "/video/" + videoId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Recommendation>>() {
                }
        );
        return response.getBody();
    }

    public Recommendation addRecommendation(Recommendation recommendation) {

        HttpEntity<Recommendation> request = new HttpEntity<>(recommendation);

        return restTemplate.postForObject(baseUrl + "/addRec", request, Recommendation.class);
    }

    public void updateRecommendations(Recommendation recommendation, Long recId) {
        HttpEntity<Recommendation> request = new HttpEntity<>(recommendation);

        restTemplate.put(baseUrl + "/updateRec/"+recId, request, Recommendation.class);
    }

    public VideoWithRecommendations getVideoWithRecommendations(Long videoId) {
        Video video = videoDaoJpa.getVideoById(videoId);
        List<Recommendation> rec = recommendationsControllerService.getRecForVideo(videoId);

        VideoWithRecommendations videoWithRecommendations = new VideoWithRecommendations();
        videoWithRecommendations.setVideo(video);
        videoWithRecommendations.setRecommendations(rec);
        return videoWithRecommendations;
    }

    public void deleteRec(Long recId) {
        restTemplate.delete(baseUrl+"/deleteRec/"+recId);
    }
}
