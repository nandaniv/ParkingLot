package org.nandani.parkinglot.model;

import lombok.Getter;
import org.nandani.parkinglot.model.enums.PaymentStatus;
import org.nandani.parkinglot.model.enums.TicketStatus;

import java.time.LocalDateTime;
@Getter
public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle ;
    private final String spotId;
    private final String reservationId;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private  PaymentStatus paymentStatus;
    private TicketStatus ticketStatus;

    public Ticket(String ticketId, Vehicle vehicle, String spotId, String reservationId, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spotId = spotId;
        this.reservationId = reservationId;
        this.entryTime = entryTime;
    }
    public void markPaymentSuccessful(){
        this.paymentStatus=PaymentStatus.SUCCESS;
    }
    public void markPaymentFailed(){
        this.paymentStatus=PaymentStatus.FAILED;
    }
    public void complete(){
        this.exitTime=LocalDateTime.now();
        this.ticketStatus=TicketStatus.COMPLETED;
    }
    public void cancel(){
        this.ticketStatus=TicketStatus.CANCEL;
    }
}
