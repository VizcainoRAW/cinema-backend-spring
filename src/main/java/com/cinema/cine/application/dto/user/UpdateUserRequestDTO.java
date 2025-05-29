package com.cinema.cine.application.dto.user;

import jakarta.validation.constraints.Size;

public record UpdateUserRequestDTO(
        @Size(max = 50, message = "First name cannot exceed 50 characters")
        String firstName,

        @Size(max = 50, message = "Last name cannot exceed 50 characters")
        String lastName,

        @Size(max = 20, message = "Phone number cannot exceed 20 characters")
        String phoneNumber
) {}
