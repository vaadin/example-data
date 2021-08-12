package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class EanGenerator extends DataType<String> {

    @Override
    public String getValue(Random random, int seed, LocalDateTime referenceTime) {
        return NodeScriptInterface.getEan13(seed);
    }

}
