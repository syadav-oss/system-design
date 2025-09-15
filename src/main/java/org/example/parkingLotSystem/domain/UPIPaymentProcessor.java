package org.example.parkingLotSystem.domain;

import org.example.parkingLotSystem.interfaces.PaymentProcessor;

public class UPIPaymentProcessor implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println("Total amount payed through Upi = " + amount);
    }
}

