package org.nandani.parkinglot.strategy.payment;

import org.nandani.parkinglot.dto.PaymentResult;

import java.math.BigDecimal;
import java.util.UUID;

public class UPIPaymentProcessor implements PaymentProcessor{
    @Override
    public PaymentResult processPayment(BigDecimal amount, String idempotencyKey){
        String transactionId= UUID.randomUUID().toString();
        return new PaymentResult(true, transactionId, "SUCCESS!");
    }
}
