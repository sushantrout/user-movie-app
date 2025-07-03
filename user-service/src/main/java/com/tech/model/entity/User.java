package com.tech.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Table("users")
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
}
