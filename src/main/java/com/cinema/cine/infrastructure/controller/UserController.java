package com.cinema.cine.infrastructure.controller;

import com.cinema.cine.application.dto.user.*;
import com.cinema.cine.application.handler.UserHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserHandler userHandler;

    public UserController(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserRequestDTO requestDTO) {
        UserDTO createdUser = userHandler.registerUser(requestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        return userHandler.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> findUserByEmail(@PathVariable String email) {
        return userHandler.findUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listAllUsers() {
        return ResponseEntity.ok(userHandler.listAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UpdateUserRequestDTO requestDTO) {
        return userHandler.updateUser(id, requestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id) {
        userHandler.removeUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return userHandler.loginUser(loginRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> findUsersByRole(@PathVariable String role) {
        return ResponseEntity.ok(userHandler.findUsersByRole(role));
    }
}