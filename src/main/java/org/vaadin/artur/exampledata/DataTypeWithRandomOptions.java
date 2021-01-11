package org.vaadin.artur.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class DataTypeWithRandomOptions extends DataType<String> {

    private String[] options;

    public DataTypeWithRandomOptions(String resourceName) {
        this.options = FileCache.get(resourceName);
    }

    public String getValue(Random random, int seed, LocalDateTime referenceTime) {
        random.setSeed(seed);

        // This is needed to avoid that random.nextInt(32) gives the same number for
        // multiple seeds
        random.nextInt();

        return options[random.nextInt(options.length)];
    }

}
