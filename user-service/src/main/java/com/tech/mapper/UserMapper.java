package com.tech.mapper;

import com.tech.model.dto.UserDTO;
import com.tech.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, UserDTO> {

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        if (entity == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
