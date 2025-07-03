package com.tech.repository;

import com.tech.model.entity.Rating;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface RatingRepository extends ReactiveCrudRepository<Rating, UUID> {
    @Query("SELECT id, user_id, movie_id, rating FROM ratings WHERE movie_id = :movieId")
    Flux<Rating> findByMovieId(@Param("movieId") UUID movieId);
}
