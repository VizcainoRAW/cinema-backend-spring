package com.cinema.cine.domain.api.usecase;

import com.cinema.cine.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserUseCase {
    // Crear un nuevo usuario
    User createUser(User user);

    // Obtener usuario por ID
    Optional<User> getUserById(Long id);

    // Obtener usuario por email
    Optional<User> getUserByEmail(String email);

    // Obtener usuario por username
    Optional<User> getUserByUsername(String username);

    // Obtener todos los usuarios
    List<User> getAllUsers();

    // Obtener usuarios por rol
    List<User> getUsersByRole(String role);

    // Actualizar usuario
    User updateUser(User user);

    // Eliminar usuario por ID
    void deleteUser(Long id);

    // Validar si las credenciales son correctas
    boolean isValidUserCredentials(String email, String password);

    // Autenticar usuario (devuelve Optional<User> si las credenciales son válidas)
    Optional<User> authenticateUser(String email, String password);
}
