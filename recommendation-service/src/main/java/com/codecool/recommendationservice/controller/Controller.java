package com.codecool.recommendationservice.controller;

import com.codecool.recommendationservice.dao.RecomendationsDaoJpa;
import com.codecool.recommendationservice.model.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recommendations")
public class Controller {

    @Autowired
    private RecomendationsDaoJpa recomendationsDaoJpa;

    @GetMapping("/all")
    public List<Recommendation> getAllRecommendation(){
        return recomendationsDaoJpa.getAllRecommendation();
    }

    @GetMapping("/{id}")
    public Recommendation getOneRec(@PathVariable("id") long id){
        return recomendationsDaoJpa.findRecommendationById(id);
    }

    @GetMapping("/video/{videoId}")
    public List<Recommendation> getRecommendationsForVideo(@PathVariable("videoId") long videoId){
        return recomendationsDaoJpa.findAllRecommendationByVideoId(videoId);
    }


    @PostMapping("/addRec")
    public Recommendation addRecommendationToVideo(@RequestBody Recommendation recommendation){
        recomendationsDaoJpa.addRecommendationToDB(recommendation);
        return recommendation;
    }

    @PostMapping("/updateRec")
    public Recommendation updateRecommendation(@RequestBody Recommendation recommendations) {
        return recomendationsDaoJpa.updateRecommendation(recommendations);
    }
}