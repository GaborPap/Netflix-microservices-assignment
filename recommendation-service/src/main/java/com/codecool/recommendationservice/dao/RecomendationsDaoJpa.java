package com.codecool.recommendationservice.dao;

import com.codecool.recommendationservice.model.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RecomendationsDaoJpa {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public List<Recommendation> getAllRecommendation(){
        return recommendationRepository.findAll();
    }

    public Recommendation findRecommendationById(Long id){
        Optional<Recommendation> rec =  recommendationRepository.findById(id);
        return rec.orElse(null);
    }

    public List<Recommendation> findAllRecommendationByVideoId(Long videoId){
        Optional<List<Recommendation>> recList =  recommendationRepository.findAllByVideoId(videoId);
        return recList.orElse(null);
    }

    public Recommendation addRecommendationToDB(Recommendation recommendation){
        if (recommendation!=null)
            recommendationRepository.save(recommendation);
        return recommendation;
    }

    public Recommendation updateRecommendation(Recommendation recommendation){
        if (recommendation==null)
            return null;
        if (recommendation.getId()==null)
            return null;

        Optional<Recommendation> recOptional = recommendationRepository.findById(recommendation.getId());
        if (recOptional.isPresent()) {

            Recommendation currentRec = recOptional.get();

            currentRec.setRating(recommendation.getRating());
            currentRec.setComment(recommendation.getComment());
            recommendationRepository.save(currentRec);
            return currentRec;
        }
        return null;
    }
}
