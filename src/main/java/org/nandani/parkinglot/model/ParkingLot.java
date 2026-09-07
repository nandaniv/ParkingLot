package org.nandani.parkinglot.model;

import lombok.Getter;

import java.util.List;
@Getter
public class ParkingLot {
    private final int lotId;
    private final String location;
    private final List<ParkingFloor> parkingFloorList;

    public ParkingLot(int lotId, String location, List<ParkingFloor> parkingFloorList) {
        this.lotId = lotId;
        this.location = location;
        this.parkingFloorList = parkingFloorList;
    }
}
