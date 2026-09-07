package org.nandani.parkinglot.dto;

import lombok.Getter;

@Getter
public class PaymentResult {
    private final boolean successful;
    private final String transactionId;
    private final String message;

    public PaymentResult(boolean successful, String transactionId, String message) {
        this.successful = successful;
        this.transactionId = transactionId;
        this.message = message;
    }
}
