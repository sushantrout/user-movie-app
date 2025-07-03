package com.tech.mapper;

import com.tech.model.dto.RatingDTO;
import com.tech.model.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    public Rating toEntity(RatingDTO dto) {
        return Rating.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .movieId(dto.getMovieId())
                .rating(dto.getRating())
                .build();
    }

    public RatingDTO toDto(Rating entity) {
        return RatingDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .movieId(entity.getMovieId())
                .rating(entity.getRating())
                .build();
    }
}
