package com.tech.repository;

import com.tech.model.entity.Movie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface MovieRepository extends ReactiveCrudRepository<Movie, UUID> {
}
