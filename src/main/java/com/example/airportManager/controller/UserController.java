package com.example.airportManager.controller;

import com.example.airportManager.dto.user.UserCreateDTO;
import com.example.airportManager.dto.user.UserResponseDTO;
import com.example.airportManager.dto.user.UserUpdateDTO;
import com.example.airportManager.model.User;
import com.example.airportManager.model.UserStatus;
import com.example.airportManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(UUID id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public Page<UserResponseDTO> listAll(
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam Optional<UserStatus> status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> createdAtFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> createdAtTo
            ){

        return userService.listAll(pageable, status, createdAtFrom, createdAtTo);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
            @RequestBody UserCreateDTO userCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(userCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody UserUpdateDTO userUpdateDTO){
        UserResponseDTO updatedUser = userService.update(id, userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
