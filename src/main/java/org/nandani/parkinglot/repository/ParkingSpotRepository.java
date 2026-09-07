package org.nandani.parkinglot.repository;

import org.nandani.parkinglot.model.ParkingSpot;
import org.nandani.parkinglot.model.enums.SpotStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingSpotRepository {

    private final Map<String, ParkingSpot> parkingSpots = new HashMap<>();

    public void save(ParkingSpot parkingSpot) {
        parkingSpots.put(parkingSpot.getSpotId(), parkingSpot);
    }

    public Optional<ParkingSpot> findById(String spotId) {
        return Optional.ofNullable(parkingSpots.get(spotId));
    }

    public synchronized boolean reserveSpot(
            String spotId,
            String reservationId,
            LocalDateTime expiry
    ) {
        ParkingSpot spot = parkingSpots.get(spotId);

        if (spot == null || spot.getStatus() != SpotStatus.AVAILABLE) {
            return false;
        }

        spot.reserve(reservationId, expiry);
        return true;
    }

    public synchronized boolean occupySpot(
            String spotId,
            String reservationId
    ) {
        ParkingSpot spot = parkingSpots.get(spotId);

        if (spot == null
                || spot.getStatus() != SpotStatus.RESERVED
                || !reservationId.equals(spot.getReservationId())) {
            return false;
        }

        if (spot.getReservationExpiryTime().isBefore(LocalDateTime.now())) {
            spot.release();
            return false;
        }

        spot.occupy(reservationId);
        return true;
    }

    public synchronized boolean releaseSpot(String spotId) {
        ParkingSpot spot = parkingSpots.get(spotId);

        if (spot == null) {
            return false;
        }

        spot.release();
        return true;
    }
}