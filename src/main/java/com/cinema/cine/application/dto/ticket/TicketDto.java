package com.cinema.cine.application.dto.ticket;

public record TicketDto(
    Long id,
    String seatName,
    Long userId,
    String userFullName,
    Long movieId,
    String movieName
    
) {}
