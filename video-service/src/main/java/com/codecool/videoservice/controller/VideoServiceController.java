package com.codecool.videoservice.controller;

import com.codecool.videoservice.dao.VideoDaoJpa;
import com.codecool.videoservice.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoServiceController {

    @Autowired
    private VideoDaoJpa videoDaoJpa;


    @GetMapping("/allVideos")
    public List<Video> getAllVideos(){
        return videoDaoJpa.getAllVideos();
    }

    @GetMapping("/{videoId}")
    public Video getVideoById(@PathVariable("videoId") Long videoId){
        return videoDaoJpa.getVideoById(videoId);
    }

    @PostMapping("/addvideo")
    public Video addVideo(@RequestBody Video video){
        return videoDaoJpa.addVideoToDb(video);

    }


    @PostMapping("/updateVideo")
    public Video updateVideo(@RequestBody Video video){
            return videoDaoJpa.updateVideo(video);
    }





}
