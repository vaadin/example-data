package org.vaadin.artur.exampledata;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

// Note this is manually kept in sync with /data-generator.tpl.ts
public class CombinedStringGenerator extends DataType<String> {

    private DataType<String>[] generators;
    private boolean sameSeed;
    private int seedOffset = 0;

    @SafeVarargs
    public CombinedStringGenerator(DataType<String>... generators) {
        this(true, 0, generators);
    }

    @SafeVarargs
    public CombinedStringGenerator(boolean sameSeed, int seedOffset, DataType<String>... generators) {
        this.seedOffset = seedOffset;
        this.generators = generators;
        this.sameSeed = sameSeed;
    }

    @Override
    public String getValue(Random random, int seed) {
        seed += seedOffset;
        Builder<String> values = Stream.builder();
        for (DataType<String> generator : generators) {
            String value = generator.getValue(random, seed);
            values.add(value);
            if (!sameSeed) {
                seed++;
            }
        }
        return values.build().collect(Collectors.joining(" "));
    }

}
