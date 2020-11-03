package org.vaadin.artur.exampledata;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.util.UriUtils;

public class BookImageGenerator extends DataType<String> {

    @Override
    public String getValue(Random random, int seed) {
        String title = DataType.BOOK_TITLE.getValue(random, seed);
        String author = DataType.FULL_NAME.getValue(random, seed);
        String imageUrl = DataType.BOOK_IMAGE_BACKGROUND.getValue(random, seed);

        String[] templateRows = FileCache.get("bookcover.svg.tpl");
        String template = Stream.of(templateRows).collect(Collectors.joining("\n"));
        String imageData = template.replace("#title#", title).replace("#image#", imageUrl).replace("#author#", author);
        return "data:image/svg+xml;utf8," + UriUtils.encodeQuery(imageData, StandardCharsets.UTF_8);
    }

}
