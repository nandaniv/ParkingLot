package org.nandani.parkinglot.service;

import org.nandani.parkinglot.model.Ticket;
import org.nandani.parkinglot.strategy.pricing.PricingStrategy;

import java.math.BigDecimal;

public class PricingService {
    private final PricingStrategy pricingStrategy;

    public PricingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
    public BigDecimal calculatePrice(Ticket ticket){
       return pricingStrategy.calculatePrice(ticket);
    }
}
