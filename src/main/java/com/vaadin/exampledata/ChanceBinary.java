package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class ChanceBinary extends DataType<byte[]> {

    private byte[][] options;

    public ChanceBinary(String fileNames) {
        this.options = FileCache.getBinaries(fileNames);
    }

    public byte[] getValue(Random random, int seed, LocalDateTime referenceTime) {
        random.setSeed(seed);

        // This is needed to avoid that random.nextInt(32) gives the same number for
        // multiple seeds
        random.nextInt();

        return options[random.nextInt(options.length)];
    }
}
