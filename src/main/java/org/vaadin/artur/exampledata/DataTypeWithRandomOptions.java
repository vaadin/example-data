package org.vaadin.artur.exampledata;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;

public class DataTypeWithRandomOptions extends DataType<String> {

    private String[] options;

    public DataTypeWithRandomOptions(String resourceName) {
        try (InputStream res = getClass().getResourceAsStream(resourceName)) {
            if (res == null) {
                throw new IOException("Resource with name " + getClass().getPackage().getName().replace(".", "/") + "/"
                        + resourceName + " not found");
            }
            // Be sure to remove any empty rows
            this.options = Stream.of(IOUtils.toString(res, StandardCharsets.UTF_8).split("\n"))
                    .filter(value -> !value.trim().isEmpty()).toArray(String[]::new);
        } catch (IOException e) {
            LoggerFactory.getLogger(getClass()).error("Unable to load options from " + resourceName, e);
            this.options = new String[] { "N/A" };
        }
    }

    public String getValue(Random random) {
        return options[random.nextInt(options.length)];
    }

}
