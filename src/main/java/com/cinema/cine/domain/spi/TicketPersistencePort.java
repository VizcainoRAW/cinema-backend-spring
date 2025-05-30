package com.cinema.cine.domain.spi;

import java.util.List;
import java.util.Optional;

import com.cinema.cine.domain.model.Ticket;


public interface TicketPersistencePort {
    Ticket save(Ticket movie);
    List<Ticket> findAll();
    Optional<Ticket> findById(Long id);
    void deleteById(Long id);
}
