package com.example.airportManager.service;

import com.example.airportManager.dto.user.UserCreateDTO;
import com.example.airportManager.dto.user.UserResponseDTO;
import com.example.airportManager.dto.user.UserUpdateDTO;
import com.example.airportManager.model.User;
import com.example.airportManager.model.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User getById(UUID id);
    Page<UserResponseDTO> listAll(Pageable pageable,
                                  Optional<UserStatus> status,
                                  Optional<LocalDateTime> createdAtFrom,
                                  Optional<LocalDateTime> createdAtTo
                                  );


    UserResponseDTO create(UserCreateDTO userCreateDTO);
    UserResponseDTO update(UUID id, UserUpdateDTO userUpdateDTO);
    void delete(UUID id);
}
