package org.example.parkingLotSystem.dto;

import org.example.parkingLotSystem.enums.PaymentMode;

public class Receipt {
    double cost;
    PaymentMode paymentMode;
    String status;

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
