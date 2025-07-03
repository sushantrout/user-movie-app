package com.tech.service;

import com.tech.mapper.UserMapper;
import com.tech.model.dto.UserDTO;
import com.tech.repository.ReactiveUserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserService {
    private final ReactiveUserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(ReactiveUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Mono<UserDTO> createUser(UserDTO userDTO) {
        return Mono.just(userDTO)
                .map(userMapper::toEntity)
                .flatMap(userRepository::save)
                .map(userMapper::toDto);
    }

    public Mono<UserDTO> getUserById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<String> deleteUser(UUID id) {
        return userRepository.deleteById(id)
                .then(Mono.just("User deleted successfully"));
    }

    public Flux<UserDTO> getUsers(int limit, int offset) {
        return userRepository.findAll()
                .map(userMapper::toDto);
    }

    public Mono<?> updateUser(UUID id, UserDTO userDTO) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(userDTO.getName());
                    existingUser.setEmail(userDTO.getEmail());
                    return userRepository.save(existingUser);
                })
                .map(userMapper::toDto)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }
}
