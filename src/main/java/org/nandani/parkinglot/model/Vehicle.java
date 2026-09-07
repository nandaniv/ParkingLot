package org.nandani.parkinglot.model;

import lombok.Getter;
import org.nandani.parkinglot.model.enums.VehicleType;
@Getter
public class Vehicle {
    private final String licenseNumber;
    private final VehicleType vehicleType;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }
}
