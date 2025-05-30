package com.cinema.cine.infrastructure.controller;

import com.cinema.cine.application.dto.movie.MovieDto;
import com.cinema.cine.application.handler.MovieHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieHandler movieHandler;

    public MovieController(MovieHandler movieHandler) {
        this.movieHandler = movieHandler;
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        MovieDto created = movieHandler.createMovie(movieDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return movieHandler.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping 
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieHandler.getAllMovies());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieHandler.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}