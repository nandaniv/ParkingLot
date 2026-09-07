package org.nandani.parkinglot.strategy.parking;

import org.nandani.parkinglot.model.ParkingFloor;
import org.nandani.parkinglot.model.ParkingSpot;
import org.nandani.parkinglot.model.Vehicle;

import java.util.List;
import java.util.Optional;

public class FirstAvailableStrategy extends AbstractParkingSpotSelectionStrategy{
    @Override
    public Optional<String> findSpotById(Vehicle vehicle, List<ParkingFloor> parkingFloorList){
        for(ParkingFloor parkingFloor: parkingFloorList){
            for(ParkingSpot parkingSpot: parkingFloor.getParkingSpotList()){
                if(isCompatible(vehicle,parkingSpot)){
                    return Optional.of(parkingSpot.getSpotId());
                }
            }
        }
        return Optional.empty();
    }
}
