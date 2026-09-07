package org.nandani.parkinglot.factory;

import org.nandani.parkinglot.model.enums.PaymentType;
import org.nandani.parkinglot.strategy.payment.PaymentProcessor;
import org.nandani.parkinglot.strategy.payment.UPIPaymentProcessor;

public class PaymentProcessorFactory {
    public static PaymentProcessor getProcessor(PaymentType paymentType){
        return switch(paymentType){
            case CARD -> null;
            case CASH -> null;
            case PaymentType.UPI -> new UPIPaymentProcessor();

        };
    }
}
