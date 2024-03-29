package com.codecool.videoservice;

import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("production")
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

        Video video3 = Video.builder()
                .name("React with net ninja 3")
                .url("https://www.youtube.com/watch?v=1I-vfkOVAXU")
                .build();

        Video video4 = Video.builder()
                .name("React with net ninja 3")
                .url("https://www.youtube.com/watch?v=1I-vfkOVAXU")
                .build();

        Video video5 = Video.builder()
                .name("React with net ninja 3")
                .url("https://www.youtube.com/watch?v=1I-vfkOVAXU")
                .build();

        Video video6 = Video.builder()
                .name("React with net ninja 3")
                .url("https://www.youtube.com/watch?v=1I-vfkOVAXU")
                .build();
        videoRepository.save(video);
        videoRepository.save(video2);
        videoRepository.save(video3);
        videoRepository.save(video4);
        videoRepository.save(video5);
        videoRepository.save(video6);

       log.info(videoRepository.findAll().toString());
    }
}
