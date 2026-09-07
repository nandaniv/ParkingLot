package org.nandani.parkinglot.strategy.parking;

import org.nandani.parkinglot.model.ParkingSpot;
import org.nandani.parkinglot.model.Vehicle;
import org.nandani.parkinglot.model.enums.SpotStatus;
import org.nandani.parkinglot.model.enums.SpotType;

public abstract class AbstractParkingSpotSelectionStrategy implements ParkingSpotSelectionStrategy{
    protected boolean isCompatible(Vehicle vehicle, ParkingSpot parkingSpot){
        if(parkingSpot.getStatus()!= SpotStatus.AVAILABLE ) return false;
        return switch(vehicle.getVehicleType()){
            case BIKE -> parkingSpot.getSpotType() == SpotType.SMALL;
            case CAR -> parkingSpot.getSpotType() ==SpotType.COMPACT || parkingSpot.getSpotType()==SpotType.LARGE;
            case TRUCK -> parkingSpot.getSpotType()==SpotType.LARGE;
        };
    }
}
