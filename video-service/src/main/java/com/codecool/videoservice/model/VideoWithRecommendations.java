package com.codecool.videoservice.model;

import com.codecool.recommendationservice.model.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoWithRecommendations {

    Video video;
    List<Recommendation> recommendations;


}
