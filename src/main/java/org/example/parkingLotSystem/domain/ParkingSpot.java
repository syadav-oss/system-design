package org.example.parkingLotSystem.domain;

import org.example.parkingLotSystem.enums.VehicleType;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private final String parkingSpotId;
    private final VehicleType parkingType;
    private AtomicBoolean isAvailable;
    private String vehicleNumber;
    private final ParkingFloor parkingFloor;

    public ParkingSpot(VehicleType parkingType, ParkingFloor parkingFloor) {
        this.parkingSpotId = UUID.randomUUID().toString();
        this.parkingType = parkingType;
        this.parkingFloor = parkingFloor;
        isAvailable = new AtomicBoolean(true);
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public VehicleType getParkingType() {
        return parkingType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public boolean markOccupied(String vehicleNumber) {
        if(isAvailable.compareAndSet(true,false)) {
            setVehicleNumber(vehicleNumber);
            return true;
        }else{
            System.out.println("Slot is already occupied");
            return false;
        }
    }

    public void markAvailable() {
        isAvailable.set(true);
    }

    public boolean isAvailable() {
        return isAvailable.get();
    }
}
