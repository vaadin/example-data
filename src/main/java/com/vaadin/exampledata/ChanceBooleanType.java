package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class ChanceBooleanType extends DataType<Boolean> {

    private String type;
    private String options = "";

    public ChanceBooleanType(String type) {
        this.type = type;
    }

    public ChanceBooleanType(String type, String options) {
        this.type = type;
        this.options = options;
    }

    @Override
    public Boolean getValue(Random random, int seed, LocalDateTime referenceTime) {
        return NodeScriptInterface.getChanceBoolean(seed, this.type, this.options);
    }

}
