package com.tech.handler;

import com.tech.model.dto.RatingDTO;
import com.tech.model.entity.Rating;
import com.tech.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RatingHandler {

    private final RatingService ratingService;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(RatingDTO.class)
                .flatMap(ratingService::create)
                .flatMap(rating ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(rating)
                );
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return ratingService.getById(id)
                .flatMap(rating ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(rating))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ratingService.getAll(), RatingDTO.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return request.bodyToMono(RatingDTO.class)
                .flatMap(dto -> ratingService.update(id, dto))
                .flatMap(rating -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(rating))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return ratingService.delete(id)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getRatingsByMovieId(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        Flux<Rating> ratingsFlux = ratingService.getRatingsByMovieId(id);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ratingsFlux, Rating.class);
    }


}
