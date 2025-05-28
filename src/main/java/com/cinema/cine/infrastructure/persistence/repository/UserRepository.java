package com.cinema.cine.infrastructure.persistence.repository;

import com.cinema.cine.domain.model.UserRole;
import com.cinema.cine.infrastructure.persistence.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findByRole(UserRole role);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}