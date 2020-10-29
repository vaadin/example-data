package org.vaadin.artur.exampledata;

import java.util.Random;

public class EanGenerator extends DataType<String> {

    @Override
    public String getValue(Random random, int seed) {
        return NodeScriptInterface.getEan13(seed);
    }

}
