package com.tech.handler;

import com.tech.model.dto.MovieDetail;
import com.tech.model.dto.RatingDTO;
import com.tech.model.entity.Movie;
import com.tech.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MovieHandler {

    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder; // Define in config or main class

    public Mono<ServerResponse> createMovie(ServerRequest request) {
        return request.bodyToMono(Movie.class)
                .flatMap(movieService::createMovie)
                .flatMap(movie -> ServerResponse.ok().bodyValue(movie));
    }

    public Mono<ServerResponse> getMovieById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return movieService.getMovieById(id)
                .flatMap(movie -> ServerResponse.ok().bodyValue(movie))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getDetailsById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));

        WebClient webClient = webClientBuilder
                //.baseUrl("http://rating-service") // Use service name registered in Eureka
                .build();

        return movieService.getMovieById(id)
                .flatMap(movie -> webClient.get()
                        .uri("http://rating-service/ratings/movie/{id}", id)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<RatingDTO>>() {})
                        .flatMap(ratings -> {
                            MovieDetail detail = new MovieDetail();
                            detail.setMovieId(movie.getId().toString());
                            detail.setTitle(movie.getTitle());
                            detail.setRatings(ratings);
                            return ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(detail);
                        })
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> getAllMovies(ServerRequest request) {
        return ServerResponse.ok().body(movieService.getAllMovies(), Movie.class);
    }

    public Mono<ServerResponse> updateMovie(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return request.bodyToMono(Movie.class)
                .flatMap(movie -> movieService.updateMovie(id, movie))
                .flatMap(updated -> ServerResponse.ok().bodyValue(updated))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteMovie(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return movieService.deleteMovie(id)
                .then(ServerResponse.noContent().build());
    }
}
