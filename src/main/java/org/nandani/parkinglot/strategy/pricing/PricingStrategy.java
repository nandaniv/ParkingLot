package org.nandani.parkinglot.strategy.pricing;

import org.nandani.parkinglot.model.Ticket;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Ticket ticket);
}
