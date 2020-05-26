package org.vaadin.artur.exampledata;

import java.util.Random;

public class IdDataType extends DataType<Integer> {
    private int sequence = 1;

    @Override
    public Integer getValue(Random random) {
        return sequence++;
    }

}
