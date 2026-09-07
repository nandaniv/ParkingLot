package org.nandani.parkinglot.model;

import lombok.Getter;
import org.nandani.parkinglot.model.enums.SpotStatus;
import org.nandani.parkinglot.model.enums.SpotType;

import java.time.LocalDateTime;
@Getter
public class ParkingSpot {
    private final String spotId;
    private  SpotStatus status;
    private final int floorNumber;
    private final SpotType spotType;
    private  String reservationId;
    private LocalDateTime reservationExpiryTime;


    public ParkingSpot(String spotId, int floorNumber, SpotType spotType) {
        this.spotId = spotId;
        this.floorNumber = floorNumber;
        this.spotType = spotType;
        this.status= SpotStatus.AVAILABLE;
    }
    //reserve, occupy, release
    public void reserve(String reservationId, LocalDateTime reservationExpiryTime){
        //spotstatus, expirytime, reserveid
        this.reservationId=reservationId;
        this.status= SpotStatus.RESERVED;
        this.reservationExpiryTime=reservationExpiryTime;
    }
    public void occupy(String reservationId){
        this.status=SpotStatus.OCCUPIED;
        this.reservationExpiryTime=null;
        this.reservationId=null;
    }
    public void release(){
        this.status=SpotStatus.AVAILABLE;
        this.reservationId=null;
        this.reservationExpiryTime=null;
    }
}
