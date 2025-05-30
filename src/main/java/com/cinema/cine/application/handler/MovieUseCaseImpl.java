package com.cinema.cine.application.handler;

import com.cinema.cine.domain.api.usecase.MovieUseCase;
import com.cinema.cine.domain.model.Movie;
import com.cinema.cine.domain.spi.MoviePersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieUseCaseImpl implements MovieUseCase {

    private final MoviePersistencePort moviePersistencePort;

    public MovieUseCaseImpl(MoviePersistencePort moviePersistencePort) {
        this.moviePersistencePort = moviePersistencePort;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return moviePersistencePort.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return moviePersistencePort.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return moviePersistencePort.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        Optional<Movie> existing = moviePersistencePort.findById(id);
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Movie with ID " + id + " not found.");
        }
        moviePersistencePort.deleteById(id);
    }

}
