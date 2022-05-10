package com.vaadin.exampledata;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookImageGenerator extends DataType<String> {

    @Override
    public String getValue(Random random, int seed, LocalDateTime referenceTime) {
        byte[] image = DataType.BOOK_IMAGE_BACKGROUND_BINARY.getValue(random, seed, referenceTime);
        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(image);
    }

}
