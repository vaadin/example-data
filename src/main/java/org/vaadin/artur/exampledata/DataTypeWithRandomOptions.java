package org.vaadin.artur.exampledata;

import java.util.Random;

public class DataTypeWithRandomOptions extends DataType<String> {

    private String[] options;

    public DataTypeWithRandomOptions(String resourceName) {
        this.options = FileCache.get(resourceName);
    }

    public String getValue(Random random, int seed) {
        random.setSeed(seed);
        return options[random.nextInt(options.length)];
    }

}
