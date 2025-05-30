
package com.cinema.cine.application.factory;

import com.cinema.cine.domain.model.Movie;
import com.cinema.cine.application.dto.movie.*;
import org.springframework.stereotype.Component;

@Component
public class MovieFactory {

    public Movie toDomain(MovieDto dto) {
        Movie movie = new Movie();
        movie.setName(dto.name());
        movie.setImagePath(dto.imagePath());
        return movie;
    }

    public MovieDto toDto(Movie movie) {
        return new MovieDto(
            movie.getId(),
            movie.getName(),
             movie.getImagePath());
    }
}
