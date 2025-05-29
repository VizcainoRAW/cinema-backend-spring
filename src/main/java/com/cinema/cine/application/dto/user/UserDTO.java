package com.cinema.cine.application.dto.user;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public String getFullName() {
        return firstName + " " + lastName;
    }
}