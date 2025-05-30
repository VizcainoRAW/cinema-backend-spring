package com.cinema.cine.domain.api.usecase;

import java.util.List;
import java.util.Optional;

import com.cinema.cine.domain.model.Movie;

public interface MovieUseCase {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();
    void deleteById(Long id);
    Optional<Movie> findById(Long id);
}
