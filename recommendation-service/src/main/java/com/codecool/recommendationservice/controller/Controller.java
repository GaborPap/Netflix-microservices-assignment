package com.codecool.recommendationservice.controller;

import com.codecool.recommendationservice.model.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recommendations")
public class Controller {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @GetMapping("/all")
    public List<Recommendation> getAllRecommendation(){
        return recommendationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Recommendation getOneRec(@PathVariable("id") long id){
        Optional<Recommendation> r = recommendationRepository.findById(id);
        return r.orElse(null);
    }

    @GetMapping("/video/{videoId}")
    public List<Recommendation> getRecommendationsForVideo(@PathVariable("videoId") long videoId){
        return recommendationRepository.findAllByVideoId(videoId).orElse(null);
    }


    @PostMapping("/addRec")
    public Recommendation addRecommendationToVideo(@RequestBody Recommendation recommendation){
        recommendationRepository.save(recommendation);
        return recommendation;
    }

    @PostMapping("/updateRec")
    public Recommendation updateRecommendation(@RequestBody Recommendation recommendations) {
        Optional<Recommendation> rec = recommendationRepository.findById(recommendations.getId());
        Recommendation rec2 = rec.get();

        rec2.setRating(recommendations.getRating());
        rec2.setComment(recommendations.getComment());
        //rec.ifPresent(value -> value = recommendations);
        recommendationRepository.save(rec2);
        return rec.get();
    }
}