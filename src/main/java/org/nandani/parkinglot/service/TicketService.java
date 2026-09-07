package org.nandani.parkinglot.service;

import org.nandani.parkinglot.model.Ticket;
import org.nandani.parkinglot.model.Vehicle;
import org.nandani.parkinglot.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public Ticket generateTicket(Vehicle vehicle, String spotId, String reservationId){
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, vehicle, spotId, reservationId, LocalDateTime.now());
        ticketRepository.save(ticket);
        return ticket;

    }
    public Ticket getTicket(String ticketId){
        return ticketRepository.findById(ticketId).orElseThrow(()->new IllegalArgumentException("no ticket found"));
    }
    public void markPaymentSuccessful(Ticket ticket){
        ticket.markPaymentSuccessful();
        ticketRepository.update(ticket);
    }
    public void markPaymentFailed(Ticket ticket) {
        ticket.markPaymentFailed();
        ticketRepository.update(ticket);
    }

    public void completeTicket(Ticket ticket) {
        ticket.complete();
        ticketRepository.update(ticket);
    }

    public void cancelTicket(Ticket ticket) {
        ticket.cancel();
        ticketRepository.update(ticket);
    }
}
