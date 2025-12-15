package com.example.airportManager.service.impl;

import com.example.airportManager.dto.user.UserCreateDTO;
import com.example.airportManager.dto.user.UserResponseDTO;
import com.example.airportManager.dto.user.UserUpdateDTO;
import com.example.airportManager.mapper.UserMapper;
import com.example.airportManager.model.User;
import com.example.airportManager.model.UserStatus;
import com.example.airportManager.repository.UserRepository;
import com.example.airportManager.service.UserService;
import com.example.airportManager.spec.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No User found!"));
        return user;
    }

    @Override
    public Page<UserResponseDTO> listAll(Pageable pageable,
                                         Optional<UserStatus> status,
                                         Optional<LocalDateTime> createdAtFrom,
                                         Optional<LocalDateTime> createdAtTo) {

        Specification<User> spec = Specification.where(null);
        if (createdAtFrom.isPresent() || createdAtTo.isPresent()) {
            spec = spec.and(UserSpecifications.createdBetween(createdAtFrom.orElse(null), createdAtTo.orElse(null)));
        }
        if (status.isPresent()){
            spec = spec.and(UserSpecifications.hasStatus(status.get()));
        }

        return userRepository.findAll(spec, pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public UserResponseDTO create(UserCreateDTO userCreateDTO) {

        return null;
    }

    @Override
    public UserResponseDTO update(UUID id, UserUpdateDTO userUpdateDTO) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
