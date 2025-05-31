package com.cinema.cine.infrastructure.controller;

import com.cinema.cine.application.dto.ticket.TicketDto;
import com.cinema.cine.application.handler.TicketHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketHandler ticketHandler;

    public TicketController(TicketHandler ticketHandler) {
        this.ticketHandler = ticketHandler;
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody TicketDto ticketDto) {
        TicketDto createdTicket = ticketHandler.createTicket(ticketDto);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return ResponseEntity.ok(ticketHandler.listAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) {
        return ticketHandler.findTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketHandler.removeTicket(id);
        return ResponseEntity.noContent().build();
    }
}