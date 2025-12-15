package com.example.airportManager.mapper;

import com.example.airportManager.dto.user.UserCreateDTO;
import com.example.airportManager.dto.user.UserResponseDTO;
import com.example.airportManager.dto.user.UserUpdateDTO;
import com.example.airportManager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateDTO userCreateDTO);
    UserResponseDTO toResponse(User user);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUserFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

    default <T> T unwrap(Optional<T> optional) {
        if (optional == null) {
            return null;
        }
        return optional.orElse(null);
    }

    default <T> Optional<T> wrap(T value) {
        return Optional.ofNullable(value);
    }
}
