package org.example.parkingLotSystem.dto;

import org.example.parkingLotSystem.domain.EntryGate;
import org.example.parkingLotSystem.domain.ParkingSpot;
import org.example.parkingLotSystem.enums.VehicleType;

public class Ticket {
    public String getTicketId() {
        return ticketId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public EntryGate getEntryGate() {
        return entryGate;
    }

    public double getCost() {
        return cost;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public void setCost(double price){
        this.cost = price;
    }

    private final String ticketId;
    private final String vehicleNumber;
    private final VehicleType vehicleType;
    private final long entryTime;
    private long exitTime;
    private final ParkingSpot parkingSpot;
    private final EntryGate entryGate;
    private double cost;
    public TicketBuilder ticketBuilder;

    private Ticket(TicketBuilder ticketBuilder) {
        this.ticketId = ticketBuilder.ticketId;
        this.vehicleNumber = ticketBuilder.vehicleNumber;
        this.vehicleType = ticketBuilder.vehicleType;
        this.entryTime = ticketBuilder.entryTime;
        this.exitTime = ticketBuilder.exitTime;
        this.parkingSpot = ticketBuilder.parkingSpot;
        this.entryGate = ticketBuilder.entryGate;
    }


    public static class TicketBuilder {
        private String ticketId;
        private String vehicleNumber;
        private VehicleType vehicleType;
        private long entryTime;
        private long exitTime;
        private ParkingSpot parkingSpot;
        private EntryGate entryGate;

        public TicketBuilder() {
        }

        public TicketBuilder setTicketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }
        public TicketBuilder setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            return this;
        }
        public TicketBuilder setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }
        public TicketBuilder setEntryTime(long entryTime) {
            this.entryTime = entryTime;
            return this;
        }
        public TicketBuilder setExitTime(long exitTime) {
            this.exitTime = exitTime;
            return this;
        }
        public TicketBuilder setParkingSpot(ParkingSpot parkingSpot) {
            this.parkingSpot = parkingSpot;
            return this;
        }
        public TicketBuilder setEntryGate(EntryGate entryGate) {
            this.entryGate = entryGate;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}
