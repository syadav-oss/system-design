package org.example.parkingLotSystem.domain;

import org.example.parkingLotSystem.dto.Ticket;
import org.example.parkingLotSystem.enums.VehicleType;

import java.util.UUID;

public class EntryGate {
    private final String gateId;

    public String getGateId() {
        return gateId;
    }

    public EntryGate() {
        this.gateId = UUID.randomUUID().toString();
    }

    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, ParkingSpot parkingSpot) {
        return new Ticket.TicketBuilder()
                .setTicketId(UUID.randomUUID().toString())
                .setEntryGate(this)
                .setEntryTime(System.currentTimeMillis())
                .setVehicleNumber(vehicleNumber)
                .setVehicleType(vehicleType)
                .setParkingSpot(parkingSpot)
                .build();
    }
}
