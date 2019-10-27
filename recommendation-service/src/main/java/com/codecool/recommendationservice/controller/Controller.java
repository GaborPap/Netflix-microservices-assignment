package com.codecool.recommendationservice.controller;

import com.codecool.recommendationservice.dao.RecomendationsDaoJpa;
import com.codecool.recommendationservice.model.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recommendations")
@CrossOrigin
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

    @PutMapping("/updateRec/{recId}")
    public ResponseEntity updateRecommendation(@RequestBody Recommendation recommendations, @PathVariable("recId") Long recId) {
        recomendationsDaoJpa.updateRecommendation(recId, recommendations);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteRec/{recId}")
    public ResponseEntity deleteRecommendation(@PathVariable("recId") Long recId){
        recomendationsDaoJpa.deleteRecommendation(recId);
        return new ResponseEntity(HttpStatus.OK);
    }


}