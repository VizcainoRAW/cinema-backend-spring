package com.cinema.cine.infrastructure.persistence.adapter;

import com.cinema.cine.domain.model.Movie;
import com.cinema.cine.domain.model.Ticket;
import com.cinema.cine.domain.model.User;
import com.cinema.cine.domain.spi.TicketPersistencePort;
import com.cinema.cine.infrastructure.persistence.entity.MovieEntity;
import com.cinema.cine.infrastructure.persistence.entity.TicketEntity;
import com.cinema.cine.infrastructure.persistence.entity.UserEntity;
import com.cinema.cine.infrastructure.persistence.repository.TicketRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TicketPersistenceAdapter implements TicketPersistencePort {

    private final TicketRepository ticketRepository;

    public TicketPersistenceAdapter(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity entity = toEntity(ticket);
        TicketEntity savedEntity = ticketRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAllWithUserAndMovie()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    private TicketEntity toEntity(Ticket ticket) {
        TicketEntity entity = new TicketEntity();

        User user = ticket.getUser();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        entity.setUser(userEntity);

        Movie movie = ticket.getMovie();
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(movie.getId());
        entity.setMovie(movieEntity);

        entity.setSeatName(ticket.getSeatName());
        entity.setId(ticket.getId());

        return entity;
    }

    private Ticket toDomain(TicketEntity entity) {
        UserEntity userEntity = entity.getUser();
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setRole(userEntity.getRole());

        MovieEntity movieEntity = entity.getMovie();
        Movie movie = new Movie();
        movie.setId(movieEntity.getId());
        movie.setName(movieEntity.getName());
        movie.setImagePath(movieEntity.getImagePath());

        return new Ticket(
                entity.getId(),
                user,
                movie,
                entity.getSeatName()
        );
    }
}
