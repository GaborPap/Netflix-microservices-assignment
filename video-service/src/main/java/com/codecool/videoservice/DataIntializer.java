package com.codecool.videoservice;

import com.codecool.videoservice.Model.Video;
import com.codecool.videoservice.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataIntializer implements CommandLineRunner {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public void run(String... args) throws Exception {

        Video video = Video.builder()
                .name("Microservices 1")
                .url("https://www.youtube.com/watch?v=tsC4bIP0Jl4")
                .build();

        Video video2 = Video.builder()
                .name("Microservices 2")
                .url("https://www.youtube.com/watch?v=APQgg7gDrfg")
                .build();
        videoRepository.save(video);
        videoRepository.save(video2);

       log.info(videoRepository.findAll().toString());
    }
}
