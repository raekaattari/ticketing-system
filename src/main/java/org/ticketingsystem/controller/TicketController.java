package org.ticketingsystem.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ticketingsystem.model.Ticket;
import org.ticketingsystem.service.TicketService;

import java.util.List;
import java.util.UUID;

@RestController
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /* Get all tickets */
    @RequestMapping(value = {"/ticketing-system/tickets"}, method = RequestMethod.GET)
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /* Get ticket by id */
    @RequestMapping(value = {"/ticketing-system/tickets/{ticketId}"}, method = RequestMethod.GET)
    public Ticket getTicketById(@PathVariable("ticketId") UUID ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    /* Get all tickets by status */
    @RequestMapping(value = {"/ticketing-system/tickets/status/{status}"}, method = RequestMethod.GET)
    public List<Ticket> getTicketsByStatus(@PathVariable("status") String status) {
        return ticketService.getTicketsByStatus(status);
    }

    /* Create new ticket */
    @RequestMapping(value = {"/ticketing-system/tickets"}, method = RequestMethod.POST)
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    /* Update ticket status */
    @RequestMapping(value = {"/ticketing-system/tickets/status/{ticketId}"}, params = { "status"}, method = RequestMethod.PATCH)
    public Ticket updateStatus(@PathVariable("ticketId") UUID ticketId, @RequestParam String status) {
        return ticketService.updateStatus(ticketId, status);
    }
}