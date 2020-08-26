package org.vaadin.artur.exampledata;

import java.time.LocalDate;
import java.util.Random;

public class BirthDayGenerator extends DataType<LocalDate> {
    private int firstYear;
    private int lastYear;

    public BirthDayGenerator(int firstYear, int lastYear) {
        this.firstYear = firstYear;
        this.lastYear = lastYear;
    }

    @Override
    public LocalDate getValue(Random random) {
        int year = firstYear + random.nextInt(lastYear - firstYear + 1);
        return LocalDate.ofYearDay(year, 1 + random.nextInt(365));
    }
}
