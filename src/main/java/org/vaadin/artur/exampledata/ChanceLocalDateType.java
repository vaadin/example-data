package org.vaadin.artur.exampledata;

import java.time.LocalDate;
import java.util.Random;

public class ChanceLocalDateType extends DataType<LocalDate> {

    private String type;
    private String options;

    public ChanceLocalDateType(String type) {
        this.type = type;
    }

    public ChanceLocalDateType(String type, String options) {
        this.type = type;
        this.options = options;
    }

    @Override
    public LocalDate getValue(Random random, int seed) {
        random.setSeed(seed);
        return ChanceInterface.getLocalDate(random.nextInt(), this.type, this.options);
    }

}
