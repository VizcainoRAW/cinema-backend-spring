package com.cinema.cine.application.factory;

import com.cinema.cine.application.dto.user.CreateUserRequestDTO;
import com.cinema.cine.application.dto.user.UserDTO;
import com.cinema.cine.domain.model.User;
import com.cinema.cine.domain.model.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUserFromRequest(CreateUserRequestDTO requestDTO) {
        return new User(
                requestDTO.username(),
                requestDTO.email(),
                requestDTO.password(),
                requestDTO.firstName(),
                requestDTO.lastName(),
                requestDTO.phoneNumber()
        );
    }

    public UserDTO createDTOFromUser(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getRole().name(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public User createUserFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.id());
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setPhoneNumber(userDTO.phoneNumber());
        user.setRole(UserRole.valueOf(userDTO.role()));
        user.setCreatedAt(userDTO.createdAt());
        user.setUpdatedAt(userDTO.updatedAt());
        return user;
    }
}