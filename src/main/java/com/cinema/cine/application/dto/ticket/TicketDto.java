package com.cinema.cine.application.dto.ticket;

public record TicketDto(
    String seatName,
    Long userId,
    String userFullName,
    Long movieId,
    String movieName
    
) {}
