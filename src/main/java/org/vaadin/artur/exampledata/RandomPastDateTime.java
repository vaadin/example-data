package org.vaadin.artur.exampledata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class RandomPastDateTime extends DataType<LocalDateTime> {

    private DataType<LocalDate> dateGenerator;
    private DataType<LocalTime> timeGenerator;

    public RandomPastDateTime(int maxDaysBack, boolean onlyHours) {
        dateGenerator = new RandomPastDate(maxDaysBack);
        timeGenerator = new RandomTime(onlyHours);
    }

    @Override
    public LocalDateTime getValue(Random random, int seed, LocalDateTime referenceTime) {
        LocalDate date = dateGenerator.getValue(random, seed, referenceTime);
        LocalTime time = timeGenerator.getValue(random, seed + date.getDayOfYear(), referenceTime);
        return LocalDateTime.of(date, time);
    }

}
