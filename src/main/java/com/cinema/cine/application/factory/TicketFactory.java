package com.cinema.cine.application.factory;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.cinema.cine.application.dto.ticket.TicketDto;
import com.cinema.cine.domain.api.usecase.MovieUseCase;
import com.cinema.cine.domain.api.usecase.UserUseCase;
import com.cinema.cine.domain.model.Movie;
import com.cinema.cine.domain.model.Ticket;
import com.cinema.cine.domain.model.User;

@Component
public class TicketFactory {

    private final UserUseCase userUseCase;
    private final MovieUseCase movieUseCase;

    public TicketFactory(UserUseCase userUseCase, MovieUseCase movieUseCase) {
        this.userUseCase = userUseCase;
        this.movieUseCase = movieUseCase;
    }

    public Ticket toDomain(TicketDto dto) {
        Optional<User> userOpt = userUseCase.getUserById(dto.userId());
        System.out.println("User: " + dto.userId());
        System.out.println("User id: " + userOpt.get().getId());
        System.out.println("User name: " + userOpt.get().getLastName());



        Optional<Movie> movieOpt = movieUseCase.findById(dto.movieId());

        if (userOpt.isEmpty() || movieOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Movie not found");
        }

        Ticket ticket = new Ticket();
        ticket.setSeatName(dto.seatName());
        ticket.setUser(userOpt.get());
        ticket.setMovie(movieOpt.get());

        return ticket;
    }

    public TicketDto toDto(Ticket ticket) {
        System.out.println("Controller");
         System.out.println("User: " + ticket.getUser());
        System.out.println("User id: " + ticket.getUser().getId());
        return new TicketDto(
            ticket.getSeatName(),
            ticket.getUser().getId(),
            ticket.getUser().getFullName(),
            ticket.getMovie().getId(),
            ticket.getMovie().getName()
        );
    }
}
