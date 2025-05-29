package com.cinema.cine.application.handler;

import com.cinema.cine.application.dto.user.*;
import com.cinema.cine.application.factory.UserFactory;
import com.cinema.cine.domain.api.usecase.UserUseCase;
import com.cinema.cine.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserHandler {

    private final UserUseCase userUseCase;
    private final UserFactory userFactory;

    public UserHandler(UserUseCase userUseCase, UserFactory userFactory) {
        this.userUseCase = userUseCase;
        this.userFactory = userFactory;
    }

    public UserDTO registerUser(CreateUserRequestDTO requestDTO) {
        User user = userFactory.createUserFromRequest(requestDTO);
        User createdUser = userUseCase.createUser(user);
        return userFactory.createDTOFromUser(createdUser);
    }

    public Optional<UserDTO> findUserById(Long id) {
        return userUseCase.getUserById(id)
                .map(userFactory::createDTOFromUser);
    }

    public Optional<UserDTO> findUserByEmail(String email) {
        return userUseCase.getUserByEmail(email)
                .map(userFactory::createDTOFromUser);
    }

    public Optional<UserDTO> findUserByUsername(String username) {
        return userUseCase.getUserByUsername(username)
                .map(userFactory::createDTOFromUser);
    }

    public List<UserDTO> listAllUsers() {
        return userUseCase.getAllUsers()
                .stream()
                .map(userFactory::createDTOFromUser)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> updateUser(Long id, UpdateUserRequestDTO requestDTO) {
        Optional<User> existingUserOpt = userUseCase.getUserById(id);

        if (existingUserOpt.isPresent()) {
            User user = existingUserOpt.get();

            if (requestDTO.firstName() != null) {
                user.setFirstName(requestDTO.firstName());
            }
            if (requestDTO.lastName() != null) {
                user.setLastName(requestDTO.lastName());
            }
            if (requestDTO.phoneNumber() != null) {
                user.setPhoneNumber(requestDTO.phoneNumber());
            }

            User updatedUser = userUseCase.updateUser(user);
            return Optional.of(userFactory.createDTOFromUser(updatedUser));
        }

        return Optional.empty();
    }

    public void removeUser(Long id) {
        userUseCase.deleteUser(id);
    }

    public Optional<UserDTO> loginUser(LoginRequestDTO loginRequestDTO) {
        return userUseCase.authenticateUser(loginRequestDTO.email(), loginRequestDTO.password())
                .map(userFactory::createDTOFromUser);
    }

    public List<UserDTO> findUsersByRole(String role) {
        return userUseCase.getUsersByRole(role)
                .stream()
                .map(userFactory::createDTOFromUser)
                .collect(Collectors.toList());
    }
}