package com.tech.router;

import com.tech.handler.RatingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> ratingRoutes(RatingHandler handler) {
        return RouterFunctions.route()
                .POST("/ratings", handler::create)
                .GET("/ratings/{id}", handler::getById)
                .GET("/ratings/movie/{id}", handler::getRatingsByMovieId)
                .GET("/ratings", handler::getAll)
                .PUT("/ratings/{id}", handler::update)
                .DELETE("/ratings/{id}", handler::delete)
                .build();
    }
}
