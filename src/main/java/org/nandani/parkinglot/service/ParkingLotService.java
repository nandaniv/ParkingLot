package org.nandani.parkinglot.service;

import org.nandani.parkinglot.dto.PaymentResult;
import org.nandani.parkinglot.model.ParkingLot;
import org.nandani.parkinglot.model.Ticket;
import org.nandani.parkinglot.model.Vehicle;
import org.nandani.parkinglot.model.enums.PaymentType;
import org.nandani.parkinglot.repository.ParkingSpotRepository;
import org.nandani.parkinglot.strategy.parking.ParkingSpotSelectionStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingLotService {
    private final PaymentService paymentService;
    private final  PricingService pricingService;
    private final TicketService ticketService;
    private final ParkingSpotSelectionStrategy parkingSpotSelectionStrategy;
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingLot parkingLot;


    public ParkingLotService(PaymentService paymentService, PricingService pricingService, TicketService ticketService, ParkingSpotSelectionStrategy parkingSpotSelectionStrategy, ParkingSpotRepository parkingSpotRepository, ParkingLot parkingLot) {
        this.paymentService = paymentService;
        this.pricingService = pricingService;
        this.ticketService = ticketService;
        this.parkingSpotSelectionStrategy = parkingSpotSelectionStrategy;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingLot = parkingLot;
    }
    //find spot id
    // reserve the spot
    //generate ticket
    // occupy the spot
    public Ticket parkVehicle(Vehicle vehicle){
        String spotId = parkingSpotSelectionStrategy.findSpotById(vehicle, parkingLot.getParkingFloorList()).orElseThrow(()-> new IllegalStateException("No spot available"));
        String reservationId= UUID.randomUUID().toString();
        boolean reserved= parkingSpotRepository.reserveSpot(spotId,reservationId, LocalDateTime.now());
        if(!reserved){
            throw  new IllegalStateException("Unable to reserve spot");
        }
        Ticket ticket = ticketService.generateTicket(vehicle,spotId,reservationId);
        boolean occupied = parkingSpotRepository.occupySpot(spotId, reservationId);
        return ticket;
    }
    public void exitVehicle(
            String ticketId,
            PaymentType paymentType
    ) {

        Ticket ticket = ticketService.getTicket(ticketId);

        BigDecimal amount = pricingService.calculatePrice(ticket);

        PaymentResult paymentResult = paymentService.processPayment(
                amount,
                ticket.getTicketId(),
                paymentType
        );

        if (!paymentResult.isSuccessful()) {
            ticketService.markPaymentFailed(ticket);
            return;
        }

        ticketService.markPaymentSuccessful(ticket);

        boolean released = parkingSpotRepository.releaseSpot(
                ticket.getSpotId()
        );

        if (!released) {
            throw new IllegalStateException(
                    "Payment successful but unable to release parking spot"
            );
        }

        ticketService.completeTicket(ticket);
    }

}
