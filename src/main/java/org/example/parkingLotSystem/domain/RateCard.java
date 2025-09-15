package org.example.parkingLotSystem.domain;

import org.example.parkingLotSystem.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class RateCard {
    private final Map<VehicleType, Double> hourlyRates;

    public RateCard() {
        hourlyRates = new HashMap<>();
        hourlyRates.put(VehicleType.CAR, 20.0);
        hourlyRates.put(VehicleType.BIKE, 10.0);
        hourlyRates.put(VehicleType.TRUCK, 50.0);
    }

    public double getRate(VehicleType type) {
        return hourlyRates.getOrDefault(type, 0.0);
    }

    public void setRate(VehicleType type, double price) {
        hourlyRates.put(type,price);
    }
}
