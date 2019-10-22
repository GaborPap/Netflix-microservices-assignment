package com.codecool.videoservice.controller;

import com.codecool.recommendationservice.model.Recommendation;
import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.model.VideoWithRecommendations;
import com.codecool.videoservice.repository.VideoRepository;
import com.codecool.videoservice.service.RecommendationsControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VideoServiceController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private RecommendationsControllerService recommendationsControllerService;

    @GetMapping("/allVideos")
    public List<Video> getAllVideos(){
        return videoRepository.findAll();
    }

    @GetMapping("/allRec")
    public List<Recommendation> getAllRec(){
        return recommendationsControllerService.getAllRec();
    }

  /*  @GetMapping("/video/{id}")
    public Recommendations getREcForVid(@PathVariable("id") Long id){
        return  recommendationsController.getONeById(id);
    }*/

    @GetMapping("/video/{id}")
    public VideoWithRecommendations getREcForVid(@PathVariable("id") Long id){
        Optional<Video> video = videoRepository.findById(id);
        List<Recommendation> rec = recommendationsControllerService.getRecForVideo(id);

        VideoWithRecommendations v = new VideoWithRecommendations();
        v.setVideo(video.get());
        v.setRecommendations(rec);
        return  v;
    }

    @GetMapping("/recforvideo/{videoId}")
    public List<Recommendation> getAllRecForVideo(@PathVariable("videoId") long videoId) {
        return recommendationsControllerService.getRecForVideo(videoId);
    }

    @PostMapping("/addvideo")
    public Video addVideo(@RequestBody Video video){
        return videoRepository.save(video);
    }

    @PostMapping("/updateVideo")
    public Video updateVideo(@RequestBody Video video){
        Optional<Video> vid1 = videoRepository.findById(video.getId());
        Video vid2 = vid1.get();
        vid2.setName(video.getName());
        vid2.setUrl(video.getUrl());
        videoRepository.save(vid2);
        return vid2;
    }



    @PostMapping("/addRecommendation")
    public Recommendation addREcommendation(@RequestBody Recommendation recommendations){
        return recommendationsControllerService.addRecommendation(recommendations);
    }

    @PostMapping("/updateRecomendation")
    public Recommendation updateRecommendation(@RequestBody Recommendation recommendations){
        return recommendationsControllerService.updateRecommendations(recommendations);
    }


}
