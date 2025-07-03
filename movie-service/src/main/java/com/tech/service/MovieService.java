package com.tech.service;

import com.tech.model.entity.Movie;
import com.tech.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Mono<Movie> createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Mono<Movie> getMovieById(UUID id) {
        return movieRepository.findById(id);
    }

    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Mono<Movie> updateMovie(UUID id, Movie movie) {
        return movieRepository.findById(id)
                .flatMap(existing -> {
                    movie.setId(existing.getId());
                    return movieRepository.save(movie);
                });
    }

    public Mono<Void> deleteMovie(UUID id) {
        return movieRepository.deleteById(id);
    }
}
