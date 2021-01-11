package org.vaadin.artur.exampledata;

import java.time.LocalTime;
import java.util.Random;

public class RandomTime extends DataType<LocalTime> {

    private boolean onlyHours;

    public RandomTime(boolean onlyHours) {
        this.onlyHours = onlyHours;
    }

    @Override
    public LocalTime getValue(Random random, int seed) {
        Integer hour = NodeScriptInterface.getChanceInteger(seed, "integer", "{min: 0, max: 23}");
        Integer minute = NodeScriptInterface.getChanceInteger(seed, "integer", "{min: 0, max: 59}");
        Integer second = NodeScriptInterface.getChanceInteger(seed, "integer", "{min: 0, max: 59}");
        if (onlyHours) {
            minute = 0;
            second = 0;
        }
        return LocalTime.of(hour, minute, second);
    }

}
