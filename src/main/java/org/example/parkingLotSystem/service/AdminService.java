package org.example.parkingLotSystem.service;

import org.example.parkingLotSystem.domain.ParkingSpot;
import org.example.parkingLotSystem.enums.VehicleType;

public class AdminService {

    private final ParkingLotService parkingLotService;
    private final PricingService pricingService;

    public AdminService(ParkingLotService parkingLotService, PricingService pricingService) {
        this.parkingLotService = parkingLotService;
        this.pricingService = pricingService;
    }

    public void addFloor(String floorId, String floorName) {
        parkingLotService.addFloors(floorId, floorName);
    }
    public void removeFloor(String floorId) {
        parkingLotService.removeFloor(floorId);
    }

    public void addSpot(String floorId, ParkingSpot spot) {
        parkingLotService.addSpot(floorId, spot);
    }
    public void removeSpot(String floorId, String spotId) {
        parkingLotService.removeSpot(floorId, spotId);
    }

    public void updateRate(VehicleType type, double newRate) {
        pricingService.updateRate(type, newRate);
    }

}
