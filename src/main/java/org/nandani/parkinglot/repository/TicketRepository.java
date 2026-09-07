package org.nandani.parkinglot.repository;

import org.nandani.parkinglot.model.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TicketRepository {

    private final Map<String, Ticket> tickets = new HashMap<>();

    public void save(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
    }

    public Optional<Ticket> findById(String ticketId) {
        return Optional.ofNullable(tickets.get(ticketId));
    }

    public void update(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
    }
}