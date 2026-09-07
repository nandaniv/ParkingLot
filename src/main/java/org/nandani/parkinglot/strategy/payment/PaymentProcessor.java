package org.nandani.parkinglot.strategy.payment;

import org.nandani.parkinglot.dto.PaymentResult;

import java.math.BigDecimal;

public interface PaymentProcessor {
    PaymentResult processPayment(BigDecimal amount, String idempotencyKey);

}
