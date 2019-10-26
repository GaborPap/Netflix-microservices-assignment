package com.codecool.recommendationservice.repository;

import com.codecool.recommendationservice.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    Optional<Recommendation> findById(long id);
    Optional<List<Recommendation>> findAllByVideoId(long videoId);

    @Override
    void deleteById(Long recId);
}
