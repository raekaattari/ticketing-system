package org.ticketingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticketingsystem.constants.StatusEnum;
import org.ticketingsystem.model.Ticket;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    @Query(value = "SELECT * FROM public.ticket where ticket_id = :ticketId", nativeQuery = true)
    Ticket getTicketById(UUID ticketId);

    @Query(value = "SELECT * FROM public.ticket where status = :status", nativeQuery = true)
    List<Ticket> getTicketsByStatus(String status);

}

