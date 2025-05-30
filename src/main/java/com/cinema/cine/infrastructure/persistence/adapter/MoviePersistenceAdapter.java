package com.cinema.cine.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cinema.cine.domain.model.Movie;
import com.cinema.cine.domain.spi.MoviePersistencePort;
import com.cinema.cine.infrastructure.persistence.entity.MovieEntity;
import com.cinema.cine.infrastructure.persistence.repository.MovieRepository;

@Component
public class MoviePersistenceAdapter implements MoviePersistencePort{
    
    private final MovieRepository repository;

    public MoviePersistenceAdapter(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie save(Movie movie) {
        MovieEntity entity = mapToMovieEntity(movie);
        MovieEntity savedEntity = repository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public List<Movie> findAll() {
        return repository.findAll()
        .stream()
        .map(this::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private MovieEntity mapToMovieEntity(Movie movie) {
    MovieEntity entity = new MovieEntity();
    entity.setName(movie.getName());
    entity.setImagePath(movie.getImagePath());
    return entity;
    }

    private Movie mapToDomain(MovieEntity entity) {
    Movie movie = new Movie();
    movie.setId(entity.getId());
    movie.setName(entity.getName());
    movie.setImagePath(entity.getImagePath());
    return movie;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return repository.findById(id)
        .map(this::mapToDomain);
    }

}