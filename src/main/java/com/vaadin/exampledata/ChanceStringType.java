package com.vaadin.exampledata;

import java.time.LocalDateTime;
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
    public String getValue(Random random, int seed, LocalDateTime referenceTime) {
        return NodeScriptInterface.getChanceString(seed, this.type, this.options);
    }

}
