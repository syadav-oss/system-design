package org.example.parkingLotSystem.domain;

import org.example.parkingLotSystem.enums.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private final String floorId;
    private final String floorName;
    private final Map<String,ParkingSpot> parkingSpots;

    public ParkingFloor(String floorId, String floorName) {
        this.floorId = floorId;
        this.floorName = floorName;
        this.parkingSpots = new HashMap<>();
    }

    public String getFloorName() {
        return floorName;
    }

    public String getFloorId() {
        return floorId;
    }

    public void addParkingSpots(ParkingSpot parkingSpot) {
        this.parkingSpots.put(parkingSpot.getParkingSpotId(),parkingSpot);
    }

    public void removeParkingSpot(String parkingSpotId) {
        this.parkingSpots.remove(parkingSpotId);
    }

    public ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for(Map.Entry<String,ParkingSpot> spot : parkingSpots.entrySet()){
            if(spot.getValue().isAvailable() && spot.getValue().getParkingType().equals(vehicleType)){
                return spot.getValue();
            }
        }
        return null;
    }
}
