package com.tech.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDetail {
    private String movieId;
    private String title;
    List<RatingDTO> ratings;
}
