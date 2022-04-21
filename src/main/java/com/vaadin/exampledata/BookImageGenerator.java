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
        String title = DataType.BOOK_TITLE.getValue(random, seed, referenceTime);
        String author = DataType.FULL_NAME.getValue(random, seed, referenceTime);
        String imageUrl = DataType.BOOK_IMAGE_BACKGROUND.getValue(random, seed, referenceTime);

        String[] templateRows = FileCache.get("bookcover.svg.tpl");
        String template = Stream.of(templateRows).collect(Collectors.joining("\n"));
        String imageData = template.replace("#title#", title).replace("#image#", imageUrl).replace("#author#", author);
        return "data:image/svg+xml;base64," + Base64.getEncoder().encodeToString(imageData.getBytes(StandardCharsets.UTF_8));
    }

}
