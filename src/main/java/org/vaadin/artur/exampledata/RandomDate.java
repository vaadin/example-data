package org.vaadin.artur.exampledata;

import java.time.LocalDate;
import java.util.Random;

public class RandomDate extends DataType<LocalDate> {

    private int maxDaysBack;

    public RandomDate(int maxDaysBack) {
        this.maxDaysBack = maxDaysBack;
    }

    @Override
    public LocalDate getValue(Random random, int seed) {
        Integer daysBack = NodeScriptInterface.getChanceInteger(seed, "integer", "{min: 0, max: " + maxDaysBack + "}");
        return LocalDate.now().minusDays(daysBack);
    }

}
