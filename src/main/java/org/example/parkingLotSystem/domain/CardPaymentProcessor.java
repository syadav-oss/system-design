package org.example.parkingLotSystem.domain;

import org.example.parkingLotSystem.interfaces.PaymentProcessor;

public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println("Total amount payed by Card = " + amount);
    }
}

