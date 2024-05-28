package org.ticketingsystem.service;

import org.springframework.stereotype.Service;
import org.ticketingsystem.constants.StatusEnum;
import org.ticketingsystem.repository.TicketRepository;
import org.ticketingsystem.model.Ticket;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(UUID ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }

    public List<Ticket> getTicketsByStatus(String status) {
        return ticketRepository.getTicketsByStatus(status);
    }

    public Ticket createTicket(Ticket ticket) {
        ticket.setTicketId(UUID.randomUUID());
        ticket.setStatus(StatusEnum.NEW);
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket updateStatus(UUID ticketId, String status) {
        Ticket ticket = ticketRepository.getTicketById(ticketId);
        if(ticket == null) {
            return null;
        }
        ticket.setStatus(StatusEnum.valueOf(status));
        ticketRepository.save(ticket);
        return ticket;
    }

}
