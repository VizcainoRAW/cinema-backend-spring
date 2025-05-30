package com.cinema.cine.domain.spi;

import java.util.List;
import java.util.Optional;

import com.cinema.cine.domain.model.Movie;

public interface MoviePersistencePort {
    Movie save(Movie movie);
    List<Movie> findAll();
    void deleteById(Long id);
    Optional<Movie> findById(Long id);
}
