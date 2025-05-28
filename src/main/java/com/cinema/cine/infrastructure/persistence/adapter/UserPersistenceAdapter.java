package com.cinema.cine.infrastructure.persistence.adapter;

import com.cinema.cine.domain.model.User;
import com.cinema.cine.domain.model.UserRole;
import com.cinema.cine.domain.spi.UserPersistencePort;
import com.cinema.cine.infrastructure.persistence.entity.UserEntity;
import com.cinema.cine.infrastructure.persistence.repository.UserRepository;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapToEntity(user);
        UserEntity savedEntity = userRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::mapToDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::mapToDomain);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByRole(UserRole role) {
        return userRepository.findByRole(role)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private UserEntity mapToEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setRole(user.getRole());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        return entity;
    }

    private User mapToDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPhoneNumber(entity.getPhoneNumber());
        user.setRole(entity.getRole());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        return user;
    }
}