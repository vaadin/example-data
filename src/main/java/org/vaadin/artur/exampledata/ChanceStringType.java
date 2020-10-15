package org.vaadin.artur.exampledata;

import java.util.Random;

public class ChanceStringType extends DataType<String> {

    private String type;
    private String options = "";

    public ChanceStringType(String type) {
        this.type = type;
    }

    public ChanceStringType(String type, String options) {
        this.type = type;
        this.options = options;
    }

    @Override
    public String getValue(Random random, int seed) {
        return ChanceInterface.getString(seed, this.type, this.options);
    }

}
