package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class IdDataType extends DataType<Integer> {
    private int sequence = 1;

    @Override
    public Integer getValue(Random random, int seed, LocalDateTime referenceTime) {
        return sequence++;
    }

}
