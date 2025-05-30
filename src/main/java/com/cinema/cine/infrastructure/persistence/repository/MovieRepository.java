package com.cinema.cine.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.cine.infrastructure.persistence.entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Long> {

}
