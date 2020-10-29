package org.vaadin.artur.exampledata;

import java.util.Random;

public class ChanceIntegerType extends DataType<Integer> {

    private String type;
    private String options = "";

    public ChanceIntegerType(String type) {
        this.type = type;
    }

    public ChanceIntegerType(String type, String options) {
        this.type = type;
        this.options = options;
    }

    @Override
    public Integer getValue(Random random, int seed) {
        return NodeScriptInterface.getChanceInteger(seed, this.type, this.options);
    }

}
