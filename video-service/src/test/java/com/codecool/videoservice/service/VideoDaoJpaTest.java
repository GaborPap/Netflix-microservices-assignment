package com.codecool.videoservice.service;

import com.codecool.videoservice.dao.VideoDaoJpa;
import com.codecool.videoservice.model.Video;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class VideoDaoJpaTest {

    @Autowired
    private VideoDaoJpa videoDaoJpa;


    @Test
    @Transactional
    public void addVideoToDatabase(){
        Video video = Video.builder()
                .name("Test video")
                .url("url")
                .build();

        videoDaoJpa.addVideoToDb(video);
        assertEquals(1, videoDaoJpa.getAllVideos().size());
    }

}