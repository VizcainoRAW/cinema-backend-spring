package com.cinema.cine.application.handler;

import com.cinema.cine.domain.api.usecase.UserUseCase;
import com.cinema.cine.domain.model.User;
import com.cinema.cine.domain.model.UserRole;
import com.cinema.cine.domain.spi.PasswordEncoderPort;
import com.cinema.cine.domain.spi.UserPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserUseCaseImpl implements UserUseCase {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderPort passwordEncoderPort;

    public UserUseCaseImpl(UserPersistencePort userPersistencePort,
                           PasswordEncoderPort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public User createUser(User user) {
        // Verificar si ya existe usuario con email o username
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        if (userPersistencePort.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("User with username " + user.getUsername() + " already exists");
        }

        // Codificar contraseña
        String encodedPassword = passwordEncoderPort.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userPersistencePort.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userPersistencePort.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userPersistencePort.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userPersistencePort.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.findAll();
    }

    @Override
    public List<User> getUsersByRole(String role) {
        try {
            UserRole userRole = UserRole.valueOf(role.toUpperCase());
            return userPersistencePort.findByRole(userRole);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUserOpt = userPersistencePort.findById(user.getId());
        if (existingUserOpt.isEmpty()) {
            throw new IllegalArgumentException("User with id " + user.getId() + " not found");
        }

        User existingUser = existingUserOpt.get();

        // Si se actualiza contraseña, codificarla
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoderPort.encode(user.getPassword());
            user.setPassword(encodedPassword);
        } else {
            // Mantener la contraseña existente si no se actualiza
            user.setPassword(existingUser.getPassword());
        }

        return userPersistencePort.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (userPersistencePort.findById(id).isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        userPersistencePort.deleteById(id);
    }

    @Override
    public boolean isValidUserCredentials(String email, String password) {
        Optional<User> userOpt = userPersistencePort.findByEmail(email);
        if (userOpt.isPresent()) {
            return passwordEncoderPort.matches(password, userOpt.get().getPassword());
        }
        return false;
    }

    @Override
    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> userOpt = userPersistencePort.findByEmail(email);
        if (userOpt.isPresent() && passwordEncoderPort.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
}