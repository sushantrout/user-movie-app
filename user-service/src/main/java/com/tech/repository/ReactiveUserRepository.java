package com.tech.repository;

import com.tech.model.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface ReactiveUserRepository extends R2dbcRepository<User, UUID> {
}

