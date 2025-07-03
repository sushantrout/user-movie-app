package com.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDTO {
    private UUID id;
    private UUID userId;
    private UUID movieId;
    private int rating;
}
