package org.vaadin.artur.exampledata;

import java.util.Random;

public class IntegerProvider extends DataType<Integer> {

    private int min;
    private int max;

    public IntegerProvider(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer getValue(Random random) {
        return random.nextInt(max - min) + min;
    }

}
