package com.cinema.cine.application.handler;

import com.cinema.cine.application.dto.ticket.TicketDto;
import com.cinema.cine.application.factory.TicketFactory;
import com.cinema.cine.domain.api.usecase.TicketUseCase;
import com.cinema.cine.domain.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TicketHandler {

    private final TicketUseCase ticketUseCase;
    private final TicketFactory ticketFactory;

    public TicketHandler(TicketUseCase ticketUseCase, TicketFactory ticketFactory) {
        this.ticketUseCase = ticketUseCase;
        this.ticketFactory = ticketFactory;
    }

    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket createdTicket = ticketUseCase.createTicket(ticketFactory.toDomain(ticketDto));
        return ticketFactory.toDto(createdTicket);
    }

    public List<TicketDto> listAllTickets() {
        return ticketUseCase.getAllTickets()
                .stream()
                .map(ticketFactory::toDto)
                .collect(Collectors.toList());
    }

    public Optional<TicketDto> findTicketById(Long id) {
        return ticketUseCase.findById(id).map(ticketFactory::toDto);
    }

    public void removeTicket(Long id) {
        ticketUseCase.deleteById(id);
    }
}
