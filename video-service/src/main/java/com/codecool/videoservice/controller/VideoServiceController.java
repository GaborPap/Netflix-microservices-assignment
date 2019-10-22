package com.codecool.videoservice.controller;

import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/video")
public class VideoServiceController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/all")
    public List<Video> getAllVideos(){
        return videoService.getAllVideos();
    }


}
