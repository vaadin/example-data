package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomFileData extends DataType<byte[]> {

    private String[] fileNames;

    public RandomFileData(String fileNamesFile) {
        this.fileNames = FileCache.get(fileNamesFile);
    }

    public byte[] getValue(Random random, int seed, LocalDateTime referenceTime) {
        random.setSeed(seed);

        // This is needed to avoid that random.nextInt(32) gives the same number for
        // multiple seeds
        random.nextInt();

        return FileCache.getFileData(fileNames[random.nextInt(fileNames.length)]);
    }
}
