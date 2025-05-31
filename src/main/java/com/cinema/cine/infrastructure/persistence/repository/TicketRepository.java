package com.cinema.cine.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cinema.cine.infrastructure.persistence.entity.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity,Long>{

    @Query("SELECT t FROM TicketEntity t JOIN FETCH t.user JOIN FETCH t.movie")
    List<TicketEntity> findAllWithUserAndMovie();

}
