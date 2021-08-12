package com.vaadin.exampledata;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public LocalDate getValue(Random random, int seed, LocalDateTime referenceTime) {
        random.setSeed(seed);
        return NodeScriptInterface.getChanceLocalDate(random.nextInt(), this.type, this.options);
    }

}
