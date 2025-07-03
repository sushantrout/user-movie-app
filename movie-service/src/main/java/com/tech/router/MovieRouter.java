package com.tech.router;

import com.tech.handler.MovieHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class MovieRouter {

    @Bean
    public RouterFunction<ServerResponse> movieRoutes(MovieHandler handler) {
        return RouterFunctions.route()
                .POST("/movies", handler::createMovie)
                .GET("/movies/{id}", handler::getMovieById)
                .GET("/movies/{id}/details", handler::getDetailsById)
                .GET("/movies", handler::getAllMovies)
                .PUT("/movies/{id}", handler::updateMovie)
                .DELETE("/movies/{id}", handler::deleteMovie)
                .build();
    }
}
