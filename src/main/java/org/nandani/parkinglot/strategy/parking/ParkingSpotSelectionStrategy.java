package org.nandani.parkinglot.strategy.parking;

import org.nandani.parkinglot.model.ParkingFloor;
import org.nandani.parkinglot.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotSelectionStrategy {
    Optional<String> findSpotById(Vehicle vehicle, List<ParkingFloor> parkingFloorList);
}
