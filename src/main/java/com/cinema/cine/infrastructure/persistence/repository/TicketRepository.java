package com.cinema.cine.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinema.cine.infrastructure.persistence.entity.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity,Long>{}
