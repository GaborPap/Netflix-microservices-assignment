package com.codecool.recommendationservice;


import com.codecool.recommendationservice.model.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private RecommendationRepository recommendationRepository;

    public DataInitializer(RecommendationRepository recommendationsRepository) {
        this.recommendationRepository = recommendationsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Recommendation rec = Recommendation.builder()
                .comment("First comment")
                .rating(2)
                .videoId(1L)
                .build();
        Recommendation rec2 = Recommendation.builder()
                .comment("Secondcomment")
                .rating(2)
                .videoId(2L)
                .build();
        Recommendation rec3 = Recommendation.builder()
                .comment("Third comment")
                .rating(2)
                .videoId(3L)
                .build();
        Recommendation rec4= Recommendation.builder()
                .comment("Third comment video 3")
                .rating(4)
                .videoId(3L)
                .build();
        recommendationRepository.save(rec);
        recommendationRepository.save(rec2);
        recommendationRepository.save(rec3);
        recommendationRepository.save(rec4);
        log.info(recommendationRepository.findAll().toString());


    }
}
