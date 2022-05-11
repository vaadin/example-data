package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class BookImageGeneratorBytes extends DataType<byte[]> {

    @Override
    public byte[] getValue(Random random, int seed, LocalDateTime referenceTime) {
        return DataType.BOOK_IMAGE_BACKGROUND_BINARY.getValue(random, seed, referenceTime);
    }

}
