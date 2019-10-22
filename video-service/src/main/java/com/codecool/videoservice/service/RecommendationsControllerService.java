package com.codecool.videoservice.service;


import com.codecool.recommendationservice.model.Recommendation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class RecommendationsControllerService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recommendations.url}")
    private String baseUrl;

    public List<Recommendation> getAllRec(){
        //List<Video> video = (List<Video>) restTemplate.getForEntity(baseUrl + "/all", Video.class).getBody();
        //Recommendations r = restTemplate.getForEntity(baseUrl + "/all", Recommendations.class).getBody();
        //System.out.println(restTemplate.getForEntity(baseUrl + "/all", Recommendations.class).getBody());

        ResponseEntity<List<Recommendation>> response = restTemplate.exchange(
                baseUrl + "/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Recommendation>>() {}

        );
        List<Recommendation> recommendations = response.getBody();

        return recommendations;
    }




    public Recommendation getONeById(long id){
        Recommendation r = restTemplate.getForEntity(baseUrl + "/"+id, Recommendation.class).getBody();
        return r;
    }

    public List<Recommendation> getRecForVideo(long videoId){
        ResponseEntity<List<Recommendation>> response = restTemplate.exchange(
                baseUrl + "/video/"+ videoId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Recommendation>>() {}

        );
        List<Recommendation> recommendations = response.getBody();

        return recommendations;
    }

    public Recommendation addRecommendation(Recommendation recommendation){

        HttpEntity<Recommendation> request = new HttpEntity<>(recommendation);
        Recommendation rec = restTemplate.postForObject(baseUrl+"/addRec", request, Recommendation.class);

        return rec;
    }

    public Recommendation updateRecommendations(Recommendation recommendation){
        HttpEntity<Recommendation> request = new HttpEntity<>(recommendation);
        Recommendation rec = restTemplate.postForObject(baseUrl+"/updateRec", request, Recommendation.class);

        return rec;
    }
}
