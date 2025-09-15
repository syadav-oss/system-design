package org.example.parkingLotSystem.service;

import org.apache.commons.lang3.ObjectUtils;
import org.example.parkingLotSystem.domain.*;
import org.example.parkingLotSystem.dto.Receipt;
import org.example.parkingLotSystem.dto.Ticket;
import org.example.parkingLotSystem.enums.PaymentMode;
import org.example.parkingLotSystem.enums.VehicleType;

import java.util.List;
import java.util.Map;

public class ParkingLotService {

    /**
     * input at entry gate
     * enter(String vehicleNumber, VehicleType)
     */
    Map<String,ParkingFloor> floors;
    Map<String, EntryGate> entryGateMap;
    Map<String, ExitGate> exitGateMap;
    Map<String, Ticket> ticketMap;

    public void addFloors(String floorId, String floorName) {
        floors.put(floorId,new ParkingFloor(floorId, floorName));
    }
    public void removeFloor(String floorId) {
        floors.remove(floorId);
    }

    public void addSpot(String floorId, ParkingSpot spot) {
        if(floors.containsKey(floorId)) {
            floors.get(floorId).addParkingSpots(spot);
        }
    }
    public void removeSpot(String floorId, String spotId) {
        if(floors.containsKey(floorId)) {
            floors.get(floorId).removeParkingSpot(spotId);
        }
    }

    public ParkingSpot assignSpot(VehicleType type, String vehicleNumber) {
        for (Map.Entry<String,ParkingFloor> floor : floors.entrySet()) {
            ParkingSpot spot = floor.getValue().findAvailableSpot(type);
            if (spot != null && spot.markOccupied(vehicleNumber)) {
                return spot;
            }
        }
        return null;
    }

    public Ticket entry(String vehicleNumber, String vehicleType, String entryGateId){
        EntryGate entryGate = entryGateMap.get(entryGateId);
        if(ObjectUtils.isEmpty(entryGate)) {
            System.out.println("Wrong entry gate");
            return null;
        }
        ParkingSpot availableSpot = assignSpot(VehicleType.valueOf(vehicleType), vehicleNumber);
        if(ObjectUtils.isEmpty(availableSpot)){
            System.out.println("Space is not available for vehicles with Type = " + vehicleType);
            return null;
        }
        Ticket ticket = entryGate.generateTicket(vehicleNumber, VehicleType.valueOf(vehicleType), availableSpot);
        ticketMap.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public Receipt exit(String ticketId, String exitGateId, String paymentModeStr) throws IllegalAccessException {
        ExitGate exitGate = exitGateMap.get(exitGateId);
        if(ObjectUtils.isEmpty(exitGate)) {
            System.out.println("Wrong exit gate");
            return null;
        }
        Ticket ticket = ticketMap.get(ticketId);
        if(ObjectUtils.isEmpty(ticket)) {
            System.out.println("Invalid Ticket");
            return null;
        }
        PaymentMode paymentMode = PaymentMode.valueOf(paymentModeStr);
        if(ObjectUtils.isEmpty(paymentMode)) {
            System.out.println("Invalid Payment mode");
            return null;
        }
        ticket.getParkingSpot().markAvailable();
        return exitGate.processExit(ticket, paymentMode);
    }

    public void setExitGateMap(Map<String, ExitGate> map) {
        exitGateMap = map;
    }

    public void setEntryGateMap(Map<String, EntryGate> entryGateMap) {
        this.entryGateMap = entryGateMap;
    }
}
