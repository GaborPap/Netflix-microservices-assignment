package com.codecool.videoservice.controller;

import com.codecool.recommendationservice.model.Recommendation;
import com.codecool.videoservice.model.VideoWithRecommendations;
import com.codecool.videoservice.service.RecommendationsControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendationsController {


    @Autowired
    private RecommendationsControllerService recommendationsControllerService;

   /* @GetMapping("/video/{videoId}")
    public List<Recommendation> getAllRecForVideo(@PathVariable("videoId") long videoId) {
        return recommendationsControllerService.getRecForVideo(videoId);
    }*/

    @GetMapping("/allRec")
    public List<Recommendation> getAllRec() {
        return recommendationsControllerService.getAllRec();
    }

    @GetMapping("/recforvideo/{videoId}")
    public List<Recommendation> getRecForVideo(@PathVariable("videoId") Long videoId){
        return recommendationsControllerService.getRecForVideo(videoId);
    }


    @PostMapping("/addRecommendation")

    public Recommendation addRecommendation(@RequestBody Recommendation recommendations) {
        System.out.println(recommendations);
        return recommendationsControllerService.addRecommendation(recommendations);
    }

    @PostMapping("/updateRecommendation")
    public Recommendation updateRecommendation(@RequestBody Recommendation recommendations) {
        return recommendationsControllerService.updateRecommendations(recommendations);
    }

    @GetMapping("/video/{id}")
    public VideoWithRecommendations getVideoWithRecommendations(@PathVariable("id") Long videoId) {
        return recommendationsControllerService.getVideoWithRecommendations(videoId);
    }

    @DeleteMapping("/deleteRec/{recId}")
    public ResponseEntity deleteRecommendation(@PathVariable("recId") Long recId){
        recommendationsControllerService.deleteRec(recId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
