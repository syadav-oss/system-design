package org.example.parkingLotSystem;

import org.apache.commons.lang3.StringUtils;
import org.example.parkingLotSystem.domain.*;
import org.example.parkingLotSystem.dto.Receipt;
import org.example.parkingLotSystem.dto.Ticket;
import org.example.parkingLotSystem.service.AdminService;
import org.example.parkingLotSystem.service.ParkingLotService;
import org.example.parkingLotSystem.service.PaymentService;
import org.example.parkingLotSystem.service.PricingService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        PricingService pricingService = new PricingService(new RateCard());
        PaymentService paymentService = new PaymentService();
        ParkingLotService parkingLotService = new ParkingLotService();
        AdminService adminService = new AdminService(parkingLotService, pricingService);
        ExitGate exitGate = new ExitGate(pricingService, paymentService);
        EntryGate entryGate = new EntryGate();
        Map<String, EntryGate> entryGateMap = new HashMap<>();
        entryGateMap.put(entryGate.getGateId(), entryGate);
        parkingLotService.setEntryGateMap(entryGateMap);
        Map<String, ExitGate> exitGateMap = new HashMap<>();
        exitGateMap.put(exitGate.getExitGateId(), exitGate);
        parkingLotService.setExitGateMap(exitGateMap);
        while (scanner.hasNext()){
//            enter(vehicleNumber, VehicleType, EntryGate)
//            exit(String ticketId, ExitGate, PaymentMode)
            String input = scanner.nextLine();
            String opr = StringUtils.substringBefore(input,"(");
            String[] params = StringUtils.split(StringUtils.substringBetween(input, "(",")"), ',');
            if(opr.equalsIgnoreCase("enter")){
                Ticket ticket = parkingLotService.entry(params[0],params[1],params[2]);
            }else if(opr.equalsIgnoreCase("exit")) {
                Receipt receipt = parkingLotService.exit(params[0],params[1],params[2]);
            }
        }
    }
}
