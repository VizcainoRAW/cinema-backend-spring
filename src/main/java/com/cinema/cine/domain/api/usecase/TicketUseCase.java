package com.cinema.cine.domain.api.usecase;

import java.util.List;
import java.util.Optional;

import com.cinema.cine.domain.model.Ticket;

public interface TicketUseCase {
    Ticket createTicket(Ticket Ticket);
    List<Ticket> getAllTickets();
    void deleteById(Long id);
    Optional<Ticket> findById(Long id);
}