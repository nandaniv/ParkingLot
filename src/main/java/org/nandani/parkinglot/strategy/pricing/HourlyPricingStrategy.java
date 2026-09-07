package org.nandani.parkinglot.strategy.pricing;

import org.nandani.parkinglot.model.Ticket;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyPricingStrategy implements PricingStrategy {
    private static final BigDecimal HOURLY_RATE = BigDecimal.valueOf(50);
    @Override
    public BigDecimal calculatePrice(Ticket ticket){
        LocalDateTime exitTime = ticket.getExitTime() != null
                ? ticket.getExitTime()
                : LocalDateTime.now();

        long minutes = Duration.between(
                ticket.getEntryTime(),
                exitTime
        ).toMinutes();

        long hours = Math.max(1, (minutes + 59) / 60);

        return HOURLY_RATE.multiply(BigDecimal.valueOf(hours));
    }
}
