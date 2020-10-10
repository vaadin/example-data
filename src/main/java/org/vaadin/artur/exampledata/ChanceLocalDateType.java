package org.vaadin.artur.exampledata;

import java.time.LocalDate;
import java.util.Random;

public class ChanceLocalDateType extends DataType<LocalDate> {

    private String type;

    public ChanceLocalDateType(String type) {
        this.type = type;
    }

    @Override
    public LocalDate getValue(Random random) {
        return ChanceInterface.getLocalDate(random.nextInt(), this.type);
    }

}
