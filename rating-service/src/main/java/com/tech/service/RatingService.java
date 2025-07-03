package com.tech.service;

import com.tech.mapper.RatingMapper;
import com.tech.model.dto.RatingDTO;
import com.tech.model.entity.Rating;
import com.tech.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public Mono<RatingDTO> create(RatingDTO dto) {
        return ratingRepository.save(ratingMapper.toEntity(dto))
                .map(ratingMapper::toDto);
    }

    public Mono<RatingDTO> getById(UUID id) {
        return ratingRepository.findById(id)
                .map(ratingMapper::toDto);
    }

    public Flux<RatingDTO> getAll() {
        return ratingRepository.findAll()
                .map(ratingMapper::toDto);
    }

    public Mono<RatingDTO> update(UUID id, RatingDTO dto) {
        return ratingRepository.findById(id)
                .flatMap(existing -> {
                    Rating updated = ratingMapper.toEntity(dto);
                    return ratingRepository.save(updated);
                })
                .map(ratingMapper::toDto);
    }

    public Mono<Void> delete(UUID id) {
        return ratingRepository.deleteById(id);
    }

    public Flux<Rating> getRatingsByMovieId(UUID id) {
        return ratingRepository.findByMovieId(id);
    }
}
