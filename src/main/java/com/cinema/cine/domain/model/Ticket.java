package com.cinema.cine.domain.model;

import lombok.Getter;
import lombok.Setter; 

@Getter
@Setter
public class Ticket{
    private Long id;
    private User user;
    private Movie movie;
    private String seatName;

    public Ticket(){}

    public Ticket(Long id, User user, Movie movie, String seatName) {
        this.id = id;
        this.user = user;
        this.movie = movie;
        this.seatName = seatName;
    }
}