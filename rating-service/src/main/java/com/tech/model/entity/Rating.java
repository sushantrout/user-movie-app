package com.tech.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("ratings")
@Builder
public class Rating {
    @Id
    private UUID id;

    private UUID userId;
    private UUID movieId;
    private int rating;
}
