package com.cinema.cine.application.handler;

import com.cinema.cine.application.factory.TicketFactory;
import com.cinema.cine.domain.api.usecase.TicketUseCase;
import com.cinema.cine.domain.model.Ticket;
import com.cinema.cine.domain.spi.TicketPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketUseCaseImpl implements TicketUseCase {

    private final TicketPersistencePort ticketPersistencePort;

    public TicketUseCaseImpl(TicketPersistencePort ticketPersistencePort, TicketFactory ticketFactory) {
        this.ticketPersistencePort = ticketPersistencePort;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketPersistencePort.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketPersistencePort.findAll();
    }

    @Override
    public void deleteById(Long id) {
        ticketPersistencePort.deleteById(id);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketPersistencePort.findById(id);
    }
}