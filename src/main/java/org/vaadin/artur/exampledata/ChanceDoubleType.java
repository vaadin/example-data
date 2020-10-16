package org.vaadin.artur.exampledata;

import java.util.Random;

public class ChanceDoubleType extends DataType<Double> {

    private String type;
    private String options = "";

    public ChanceDoubleType(String type) {
        this.type = type;
    }

    public ChanceDoubleType(String type, String options) {
        this.type = type;
        this.options = options;
    }

    @Override
    public Double getValue(Random random, int seed) {
        return ChanceInterface.getDouble(seed, this.type, this.options);
    }

}
