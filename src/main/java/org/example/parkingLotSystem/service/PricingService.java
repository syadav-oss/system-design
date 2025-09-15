package org.example.parkingLotSystem.service;

import org.example.parkingLotSystem.domain.RateCard;
import org.example.parkingLotSystem.dto.Ticket;
import org.example.parkingLotSystem.enums.VehicleType;

import java.time.Duration;

public class PricingService {
    private final RateCard rateCard;

    public PricingService(RateCard rateCard) {
        this.rateCard = rateCard;
    }

    public double calculatePrice(Ticket ticket) {
        Duration duration = Duration.ofMillis(ticket.getExitTime() - ticket.getEntryTime());
        long hours = Math.max(duration.toHours(), 1);
        double rate = rateCard.getRate(ticket.getVehicleType());
        return hours * rate;
    }

    public void updateRate(VehicleType type, double newRate) {
        rateCard.setRate(type, newRate);
    }
}
