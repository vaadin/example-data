package org.vaadin.artur.exampledata;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Note this is manually kept in sync with /data-generator.tpl.ts
public class CombinedStringGenerator extends DataType<String> {

    private DataType<String>[] generators;

    @SafeVarargs
    public CombinedStringGenerator(DataType<String>... generators) {
        this.generators = generators;
    }

    @Override
    public String getValue(Random random, int seed) {
        return Stream.of(this.generators).map(generator -> generator.getValue(random, seed))
                .collect(Collectors.joining(" "));
    }

}
