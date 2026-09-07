package org.nandani.parkinglot.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpotList;

    public ParkingFloor(int floorNumber, List<ParkingSpot> parkingSpotList) {
        this.floorNumber = floorNumber;
        this.parkingSpotList = parkingSpotList;
    }
}
