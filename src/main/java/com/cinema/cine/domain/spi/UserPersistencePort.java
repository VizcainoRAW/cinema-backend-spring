package com.cinema.cine.domain.spi;

import com.cinema.cine.domain.model.User;
import com.cinema.cine.domain.model.UserRole;
import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    List<User> findByRole(UserRole role);
    void deleteById(Long id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}