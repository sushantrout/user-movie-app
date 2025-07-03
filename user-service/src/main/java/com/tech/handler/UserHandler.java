package com.tech.handler;

import com.tech.service.UserService;
import com.tech.model.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        return request.bodyToMono(UserDTO.class)
                .flatMap(userService::createUser)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .bodyValue(user));
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return userService.getUserById(id)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        int limit = request.queryParam("limit")
                .map(Integer::parseInt)
                .filter(l -> l > 0)
                .orElse(10);

        int offset = request.queryParam("offset")
                .map(Integer::parseInt)
                .filter(o -> o >= 0)
                .orElse(0);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getUsers(limit, offset), UserDTO.class);
    }


    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return userService.deleteUser(id)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        UUID id = UUID.fromString(serverRequest.pathVariable("id"));
        return serverRequest.bodyToMono(UserDTO.class)
                .flatMap(userDTO -> userService.updateUser(id, userDTO))
                .flatMap(user -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
