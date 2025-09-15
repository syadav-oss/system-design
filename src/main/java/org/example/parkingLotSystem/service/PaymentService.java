package org.example.parkingLotSystem.service;

import org.apache.commons.lang3.ObjectUtils;
import org.example.parkingLotSystem.domain.*;
import org.example.parkingLotSystem.dto.Receipt;
import org.example.parkingLotSystem.enums.PaymentMode;
import org.example.parkingLotSystem.interfaces.PaymentProcessor;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    private final Map<PaymentMode, PaymentProcessor> paymentModesPaymentProcessorMap;

    public PaymentService() {
        this.paymentModesPaymentProcessorMap = new HashMap<>();
        this.paymentModesPaymentProcessorMap.put(PaymentMode.CARD, new CardPaymentProcessor());
        this.paymentModesPaymentProcessorMap.put(PaymentMode.UPI, new UPIPaymentProcessor());
        this.paymentModesPaymentProcessorMap.put(PaymentMode.CASH, new CashPaymentProcessor());
    }

    public Receipt processPayment(double amount, PaymentMode paymentMode) throws IllegalAccessException {
        PaymentProcessor paymentProcessor = paymentModesPaymentProcessorMap.get(paymentMode);
        if(ObjectUtils.isEmpty(paymentProcessor)) {
            throw new IllegalAccessException("Payment mode is not supported, try again with some other mode");
        }
        paymentProcessor.pay(amount);
        Receipt receipt = new Receipt();
        receipt.setCost(amount);
        receipt.setPaymentMode(paymentMode);
        receipt.setStatus("SUCCESS");
        return receipt;
    }
}
