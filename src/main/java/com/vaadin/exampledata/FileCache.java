package com.vaadin.exampledata;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;

public class FileCache {

    private static HashMap<String, String[]> fileCache = new HashMap<>();

    public static String[] get(String resourceName) {

        return fileCache.computeIfAbsent(resourceName, (name) -> {
            try (InputStream res = FileCache.class.getResourceAsStream(name)) {
                if (res == null) {
                    throw new IOException("Resource with name "
                            + FileCache.class.getPackage().getName().replace(".", "/") + "/" + name + " not found");
                }
                // Remove any empty rows
                return Stream.of(IOUtils.toString(res, StandardCharsets.UTF_8).split("\n"))
                        .filter(value -> !value.trim().isEmpty()).toArray(String[]::new);
            } catch (IOException e) {
                LoggerFactory.getLogger(FileCache.class).error("Unable to load options from " + name, e);
            }
            return new String[] {};
        });
    }
}
