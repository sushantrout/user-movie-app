package com.tech.controller;

import com.tech.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<?> userRoutes(UserHandler handler) {
        return route(POST("/users"), handler::createUser)
                .andRoute(GET("/users"), handler::getAllUsers)
                .andRoute(GET("/users/{id}"), handler::getUserById)
                .andRoute(PUT("/users/{id}"), handler::updateUser)
                .andRoute(DELETE("/users/{id}"), handler::deleteUser);
    }
}
