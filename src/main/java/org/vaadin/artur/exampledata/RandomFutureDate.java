package org.vaadin.artur.exampledata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class RandomFutureDate extends DataType<LocalDate> {

    private int maxDaysBack;

    public RandomFutureDate(int maxDaysForward) {
        this.maxDaysBack = maxDaysForward;
    }

    @Override
    public LocalDate getValue(Random random, int seed, LocalDateTime referenceTime) {
        Integer daysBack = NodeScriptInterface.getChanceInteger(seed, "integer", "{min: 0, max: " + maxDaysBack + "}");
        return referenceTime.toLocalDate().plusDays(daysBack);
    }

}
