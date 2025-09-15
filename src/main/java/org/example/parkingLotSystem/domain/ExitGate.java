package org.example.parkingLotSystem.domain;

import org.example.parkingLotSystem.dto.Receipt;
import org.example.parkingLotSystem.dto.Ticket;
import org.example.parkingLotSystem.enums.PaymentMode;
import org.example.parkingLotSystem.service.PaymentService;
import org.example.parkingLotSystem.service.PricingService;

import java.util.UUID;

public class ExitGate {
    public String getExitGateId() {
        return exitGateId;
    }

    private final String exitGateId;
    private final PricingService pricingService;
    private final PaymentService paymentService;

    public ExitGate(PricingService pricingService, PaymentService paymentService) {
        this.exitGateId = UUID.randomUUID().toString();
        this.pricingService = pricingService;
        this.paymentService = paymentService;
    }

    public Receipt processExit(Ticket ticket, PaymentMode paymentModes) throws IllegalAccessException {
        ticket.setExitTime(System.currentTimeMillis());
        ticket.setCost(pricingService.calculatePrice(ticket));
        return paymentService.processPayment(ticket.getCost(), paymentModes);
    }
}
