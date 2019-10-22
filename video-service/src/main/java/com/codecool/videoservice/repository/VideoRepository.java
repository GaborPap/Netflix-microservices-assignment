package com.codecool.videoservice.repository;

import com.codecool.videoservice.Model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {


}