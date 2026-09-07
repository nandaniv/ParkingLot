package org.nandani.parkinglot.service;

import org.nandani.parkinglot.dto.PaymentResult;
import org.nandani.parkinglot.factory.PaymentProcessorFactory;
import org.nandani.parkinglot.model.enums.PaymentType;
import org.nandani.parkinglot.strategy.payment.PaymentProcessor;

import java.math.BigDecimal;

public class PaymentService {
    public PaymentResult processPayment(BigDecimal amount, String idempotencyKey, PaymentType paymentType){
        PaymentProcessor paymentProcessor = PaymentProcessorFactory.getProcessor(paymentType);
        return paymentProcessor.processPayment(amount,idempotencyKey);

    }
}
