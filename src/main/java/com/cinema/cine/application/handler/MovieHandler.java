// com/cinema/cine/application/handler/MovieHandler.java

package com.cinema.cine.application.handler;

import com.cinema.cine.application.factory.MovieFactory;
import com.cinema.cine.domain.api.usecase.MovieUseCase;
import com.cinema.cine.domain.model.Movie;
import com.cinema.cine.application.dto.movie.MovieDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovieHandler {

    private final MovieUseCase movieUseCase;
    private final MovieFactory movieFactory;

    public MovieHandler(MovieUseCase movieUseCase, MovieFactory movieFactory) {
        this.movieUseCase = movieUseCase;
        this.movieFactory = movieFactory;
    }

    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = movieFactory.toDomain(movieDto);
        return movieFactory.toDto(
            movieUseCase.createMovie(movie)
            );
    }

    public List<MovieDto> getAllMovies() {
        return movieUseCase.getAllMovies()
                .stream()
                .map(movieFactory::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MovieDto> getMovieById(Long id) {
        return movieUseCase.findById(id)
        .map(movieFactory::toDto);
    }

    public void deleteMovie(Long id) {
        movieUseCase.deleteById(id);
    }
}