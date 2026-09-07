package org.nandani.parkinglot.client;


import org.nandani.parkinglot.model.*;
import org.nandani.parkinglot.model.enums.*;
import org.nandani.parkinglot.repository.ParkingSpotRepository;
import org.nandani.parkinglot.repository.TicketRepository;
import org.nandani.parkinglot.service.*;
import org.nandani.parkinglot.strategy.parking.FirstAvailableStrategy;
import org.nandani.parkinglot.strategy.parking.ParkingSpotSelectionStrategy;
import org.nandani.parkinglot.strategy.pricing.HourlyPricingStrategy;
import org.nandani.parkinglot.strategy.pricing.PricingStrategy;


import java.util.List;

public class ParkingLotClient {

    public static void main(String[] args) {

        // Create parking spots
        ParkingSpot spot1 =
                new ParkingSpot("F1-S1", 1, SpotType.COMPACT);

        ParkingSpot spot2 =
                new ParkingSpot("F1-S2", 1, SpotType.LARGE);

        ParkingSpot spot3 =
                new ParkingSpot("F1-S3", 1, SpotType.SMALL);


        // Create floor
        ParkingFloor floor =
                new ParkingFloor(
                        1,
                        List.of(spot1, spot2, spot3)
                );

        // Create parking lot
        ParkingLot parkingLot =
                new ParkingLot(
                        1,
                        "Hyderabad",
                        List.of(floor)
                );

        // Repositories
        ParkingSpotRepository parkingSpotRepository =
                new ParkingSpotRepository();

        parkingSpotRepository.save(spot1);
        parkingSpotRepository.save(spot2);
        parkingSpotRepository.save(spot3);

        TicketRepository ticketRepository =
                new TicketRepository();

        // Services
        TicketService ticketService =
                new TicketService(ticketRepository);

        PricingStrategy pricingStrategy =
                new HourlyPricingStrategy();

        PricingService pricingService =
                new PricingService(pricingStrategy);

        PaymentService paymentService =
                new PaymentService();

        // Strategy
        ParkingSpotSelectionStrategy spotSelectionStrategy =
                new FirstAvailableStrategy();

        // Main service
        ParkingLotService parkingLotService =
                new ParkingLotService(
                       paymentService,  pricingService, ticketService, spotSelectionStrategy, parkingSpotRepository, parkingLot
                );

        // Park vehicle
        Vehicle vehicle =
                new Vehicle(
                        "KA01AB1234",
                        VehicleType.CAR
                );

        Ticket ticket =
                parkingLotService.parkVehicle(vehicle);

        System.out.println(
                "Vehicle parked. Ticket ID: "
                        + ticket.getTicketId()
        );

        // Exit vehicle
        parkingLotService.exitVehicle(
                ticket.getTicketId(),
                PaymentType.UPI
        );

        System.out.println("Vehicle exited successfully.");
    }
}